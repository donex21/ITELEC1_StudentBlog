package com.example.studentlog;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ViewStudentActivity extends AppCompatActivity {

    ListView listView;
    ArrayAdapter arrayAdapter;
    Db_Operations db_operations;
    List<String> liststud;
    List<String> idholder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_student);
        db_operations = new Db_Operations(this,"",null,1);
        liststud =  new ArrayList<String>();
        idholder = new ArrayList<>();
        listView = findViewById(R.id.listview);

        dataView();
        listView.setAdapter(arrayAdapter);

        deletedata();
        //arrayAdapter = new ArrayAdapter(this, R.layout.student_list, liststud);

    }

    private void deletedata() {
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                final int delItem = position;

                new AlertDialog.Builder(ViewStudentActivity.this)
                        .setIcon(R.drawable.clear_foreground)
                        .setTitle("Are You Sure")
                        .setMessage("Do you want to Delete this Post")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                liststud.remove(delItem);
                                arrayAdapter.notifyDataSetChanged();
                            }
                        }).setNegativeButton("No", null)
                        .show();
                db_operations.delete_student(idholder.get(delItem));
                return true;
            }
        });

    }


    public List<String> getIdholder(){
        return idholder;
    }

    private void dataView() {
        Cursor cursor = db_operations.viewAllStudent();

        if(cursor.getCount() == 0){
            Toast.makeText(ViewStudentActivity.this, "No data",Toast.LENGTH_LONG).show();
        }else{
            while(cursor.moveToNext()) {
                idholder.add(cursor.getString(1));
                liststud.add(cursor.getString(1) + " - " + cursor.getString(2) + " " +
                        cursor.getString(3));
            }
            arrayAdapter = new ArrayAdapter(this, R.layout.student_list, liststud);

            //arrayAdapter.notifyDataSetChanged();
        }
    }

}