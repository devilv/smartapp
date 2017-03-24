package com.devil.smartapp;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by devil on 3/22/2017.
 */

public class Groupchatfragment extends Fragment {
    public static Groupchatfragment newInstance() {
        Groupchatfragment fragment = new Groupchatfragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.groupchat, container, false);
    }
}
