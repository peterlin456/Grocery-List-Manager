package edu.qc.seclass.glm;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

//rename list
public class RenameList extends AppCompatActivity {
    Button button;
    EditText name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rename_list);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setTitle("Rename list");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        name = findViewById(R.id.listNameText);
        button = findViewById(R.id.renamebutton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //name cannot be ""
                if(name.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(), "Name cannot be empty", Toast.LENGTH_LONG).show();
                }else {
                    String nameValue = name.getText().toString();
                    long glID = -1;
                    GLMDatabase db = GLMDatabase.getInstance(MainActivity.context);
                    for (Grocery_list list : MainActivity.list) {
                        if (list.isSelected()) {
                            glID = list.getID();
                            break;
                        }
                    }
                    db.renameList(glID, nameValue);
                    MainActivity.list = db.getList();
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
