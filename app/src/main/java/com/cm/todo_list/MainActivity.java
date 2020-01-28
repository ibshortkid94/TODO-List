 package com.cm.todo_list;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;


import java.util.ArrayList;

 public class MainActivity extends AppCompatActivity {
     private ListView lv;

     final ArrayList <PrimaryTask> pt = new ArrayList<>();
     ArrayList items = new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        lv = (ListView) findViewById(R.id.list);

        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                items );

        lv.setAdapter(arrayAdapter);


        FloatingActionButton add = (FloatingActionButton) findViewById(R.id.addBttn);
        add.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);
                alertDialog.setTitle("Add Item");
                alertDialog.setMessage("Add something to the list");

                final EditText input = new EditText(MainActivity.this);
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
                                    items.add(text);
                                    PrimaryTask t = new PrimaryTask();
                                    t.setTitle(text);
                                    pt.add(t);
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

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, final int pos, long id) {

                AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);
                alertDialog.setTitle("Delete");
                alertDialog.setMessage("Really Delete?");

                final int position = pos;

                Intent edit = new Intent(MainActivity.this, TaskEditActivity.class);
                edit.putExtra("taskObject", pt.get(position));
                startActivity(edit);
            }
        });

        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> arg0, View arg1, final int pos, long id) {

                AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);
                alertDialog.setTitle("Delete");
                alertDialog.setMessage("Really Delete?");

                final int position = pos;

                alertDialog.setPositiveButton("Yes",
                      new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            items.remove(position);
                            pt.remove(position);
                            arrayAdapter.notifyDataSetChanged();
                            arrayAdapter.notifyDataSetInvalidated();
                }
                });

                alertDialog.setNegativeButton("No",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });
                alertDialog.show();
                return true;
            }
        });

    }
}
