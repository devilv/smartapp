package com.devil.smartapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

/**
 * Created by devil on 3/22/2017.
 */

public class Todolistactivity extends Activity {
    //FloatingActionButton btn;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.todolistlayout);
       // btn=(FloatingActionButton)findViewById(R.id.button3);

}
public void saveButton(View v){
    String messageText=((EditText)findViewById(R.id.message)).getText().toString();
    if(messageText.equals("")){

    }
    else{
        Intent i=new Intent();
        i.putExtra(Intent_constants.INTENT_MESSAGE_FIELD,messageText);
        setResult(Intent_constants.INTENT_RESULT_CODE,i);
        finish();
    }
}
}
