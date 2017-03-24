package com.devil.smartapp;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by devil on 3/22/2017.
 */

public class Todolistfragment extends Fragment implements View.OnClickListener {
    ListView lst;
    FloatingActionButton btn;
    ArrayList<String>arrayList;
    ArrayAdapter<String>arrayAdapter;
    String messageText;
    public static Todolistfragment newInstance() {
        Todolistfragment fragment = new Todolistfragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.todolist, container, false);
        btn=(FloatingActionButton)view.findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getActivity().getApplication(),Todolistactivity.class);
                startActivityForResult(i,Intent_constants.INTENT_REQUEST_CODE);
            }
        });
        lst=(ListView)view.findViewById(R.id.listview);
        arrayList=new ArrayList<>();
        arrayAdapter=new ArrayAdapter<String>(getActivity().getApplicationContext(),android.R.layout.simple_list_item_1,arrayList);
        lst.setAdapter(arrayAdapter);
        lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i=new Intent();
                i.setClass(getActivity().getApplication(),EditMessageClass.class);
                i.putExtra(Intent_constants.INTENT_MESSAGE_DATA,arrayList.get(position).toString());
                i.putExtra(Intent_constants.INTENT_ITEM_POSITION,position);
                startActivityForResult(i,Intent_constants.INTENT_REQUEST_CODE_TWO);
            }
        });
        return view;
    }

    @Override
    public void onClick(View v) {
    }
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        if(resultCode==Intent_constants.INTENT_REQUEST_CODE){
            messageText=data.getStringExtra(Intent_constants.INTENT_MESSAGE_FIELD);
            arrayList.add(messageText);
            arrayAdapter.notifyDataSetChanged();
        }
    }
}
