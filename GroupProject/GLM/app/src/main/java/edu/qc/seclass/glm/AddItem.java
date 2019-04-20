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

//add item to list
public class AddItem extends AppCompatActivity {
    Button button;
    EditText quantity;
    TextView name;
    String item;
    int glposition, position;
    Item_list newItem;
    GLMDatabase db;
    String limitQ;
    double que;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setTitle("Add Item to List");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        db = GLMDatabase.getInstance(MainActivity.context);
        glposition = getIntent().getIntExtra("glposition", -1);
        position = getIntent().getIntExtra("position", -1);
        item = Viewname_class.itemList.get(position);
        button = findViewById(R.id.addtolist);
        name = findViewById(R.id.name);
        name.setText(item);
        quantity = findViewById(R.id.quantity);
        quantity.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //quantity cannot be empty
                if(quantity.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(), "Quantity cannot be empty", Toast.LENGTH_LONG).show();
                }else {
                    que = Double.parseDouble(quantity.getText().toString());
                    if(que <= 0.0) {
                        Toast.makeText(getApplicationContext(), "Quantity must be greater than 0", Toast.LENGTH_LONG).show();
                        return;
                    }
                    newItem = new Item_list(item, db.getItemType(db.getItemID(item)), que, false);
                    db.addItem(newItem, glposition);
                    Intent intent = new Intent(AddItem.this, Lists.class);
                    intent.putExtra("glposition", glposition);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
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
