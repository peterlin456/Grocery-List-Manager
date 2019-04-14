package edu.qc.seclass.glm;

import android.app.ListActivity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;

public class AddList extends AppCompatActivity{
    Button button;
    EditText name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_list);
        button = (Button)findViewById(R.id.addlistbutton);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = (EditText)findViewById(R.id.listNameText);
                String nameValue = name.getText().toString();
                GLMDatabase db = GLMDatabase.getInstance(MainActivity.context);
                db.addList(nameValue);
                MainActivity.list.add(new Grocery_list(nameValue, false));
                // When clicked, jump back to list manager
                finish();
            }
        });
    }
}
