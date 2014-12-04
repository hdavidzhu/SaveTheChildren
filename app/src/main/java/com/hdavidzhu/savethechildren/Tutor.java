package com.hdavidzhu.savethechildren;

import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.app.ListFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.hdavidzhu.savethechildren.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * Created by casey on 12/2/14.
 */
public class Tutor extends Fragment{
    Context context;

    //this fragment will display the name and then the list of things they need training in

    ListView listview;
    ArrayAdapter<String> adapter;
    List<String> arr;
    String[] items = {"Fake Module 1", "Fake Module 2", "Fake Module 3", "Fake Module 4"};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        View view = inflater.inflate(R.layout.tab, container, false);
//        TextView textview = (TextView) view.findViewById(R.id.text_view);
//        textview.setText("Yeah what up");
        View view = inflater.inflate(R.layout.tutor, container, false);
        //now you must initialize your list view
        ListView listview = (ListView)view.findViewById(R.id.training_list);
        arr = new ArrayList<String>(Arrays.asList(items));
        adapter = new ArrayAdapter<String>(view.getContext(), R.layout.tutor_list_item, arr);
        listview.setAdapter(adapter);

        Button addButton = (Button) view.findViewById(R.id.add_button);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.main_activity_container, new Subject());
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

    public void getSubjects() {
        final RequestQueue queue = Volley.newRequestQueue(getActivity());
        String url = "http://192.168.56.101:3000/subjects";

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
                // TOD O Auto-generated method stub

                // main code on after clicking yes
                arr.remove(deletePosition);
                adapter.notifyDataSetChanged();
                adapter.notifyDataSetInvalidated();

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
