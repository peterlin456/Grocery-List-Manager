package edu.qc.seclass.glm;

import android.app.ListActivity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;

//add new list
public class AddList extends AppCompatActivity{
    private Button button;
    private EditText name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setTitle("Create new Item");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        button = findViewById(R.id.addlistbutton);
        name = findViewById(R.id.listNameText);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //list name cannot be ""
                if(name.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(), "Name cannot be empty", Toast.LENGTH_LONG).show();
                }else {
                    String nameValue = name.getText().toString();
                    GLMDatabase db = GLMDatabase.getInstance(MainActivity.context);
                    int glID = db.addList(nameValue);
                    MainActivity.list.add(new Grocery_list(nameValue, false, glID));
                    // When clicked, jump back to list manager
                    finish();
                }
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                onBackPressed();
                return true;
            default:return super.onOptionsItemSelected(item);
        }
    }
}
