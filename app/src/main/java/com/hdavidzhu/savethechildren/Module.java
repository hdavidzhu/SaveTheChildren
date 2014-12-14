package com.hdavidzhu.savethechildren;

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

import java.util.List;

public class Module extends Fragment {

    public String[] items;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.training_module, container, false);
        final ListView listview =(ListView)view.findViewById(R.id.modules_listview);

        // final String[] items = new String[] {"Fake Module One", "Fake Module Two", "Fake Module Three", "Fake Module Four", "Fake Module Five"};

        Bundle bundle = this.getArguments();
        String mySubject = bundle.getString("subject");
        String myGrade = bundle.getString("grade");

        VolleySingleton.getInstance().getClassModules(mySubject, myGrade, new ClassModuleCallback() {
            @Override
            public void handle(List<String> classModules) {
                items = classModules.toArray(new String[classModules.size()]);
                ArrayAdapter<String> adapter =
                        new ArrayAdapter<String>(view.getContext(), R.layout.module_list_item, items);
                listview.setAdapter(adapter);
            }
        });

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                MainActivity.tutorItems.add(items[i]);
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.main_activity_container,new Tutor());
                ft.addToBackStack(null);
                ft.commit();
            }
        });
        return view;
    }
}