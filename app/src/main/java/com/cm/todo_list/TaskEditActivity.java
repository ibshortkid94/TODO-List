package com.cm.todo_list;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;


import java.util.ArrayList;

import android.os.Bundle;
import android.widget.TextView;

public class TaskEditActivity extends  AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.taskedit);


        //get data from intent
        Intent edit = getIntent();
        PrimaryTask pt = getIntent().getParcelableExtra("taskObject");

        //populate text fields
        TextView title = (TextView) findViewById(R.id.title);
        title.setText(pt.title);

        TextView desc = (TextView) findViewById((R.id.description));
        desc.setText(pt.description);

        //back
        Button back = (Button) findViewById(R.id.complete);
        back.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //set complete code
            }
        });

        //add subtask
        Button sub = (Button) findViewById(R.id.add_sub);
        back.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //add subtask code
            }
        });
    }
}
