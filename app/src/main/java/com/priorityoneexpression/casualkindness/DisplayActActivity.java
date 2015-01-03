package com.priorityoneexpression.casualkindness;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;


public class DisplayActActivity extends ActionBarActivity {

    private TextView titleView = null;
    private TextView descriptionView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_act);
        titleView = (TextView) findViewById(R.id.actTitleView);
        descriptionView = (TextView) findViewById(R.id.actDescriptionView);
        Intent intent = getIntent();
        String objectId = intent.getStringExtra(MainActivity.EXTRA_ID);
        ParseQuery <ParseObject> query = ParseQuery.getQuery("Acts");
        query.getInBackground(objectId, new GetCallback<ParseObject>() {
            @Override
            public void done(ParseObject parseObject, ParseException e) {
                if (e == null) {
                    Log.d("act", "Retrieved: " + parseObject.getObjectId());
                    String title = parseObject.getString("UniqueDescriptiveName");
                    String description = parseObject.getString("FullDescription");
                    if (titleView == null || descriptionView == null) {
                        Log.d("View", "Error: Views don't exist.");
                    } else {
                        titleView.setText(title);
                        descriptionView.setText(description);
                    }
                } else {
                    Log.d("act", "Error: " + e.getMessage());
                }
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_display_act, menu);
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

    public void acceptAct(View view) {
        // TODO: Add checklist
    }

    public void declineAct(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
