package com.example.studentlog;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class UpdateStudentActivity extends AppCompatActivity {

    Button updatebtn;
    TextView search;
    EditText idnum, fname, lname;
    Db_Operations db_operations;
   // ViewStudentActivity viewStudentActivity;
    //List<String> idHolder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_student);

        //viewStudentActivity = new ViewStudentActivity();
        db_operations = new Db_Operations(this,"",null,1);
        updatebtn = findViewById(R.id.updatebtn);
        search = findViewById(R.id.search);
        idnum = findViewById(R.id.updateId);
        fname = findViewById(R.id.updateFName);
        lname =findViewById(R.id.updateLName);


    }

    public void updatedb(View view) {
        if((!db_operations.SearchID(idnum.getText().toString()))
                || idnum.getText().toString().equals("")
                || fname.getText().toString().equals("")
                || lname.getText().toString().equals(""))
        {
            Toast.makeText(UpdateStudentActivity.this,"Pls Complete Details", Toast.LENGTH_SHORT).show();
        }else{
            db_operations.update_students(idnum.getText().toString(), fname.getText().toString(), lname.getText().toString());
            Toast.makeText(UpdateStudentActivity.this,"Succesfully Updated", Toast.LENGTH_SHORT).show();
            idnum.setText("");
            fname.setText("");
            lname.setText("");
        }
    }

    public void searchId(View view) {
        if(db_operations.SearchID(idnum.getText().toString())){
                AlertDialog.Builder dialog = new AlertDialog.Builder(UpdateStudentActivity.this);
                dialog.setTitle("ID FOUND");
                dialog.setCancelable(true);
                dialog.setMessage("Enter new Firstname and Lastname");
                dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
            AlertDialog alertmsg = dialog.create();
            alertmsg.show();
        }else{
            Toast.makeText(UpdateStudentActivity.this,"This Id not Exist", Toast.LENGTH_SHORT).show();
        }

    }


}