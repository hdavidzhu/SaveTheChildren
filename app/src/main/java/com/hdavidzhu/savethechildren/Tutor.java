package com.hdavidzhu.savethechildren;

import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.hdavidzhu.savethechildren.R;
import com.hdavidzhu.savethechildren.callbacks.SubjectsCallback;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import java.util.List;

public class Tutor extends Fragment{
    Context context;
    String name;
    //this fragment will display the name and then the list of things they need training in

    ListView listview;
    ArrayAdapter<String> adapter;
    List<String> arr;

    //String[] items = {"Fake Module 1", "Fake Module 2", "Fake Module 3", "Fake Module 4"};

    public static Tutor newInstance(String name) {
        Tutor fragment = new Tutor();
        fragment.name = name;
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        View view = inflater.inflate(R.layout.tab, container, false);
//        TextView textview = (TextView) view.findViewById(R.id.text_view);
//        textview.setText("Yeah what up");
        View view = inflater.inflate(R.layout.tutor, container, false);
//        handleBackPress();

        //now you must initialize your list view
        ListView listview = (ListView)view.findViewById(R.id.training_list);
        arr = MainActivity.tutorItems;
        final TextView tutorName = (TextView) view.findViewById(R.id.tutor_name);
        tutorName.setText(this.name);
        adapter = new ArrayAdapter<String>(view.getContext(), R.layout.tutor_list_item, arr);
        listview.setAdapter(adapter);

        Button addButton = (Button) view.findViewById(R.id.add_button);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.main_activity_container, new Subject());

                //ft.addToBackStack(null);;
                ft.commit();
            }
        });

        listview.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                removeItemFromList(i);
                return false;
            }
        });
        return view;
    }

    // method to remove list item
    protected void removeItemFromList(int position) {
        final int deletePosition = position;

        AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());

        alert.setTitle("Delete");
        alert.setMessage("Do you want delete this item?");
        alert.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // main code on after clicking yes
                arr.remove(deletePosition);
                adapter.notifyDataSetChanged();
                adapter.notifyDataSetInvalidated();// TODO remove?

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

    private void handleBackPress() {
        if (getView() != null) {
            getView().setFocusableInTouchMode(true);
            getView().setOnKeyListener(new View.OnKeyListener() {
                @Override
                public boolean onKey(View v, int keyCode, KeyEvent event) {
                    if (keyCode == KeyEvent.KEYCODE_BACK) {
                        Log.i("DebugDebug", "Here");
                        getFragmentManager().beginTransaction().replace(R.id.main_activity_container, new NewTutor()).commit();
                        return true;
                    }
                    return false;
                }
            });
        }
    }
}
