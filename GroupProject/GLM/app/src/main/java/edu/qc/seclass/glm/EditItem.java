package edu.qc.seclass.glm;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

//edit item quantity
public class EditItem extends AppCompatActivity {
    Button button;
    EditText quantity;
    TextView name;
    Item_list item;
    int glposition, position;
    GLMDatabase db;
    double que;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_item);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setTitle("Edit item");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        db = GLMDatabase.getInstance(MainActivity.context);
        glposition = getIntent().getIntExtra("glposition", -1);
        position = getIntent().getIntExtra("position", -1);
        item = Lists.itemList.get(position);
        button = findViewById(R.id.confirm);
        name = findViewById(R.id.name);
        name.setText(item.getName());
        quantity = findViewById(R.id.quantity);
        quantity.setText(item.getQuant());
        quantity.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //quantity cannot be empty
                if(quantity.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(), "Please enter quantity", Toast.LENGTH_LONG).show();
                }else {
                    que = Double.parseDouble(quantity.getText().toString());
                    item.setQuant(que);
                    db.editListItem(item, glposition);
                    // When clicked, jump back to list manager
                    Intent intent = new Intent(EditItem.this, Lists.class);
                    intent.putExtra("glposition", glposition);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                    //finish();
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
