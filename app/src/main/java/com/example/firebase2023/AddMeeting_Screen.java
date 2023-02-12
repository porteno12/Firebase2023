package com.example.firebase2023;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddMeeting_Screen extends AppCompatActivity {
    String uid="";
    Intent intent;
    Button btn_addMeeting_add;
    EditText et_add_hour, et_add_day;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference meetingsRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_meeting_screen);

        firebaseDatabase = FirebaseDatabase.getInstance();

        et_add_hour = findViewById(R.id.et_add_hour);
        et_add_day = findViewById(R.id.et_add_day);
        btn_addMeeting_add = findViewById(R.id.btn_addMeeting_add);
        btn_addMeeting_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Meeting mt = new Meeting( et_add_day.getText().toString(),
                        et_add_hour.getText().toString(), uid);
                meetingsRef = firebaseDatabase.getReference("Meetings").push();
                mt.setIdMeeting(meetingsRef.getKey());
                meetingsRef.setValue(mt);
            }
        });
        intent = getIntent();
        uid = intent.getStringExtra("uid");
        Log.d("tag", uid);
    }
}