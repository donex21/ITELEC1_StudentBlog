package com.example.studentlog;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddStudent extends AppCompatActivity {

    EditText IdNum, studFname, studLName;
    Button addStudbtn;
    Db_Operations db_operations;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_student);
        IdNum = findViewById(R.id.idnumber);
        studFname = findViewById(R.id.studentFName);
        studLName = findViewById(R.id.studentLName);
        addStudbtn = findViewById(R.id.addStudentInput);

        db_operations = new Db_Operations(this,"",null,1);

    }

    public void addtodb(View view) {
        try {
            if(IdNum.getText().toString().equals("") ||
                    studFname.getText().toString().equals("") ||
                    studLName.getText().toString().equals("")){
                Toast.makeText(AddStudent.this,"PLs. Complete fill up!!", Toast.LENGTH_SHORT).show();
            }else{
                db_operations.insert_student(IdNum.getText().toString(), studFname.getText().toString(), studLName.getText().toString());
                Toast.makeText(AddStudent.this,"Successfully Added", Toast.LENGTH_SHORT).show();
                IdNum.setText("");
                studFname.setText("");
                studLName.setText("");
            }

        }catch (SQLiteException e){
            Toast.makeText(AddStudent.this,"The ID is Already Exist", Toast.LENGTH_SHORT).show();
        }
    }
}