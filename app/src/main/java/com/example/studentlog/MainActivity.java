package com.example.studentlog;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button addbtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addbtn = findViewById(R.id.add_student);
    }

    public void gotoadd(View view) {
        Intent addstdnt = new Intent(MainActivity.this, AddStudent.class);
        startActivity(addstdnt);
    }

    public void gotoView(View view) {
        Intent viewstud = new Intent(MainActivity.this, ViewStudentActivity.class);
        startActivity(viewstud);
    }

    public void gotoUpdate(View view) {
        Intent updatestud = new Intent(MainActivity.this, UpdateStudentActivity.class);
        startActivity(updatestud);
    }
}