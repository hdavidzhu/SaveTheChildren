package com.hdavidzhu.savethechildren;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

public class Roster extends Fragment{
    MainActivity activity;
    ArrayAdapter<String> adapter;
    List<String> tutorNames;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        //Input: The Main Activity activity
        //Output: None
        //This method is run when the fragment and the Main Activity first meet.
        // It informs the Main Activity that this fragment belongs to the activity.
        this.activity = (MainActivity) activity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        //Input: inflated layout, view container, and bundled instances saved by previous fragments
        //Output: view
        //This method runs every time Roster is called.

        View view = inflater.inflate(R.layout.roster, container, false); //inflates the view with the appropriate layout

        //initialize your list view
        ListView listview =(ListView)view.findViewById(R.id.list_view);

        tutorNames = MainActivity.names;

        adapter = new ArrayAdapter<String>(view.getContext(), R.layout.roster_list_item, MainActivity.names);
        listview.setAdapter(adapter);

        //when one of the items in the list is clicked, it gets added to the names String Array in Main Activity.
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                activity.switchTutor(MainActivity.names.get(i));
            }
        });

        // This listener allows users to remove tutors from the roster using a long click
        listview.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                // Calls the alert dialog that gives tutors an option to confirm or cancel
                removeItemFromList(i);
                return true;
            }
        });

        return view;
    }

    // This method to takes in a position of the item that is clicked and then creates an alert dialog that allows users to delete the item
    protected void removeItemFromList(int position) {
        final int deletePosition = position;

        AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());

        alert.setTitle("Delete");
        alert.setMessage("Do you want delete this item?");
        alert.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // main code on after clicking yes

                VolleySingleton.getInstance().deleteTutor(MainActivity.names.get(deletePosition));
                MainActivity.names.remove(deletePosition);
                adapter.notifyDataSetChanged();
            }
        });
        alert.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        alert.show();
    }
}