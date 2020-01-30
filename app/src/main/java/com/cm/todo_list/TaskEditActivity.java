package com.cm.todo_list;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SearchRecentSuggestionsProvider;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;

import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class TaskEditActivity extends  AppCompatActivity{

    private ListView lv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.taskedit);


        //get data from intent
        Intent edit = getIntent();
        final PrimaryTask pt = getIntent().getParcelableExtra("taskObject");
        ArrayList items = pt.subTask;

        lv = (ListView) findViewById(R.id.subtasks);

        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                items);

        lv.setAdapter(arrayAdapter);


        //populate text fields
        TextView title = (TextView) findViewById(R.id.title);
        title.setText(pt.title);

        TextView desc = (TextView) findViewById((R.id.description));
        desc.setText(pt.description);

        //set complete


        //add subtask
        Button sub = (Button) findViewById(R.id.add_sub);
        sub.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //add subtask code
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(TaskEditActivity.this);
                alertDialog.setTitle("Add Item");
                alertDialog.setMessage("Add a Subtask");

                final EditText input = new EditText(TaskEditActivity.this);
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.MATCH_PARENT);
                input.setLayoutParams(lp);
                alertDialog.setView(input);


                alertDialog.setPositiveButton("Confirm",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String text = input.getText().toString();
                                if (text != ""){
                                    pt.addSub(text);
                                    arrayAdapter.notifyDataSetChanged();
                                    arrayAdapter.notifyDataSetInvalidated();
                                }

                            }
                        });

                alertDialog.setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });
                alertDialog.show();

            }
        });

        //back button
    }
}
