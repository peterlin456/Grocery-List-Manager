package edu.qc.seclass.glm;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

//create new item
public class CreateItem extends AppCompatActivity {
    EditText name, quantity;
    Spinner types;
    int glposition = -1;
    String newName = "";
    Button button;
    GLMDatabase db = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_item);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setTitle("Create new Item");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        db = GLMDatabase.getInstance(MainActivity.context);
        glposition = getIntent().getIntExtra("glposition", -1);
        newName = getIntent().getStringExtra("name");
        name = findViewById(R.id.nameText);
        quantity = findViewById(R.id.quantityText);
        quantity.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        button = findViewById(R.id.button);
        addTypeOnSpinner();
        name.setText(newName);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nameV = name.getText().toString();
                String typeV = types.getSelectedItem().toString();
                //quantity cannot be empty
                if (quantity.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(), "Enter quantity", Toast.LENGTH_LONG).show();
                    return;
                }
                Double quant = Double.parseDouble(quantity.getText().toString());
                Item_list item = new Item_list(nameV, typeV,quant,false);
                //if item already in database dont create it
                if(isNewItem(item))
                    db.insertItem(item);
                //but still add it into list
                db.addItem(item, glposition);
                Intent intent = new Intent(CreateItem.this, Lists.class);
                intent.putExtra("glposition", glposition);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });
    }

    //check if the item already in database
    boolean isNewItem(Item_list item){
        ArrayList<String> itemL = db.getItemsByType(item.getType());
        for(String i: itemL){
            if(i.toLowerCase().equals(item.getName().toLowerCase()))
                return false;
        }
        return true;
    }

    //shows all types
    public void addTypeOnSpinner() {
        types = findViewById(R.id.typeSpi);
        ArrayList<String> itemTypes = db.getTypes();
        ArrayAdapter<String> dataAdapterType = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, itemTypes);
        dataAdapterType.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        types.setAdapter(dataAdapterType);
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
