package com.devil.smartapp;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.view.MenuItem;

/**
 * Created by devil on 3/9/2017.
 */

public class Bottomlayout extends Activity{
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bottomlayout);
        BottomNavigationView bottomNavigationView = (BottomNavigationView)
                findViewById(R.id.bottom_navigation);

        bottomNavigationView.setOnNavigationItemSelectedListener
                (new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        Fragment selectedFragment = null;
                        switch (item.getItemId()) {
                            case R.id.action_home:
                                selectedFragment = Homefragment.newInstance();
                                break;
                            case R.id.action_groupchat:
                                selectedFragment = Groupchatfragment.newInstance();
                                //Toast.makeText(Bottomlayout.this,"hello",Toast.LENGTH_LONG).show();
                                break;
                            case R.id.action_profile:
                                selectedFragment = Todolistfragment.newInstance();
                                //selectedFragment = ItemThreeFragment.newInstance();
                                break;
                            case R.id.action_student:

                                break;
                        }
                        FragmentTransaction transaction = getFragmentManager().beginTransaction();
                        transaction.replace(R.id.frame_layout, selectedFragment);
                        transaction.commit();
                        return true;
                    }
                });

        //Manually displaying the first fragment - one time only
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_layout, Homefragment.newInstance());
        transaction.commit();

        //Used to select an item programmatically
        bottomNavigationView.getMenu().getItem(2).setChecked(true);
    }
}