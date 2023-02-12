package com.example.firebase2023;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    FirebaseAuth firebaseAuth;
    Button btnAddMeeting;
    Button btnReg_dialog, btnLogin_dialog;  //dialog buttons
    Button btnRegister_main, btnLogin_main;    //in main the dialog
    EditText etEmail_reg, etPass_reg, etEmail_login, etPass_login;
    Dialog d;
    ProgressDialog progressDialog;
    Button btnAllMeetings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initAll();
        firebaseAuth = FirebaseAuth.getInstance();


    }

    private void initAll() {

        btnAllMeetings = findViewById(R.id.btnAllMeetings);
        btnRegister_main = findViewById(R.id.btnRegister_main);
        btnLogin_main = findViewById(R.id.btnLogin_main);
        btnAddMeeting = findViewById(R.id.btnAddMeeting);

        progressDialog = new ProgressDialog(this);

        btnRegister_main.setOnClickListener(this);
        btnAddMeeting.setOnClickListener(this);
        btnLogin_main.setOnClickListener(this);
        btnAllMeetings.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnAddMeeting:
                Intent intent = new Intent(MainActivity.this,
                        AddMeeting_Screen.class);
                String uid = FirebaseAuth.getInstance().getCurrentUser().getUid().toString();
                intent.putExtra("uid", uid);
                startActivity(intent);
                break;

            case R.id.btnLogin_main:
                createLoginDialog();
                break;
            case R.id.btnReg_dialog:
                createAccount();
                break;
            case R.id.btnAllMeetings:
                //todo
                break;
            case R.id.btnLogin_dialog:
                login();
                break;
            case R.id.btnRegister_main:
                createRegisterDialog();
                break;

        }
    }

    private void login() {
        progressDialog.setMessage("Login please wait...");
        progressDialog.show();

        firebaseAuth.signInWithEmailAndPassword(etEmail_login.getText().toString(),
                etPass_login.getText().toString()).addOnCompleteListener(this,
                new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(MainActivity.this,
                                    "auth success", Toast.LENGTH_SHORT).show();
                            btnLogin_main.setText("Logout");
                            btnAllMeetings.setVisibility(View.VISIBLE);
                            btnAddMeeting.setVisibility(View.VISIBLE);
                        }else {
                            Toast.makeText(MainActivity.this,
                                    "not ok!", Toast.LENGTH_SHORT).show();
                        }
                        d.dismiss();
                        progressDialog.dismiss();
                    }
                });
    }

    private void createAccount() {
        progressDialog.setMessage("Registering please wait...");
        progressDialog.show();

        firebaseAuth.createUserWithEmailAndPassword(etEmail_reg.getText().toString(),
                etPass_reg.getText().toString()).addOnCompleteListener(this,
                new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(MainActivity.this,
                                    "Register was ok!", Toast.LENGTH_SHORT).show();
                            btnLogin_main.setText("Logout");
                            btnAllMeetings.setVisibility(View.VISIBLE);
                            btnAddMeeting.setVisibility(View.VISIBLE);
                        }else{
                            Toast.makeText(MainActivity.this,
                                    "Register was not ok!", Toast.LENGTH_SHORT).show();

                        }
                        d.dismiss();
                        progressDialog.dismiss();
                    }
                });

    }

    private void createRegisterDialog() {
        d= new Dialog(this);
        d.setContentView(R.layout.register_layout);
        d.setTitle("Register");
        d.setCancelable(true);
        etEmail_reg = d.findViewById(R.id.etEmail_reg);
        etPass_reg = d.findViewById(R.id.etPass_reg);
        btnReg_dialog = d.findViewById(R.id.btnReg_dialog);
        btnReg_dialog.setOnClickListener(this);
        d.show();
    }

    private void createLoginDialog() {
        d= new Dialog(this);
        d.setContentView(R.layout.login_layout);
        d.setTitle("Login");
        d.setCancelable(true);
        etEmail_login =  d.findViewById(R.id.etEmail_login);
        etPass_login =  d.findViewById(R.id.etPass_login);
        btnLogin_dialog =  d.findViewById(R.id.btnLogin_dialog);
        btnLogin_dialog.setOnClickListener(this);
        d.show();
    }
}