package com.hdavidzhu.savethechildren;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.hdavidzhu.savethechildren.callbacks.ClassModuleCallback;

import org.json.JSONObject;

import java.util.List;

public class Module extends Fragment {
    MainActivity activity;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.activity = (MainActivity) activity;
    }

    public String[] items;

    // The onCreate method for this class takes in the Layout, the View, and the Bundle that contains variables we need
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.training_module, container, false);
        final ListView listview =(ListView)view.findViewById(R.id.modules_listview);

        Bundle bundle = this.getArguments();
        String mySubject = bundle.getString("subject");
        String myGrade = bundle.getString("grade");

        //Get instance of volley singleton and the getClassModules method that it defines
        VolleySingleton.getInstance().getClassModules(mySubject, myGrade, new ClassModuleCallback() {
            //When VolleySingleton calls callback.handle this method can process the list that is passed through the callback
            @Override
            public void handle(List<String> classModules) {
                items = classModules.toArray(new String[classModules.size()]);
                ArrayAdapter<String> adapter =
                        new ArrayAdapter<String>(view.getContext(), R.layout.module_list_item, items);
                listview.setAdapter(adapter);
            }

            @Override
            public void send(String classModule) {}
        });

        // This onClick listener takes in the adapter view and changes the fragment to the fragment that the user clicks on
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                MainActivity.tutorItems.add(items[i]);

                // TODO Replace these dummy data.
                String teacher = "Dummy Teacher";

                VolleySingleton.getInstance().setTutorItem(MainActivity.curTutor.name, items[i]);

                activity.goBackToTutor();

                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.main_activity_container, MainActivity.curTutor);
                ft.addToBackStack(null);
                ft.commit();
            }
        });
        return view;
    }
}