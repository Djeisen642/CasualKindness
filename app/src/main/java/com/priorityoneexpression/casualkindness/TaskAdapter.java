package com.priorityoneexpression.casualkindness;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Jason on 2/9/2015.
 */
public class TaskAdapter extends ArrayAdapter<Act> {
	public TaskAdapter(Context context, ArrayList<Act> acts) {
		super(context, 0, acts);
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		Act act = getItem(position);

		if (convertView == null) {
			convertView = LayoutInflater.from(getContext())
					.inflate(R.layout.task_view, parent, false);
		}
		TextView name = (TextView) convertView.findViewById(
				R.id.taskName);
		name.setText(act.getName());
		return  convertView;
	}
}
