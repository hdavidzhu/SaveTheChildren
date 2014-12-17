package com.hdavidzhu.savethechildren;

import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.hdavidzhu.savethechildren.callbacks.TutorItemsCallback;

import java.util.List;

// This fragment will display the Tutors name and then the list of things they need training in
public class Tutor extends Fragment{
    String name;

    ListView listview;
    ArrayAdapter<String> adapter;
    List<String> arr;

    // Create a new instance of the Tutor fragment with the correct name from the Roster fragment
    public static Tutor newInstance(String name) {
        Tutor fragment = new Tutor();
        fragment.name = name;
        return fragment;
    }

    // The onCreate method for this class takes in the Layout, the View, and the Bundle that contains variables we need
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.tutor, container, false);

        // Grabs the TextView for tutor name and sets the text
        final TextView tutorName = (TextView) view.findViewById(R.id.tutor_name);
        tutorName.setText(this.name);

        // Accesses the ListView that contains training subjects the tutor has requested
        final ListView listview = (ListView)view.findViewById(R.id.training_list);

        // Gets an instance of the VolleySingleton and the getTutorItems method
        VolleySingleton.getInstance().getTutorItems(this.name, new TutorItemsCallback() {
            // handle takes in the list of training subjects from the TutorItemsCallback
            @Override
            public void handle(List<String> tutorItems) {
                arr = tutorItems;
                // A new adapter is created to connect the list with the view where the items will be rendered
                adapter = new ArrayAdapter<String>(view.getContext(), R.layout.tutor_list_item, arr);
                listview.setAdapter(adapter);
            }
        });

        // This code names the 'add' button that allows tutors to add to their list of needed training
        Button addButton = (Button) view.findViewById(R.id.add_button);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // When the button is clicked, transition ot the Subject's fragment
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.main_activity_container, new Subject());
                ft.addToBackStack(null);
                ft.commit();
            }
        });

        // This listener allows tutors to remove items from their list by pressing on the item for a few seconds
        listview.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                // Calls the alert dialog that gives tutors an option to confirm or cancel
                removeItemFromList(i);
                return false;
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

                VolleySingleton.getInstance().deleteTutorItem(MainActivity.curTutor.name, arr.get(deletePosition));
                arr.remove(deletePosition);

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

//    private void handleBackPress() {
//        if (getView() != null) {
//            getView().setFocusableInTouchMode(true);
//            getView().setOnKeyListener(new View.OnKeyListener() {
//                @Override
//                public boolean onKey(View v, int keyCode, KeyEvent event) {
//                    if (keyCode == KeyEvent.KEYCODE_BACK) {
//                        Log.i("DebugDebug", "Here");
//                        getFragmentManager().beginTransaction().replace(R.id.main_activity_container, new NewTutor()).commit();
//                        return true;
//                    }
//                    return false;
//                }
//            });
//        }
//    }
}