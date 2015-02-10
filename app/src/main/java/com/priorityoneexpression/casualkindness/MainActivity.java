package com.priorityoneexpression.casualkindness;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.TextView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.List;
import java.util.Random;

public class MainActivity extends ActionBarActivity {

    private Context context = this;
    public static final String EXTRA_ID = "com.priorityoneexpression.casualkindness.ID";
    private static final int FOREVER = -1;
    private static final int MONEY_FREE = 0;
    private static final int MONEY_$ = 1;
    private static final int MONEY_$$ = 2;
    private static final int MONEY_$$$ = 3;
    private static final int MONEY_$$$$ = 4;
    private static final int MONEY_PRICELESS = 5;
    private int moneyRadioButton = 0;
    private double timeAmount = 0;
    private String objectId = null;
    private Intent intent = null;
    protected CasualKindness app;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
	    setContentView(R.layout.activity_main);
		// TODO: Make the tabs
	    app = (CasualKindness) getApplication();

	    SeekBar timeSeekBar = (SeekBar) findViewById(R.id.timeSeekBar);
	    timeSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
		    @Override
		    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
			    //timeAmount = progress;
			    timeAmount = 4.0733 * Math.exp(progress * 0.1212) - 3.00; // used excel to figure out a progression that worked
			    TextView timeAmountText = (TextView) findViewById(R.id.timeAmount);
			    if (progress == 100) {
				    timeAmount = FOREVER;
				    timeAmountText.setText("Forever!");
			    } else if (timeAmount > 60.0) {
				    double hours = timeAmount / 60.0;
				    if (hours > 24.0) {
					    double days = hours / 24.0;
					    if (days > 30.0) {
						    double months = days / 24.0;
						    if (months > 12.0) {
							    double years = months / 12.0;
							    if ((int) years == 1.0)
								    timeAmountText.setText(String.valueOf((int) years) + " year");
							    else
								    timeAmountText.setText(String.valueOf((int) years) + " years");
						    } else {
							    if ((int) months == 1.0)
								    timeAmountText.setText(String.valueOf((int) months) + " month");
							    else
								    timeAmountText.setText(String.valueOf((int) months) + " months");
						    }
					    } else {
						    if ((int) days == 1.0)
							    timeAmountText.setText(String.valueOf((int) days) + " day");
						    else
							    timeAmountText.setText(String.valueOf((int) days) + " days");
					    }
				    } else {
					    if ((int) hours == 1.0)
						    timeAmountText.setText(String.valueOf((int) hours) + " hour");
					    else
						    timeAmountText.setText(String.valueOf((int) hours) + " hours");
				    }
			    } else {
				    if ((int) timeAmount == 1.0)
					    timeAmountText.setText(String.valueOf((int) timeAmount) + " minute");
				    else
					    timeAmountText.setText(String.valueOf((int) timeAmount) + " minutes");
			    }
		    }

		    @Override
		    public void onStartTrackingTouch(SeekBar seekBar) {

		    }

		    @Override
		    public void onStopTrackingTouch(SeekBar seekBar) {

		    }
	    });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.freeMoney:
                if (checked)
                    moneyRadioButton = MONEY_FREE;
                    break;
            case R.id.littleMoney:
                if (checked)
                    moneyRadioButton = MONEY_$;
                    break;
            case R.id.middleMoney:
                if (checked)
                    moneyRadioButton = MONEY_$$;
                    break;
            case R.id.bigMoney:
                if (checked)
                    moneyRadioButton = MONEY_$$$;
                    break;
            case R.id.hugeMoney:
                if (checked)
                    moneyRadioButton = MONEY_$$$$;
                    break;
            case R.id.pricelessMoney:
                if (checked)
                    moneyRadioButton = MONEY_PRICELESS;
                    break;
        }
    }

    public void getAct(View view) {

	    ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
	    NetworkInfo netInfo = cm.getActiveNetworkInfo();
	    if (netInfo != null && netInfo.isConnectedOrConnecting()) {
		    intent = new Intent(this, DisplayActActivity.class);
		    ParseQuery<ParseObject> query = ParseQuery.getQuery("Acts");
		    if (timeAmount == FOREVER) {
			    query.whereGreaterThanOrEqualTo("TimeEstimate", timeAmount);
		    } else {
			    query.whereLessThanOrEqualTo("TimeEstimate", timeAmount);
		    }
		    query.whereLessThanOrEqualTo("MonetaryEstimate", moneyRadioButton);
		    query.findInBackground(new FindCallback<ParseObject>() {
			    @Override
			    public void done(List<ParseObject> parseObjects, ParseException e) {
				    if (e == null) {
					    Log.d("acts", "Retrieved " + parseObjects.size() + " acts");
					    if (parseObjects.size() > 0) {
						    Random r = new Random();
						    int randomNumber = r.nextInt(parseObjects.size());
						    ParseObject theChosenOne = parseObjects.listIterator(randomNumber).next();
						    objectId = theChosenOne.getObjectId();
						    intent.putExtra(EXTRA_ID, objectId);
						    startActivity(intent);
					    } else {
						    Log.d("acts", "Error: No objects in criteria");
						    AlertDialog.Builder builder = new AlertDialog.Builder(context);
						    builder
								    .setTitle("No Acts")
								    .setMessage("There are no acts that fit your criteria. Please submit more to raptor923@yahoo.com so that we can fill in these gaps. Thanks!")
								    .setCancelable(true);
						    AlertDialog alertDialog = builder.create();
						    alertDialog.show();
					    }
				    } else {
					    Log.d("acts", "Error: " + e.getMessage());
				    }
			    }
		    });
	    } else {
		    AlertDialog.Builder builder = new AlertDialog.Builder(context);
		    builder
				    .setTitle("No Network Connection")
				    .setMessage("You are not connected to the internet. To get your act, please connect to the internet.")
				    .setCancelable(true);
		    AlertDialog alertDialog = builder.create();
		    alertDialog.show();
	    }
    }
}
