package edu.qc.seclass.glm;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class RenameList extends AppCompatActivity {
    Button button;
    EditText name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rename_list);
        button = (Button)findViewById(R.id.renamebutton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = (EditText)findViewById(R.id.listNameText);
                String nameValue = name.getText().toString();
                String changeName = "";
                GLMDatabase db = GLMDatabase.getInstance(MainActivity.context);
                for(Grocery_list list : MainActivity.list){
                    if(list.isSelected()){
                        changeName = list.getName();
                        break;
                    }
                }
                db.renameList(changeName, nameValue);
                MainActivity.list = db.getList();
                // When clicked, jump back to list manager
                finish();
            }
        });
    }
}
