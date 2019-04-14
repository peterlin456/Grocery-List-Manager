package edu.qc.seclass.glm;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class Viewname_class extends AppCompatActivity {
    CustAdapter itemAdapter = null;
    public static ArrayList<String> itemList = null;
    ListView listView;
    int glposition = -1;
    String itemType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.itemnameview);
        listView = (ListView) findViewById(R.id.itemList);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Intent intent = getIntent();
        glposition = intent.getIntExtra("glposition", -1);
        itemType = intent.getStringExtra("itemType");
        //String selSearchName = intent.getStringExtra("selSearchName");
        if (itemType != null){
            setTitle(itemType);
            searchItemByType(itemType);
        }/*
        else if (selSearchName != null){
            setTitle(selSearchName);
            displayItemNamesFromSearch(selSearchName);
        }*/
    }

    void searchItemByType(String itemType){
        itemList = new ArrayList<>();

        GLMDatabase db = GLMDatabase.getInstance(getApplicationContext());
        itemList = db.getItemsByType(itemType);

        itemAdapter = new CustAdapter(this, android.R.layout.simple_list_item_1, itemList);
        listView.setAdapter(itemAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // When clicked, jump to the list actiivity
                Intent intent = new Intent(Viewname_class.this, AddItem.class);
                intent.putExtra("position", position);
                intent.putExtra("glposition", glposition);
                intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent);
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

    private class CustAdapter extends ArrayAdapter<String> {
        public CustAdapter(Context context, int textViewResourceId, ArrayList<String> itemTypes) {
            super(context, textViewResourceId, itemTypes);
        }
    }
}
