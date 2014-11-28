package com.hdavidzhu.savethechildren;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;

/**
 * Created by casey on 11/28/14.
 */
public class MyTabListener implements ActionBar.TabListener {
    Fragment fragment;

   public MyTabListener(Fragment fragment){
       this.fragment = fragment;
   }

   public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction){
        fragmentTransaction.replace(R.id.fragment_container, fragment);
   }

   public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction){
       fragmentTransaction.remove(fragment);
   }

   public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction){
       //do nothing
   }
}
