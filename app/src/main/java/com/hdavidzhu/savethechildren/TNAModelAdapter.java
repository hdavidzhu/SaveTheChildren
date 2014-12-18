package com.hdavidzhu.savethechildren;

import android.widget.ArrayAdapter;
import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

// This class is an adapter that handles TNA model data and views
public class TNAModelAdapter extends ArrayAdapter<TNAModel> {
    private final Context context;
    private final ArrayList<TNAModel> modelsArrayList;

    public TNAModelAdapter(Context context, ArrayList<TNAModel> modelsArrayList) {

        super(context, R.layout.tna_target, modelsArrayList);

        this.context = context;
        this.modelsArrayList = modelsArrayList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // Create inflater
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        // Get rowView from inflater

        View rowView = null;
        if(!modelsArrayList.get(position).isGroupHeader){
            rowView = inflater.inflate(R.layout.tna_target, parent, false);

            // Get title and counter views from the rowView
            TextView titleView = (TextView) rowView.findViewById(R.id.item_title);
            TextView counterView = (TextView) rowView.findViewById(R.id.item_counter);

            // Set the text
            titleView.setText(modelsArrayList.get(position).getTitle());
            counterView.setText(modelsArrayList.get(position).getCounter());
        }
        else{
            rowView = inflater.inflate(R.layout.tna_header, parent, false);
            TextView titleView = (TextView) rowView.findViewById(R.id.header);
            titleView.setText(modelsArrayList.get(position).getTitle());

        }

        return rowView;
    }
}
