package com.devil.smartapp;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * Created by devil on 3/9/2017.
 */
public class HomeActivity extends AppCompatActivity implements View.OnClickListener {
    EditText user,pass;
    Button login;
    TextView reg,forget;
    private ProgressDialog progressBar;
    private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        firebaseAuth=FirebaseAuth.getInstance();
        forget=(TextView)findViewById(R.id.textView4);
        reg=(TextView)findViewById(R.id.textView11);
        login=(Button)findViewById(R.id.button2);
        pass=(EditText)findViewById(R.id.editText4);
        user=(EditText)findViewById(R.id.editText3);
        progressBar=new ProgressDialog(this);
        if(firebaseAuth.getCurrentUser() !=null){
            Intent i=new Intent(HomeActivity.this,Bottomlayout.class);
            startActivity(i);
            finish();
        }
            login.setOnClickListener(this);
            reg.setOnClickListener(this);

        forget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog d=new Dialog(HomeActivity.this);
                d.setTitle("Forget Password");
                d.setContentView(R.layout.forgotpassword);
                d.show();
                final EditText edt=(EditText)d.findViewById(R.id.editText);
                Button button=(Button)d.findViewById(R.id.button);
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        String email=edt.getText().toString();
                        if(TextUtils.isEmpty(email)){
                            new SweetAlertDialog(HomeActivity.this)
                                    .setTitleText("Missing params!")
                                    .setContentText("Enter email address")
                                    .show();
                            return;
                        }
                        firebaseAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if(task.isSuccessful()){
                                    new SweetAlertDialog(HomeActivity.this, SweetAlertDialog.SUCCESS_TYPE)
                                            .setTitleText("Recovery!")
                                            .setContentText("a mail has been sent to ur account!")
                                            .show();
                                    return;
                                }
                                else {
                                    new SweetAlertDialog(HomeActivity.this, SweetAlertDialog.WARNING_TYPE)
                                            .setTitleText("Oops...")
                                            .setContentText("Something went wrong!")
                                            .show();
                                    return;
                                }
                            }
                        });
                    }
                });
            }
        });
    }
    private void userLogin(){
        String email=user.getText().toString().trim();
        String password=pass.getText().toString().trim();
        if(TextUtils.isEmpty(email)){
            new SweetAlertDialog(HomeActivity.this)
                    .setTitleText("Missing params!")
                    .setContentText("Enter email address")
                    .show();
            return;
        }
        if(TextUtils.isEmpty(password)){
            new SweetAlertDialog(HomeActivity.this)
                    .setTitleText("Missing params!")
                    .setContentText("Enter password")
                    .show();
                return;
        }
        progressBar.setMessage("loging please wait..");
        progressBar.show();
        firebaseAuth.signInWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressBar.dismiss();
                        if(task.isSuccessful()){
                           finish();
                            Intent i=new Intent(HomeActivity.this,Bottomlayout.class);
                            startActivity(i);
                        }
                        else{
                            new SweetAlertDialog(HomeActivity.this, SweetAlertDialog.ERROR_TYPE)
                                    .setTitleText("Something went wromg...")
                                    .setContentText("either password or email is wrong")
                                    .show();
                        }
                    }
                });
    }
    private void registerUser(){
        String email=user.getText().toString().trim();
        String password=pass.getText().toString().trim();
        if(TextUtils.isEmpty(email)){
            new SweetAlertDialog(HomeActivity.this)
                    .setTitleText("Missing params!")
                    .setContentText("Enter email address")
                    .show();
            return;
        }
        if(TextUtils.isEmpty(password)){
            new SweetAlertDialog(HomeActivity.this)
                    .setTitleText("Missing params!")
                    .setContentText("Enter password")
                    .show();
            return;
                   }
        progressBar.setMessage("Registering user");
        progressBar.show();
        firebaseAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            new SweetAlertDialog(HomeActivity.this, SweetAlertDialog.SUCCESS_TYPE)
                                    .setTitleText("Sucess...")
                                    .setContentText("sucessfully registered")
                                    .show();

                        }
                        else
                        {
                            new SweetAlertDialog(HomeActivity.this, SweetAlertDialog.ERROR_TYPE)
                                    .setTitleText("Oops...")
                                    .setContentText("Something went wrong!")
                                    .show();
                        }
                    }
                });
    }

    @Override
    public void onClick(View v) {
    if(v == reg){
        registerUser();
    }
    if(v == login){
        userLogin();
    }
    }
}