package edu.qc.seclass.glm;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class Lists extends AppCompatActivity {

    ListView listView;
    GLMDatabase db;
    CheckBox checkAll;
    int glposition = -1;
    public static ArrayList<Item_list> itemList = null;
    CustAdapter itemAdapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listview);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        db = GLMDatabase.getInstance(getApplicationContext());
        listView = (ListView) findViewById(R.id.item_view);
        checkAll = (CheckBox) findViewById(R.id.checkallname);
        glposition = getIntent().getIntExtra("glposition", -1);
        itemList = db.getItemList(glposition);
        if(itemList == null){
            for (Item_list item : itemList) {
                item.setSelected(false);
            }
        }
        setTitle(db.getListName(glposition));
        itemAdapter = new CustAdapter(this, android.R.layout.simple_list_item_1, itemList);
        listView.setAdapter(itemAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // When clicked, jump to the list actiivity
                Intent intent = new Intent(Lists.this, EditItem.class);
                intent.putExtra("glposition", glposition);
                intent.putExtra("itemName", position);
                intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent);
                itemList = db.getItemList(glposition);
                itemAdapter.notifyDataSetChanged();
            }
        });
        checkAll.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isSelected) {
                if (itemList != null){
                    for (Item_list item : itemList){
                        item.setSelected(isSelected);
                        db.setSelected(glposition, item);
                    }
                    listView.setAdapter(itemAdapter);
                }
            }
        });
        runOnUiThread(new Runnable() {
            public void run() {
                itemAdapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.itemlist , menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent;
        switch (item.getItemId()){
            case android.R.id.home:
                intent = new Intent(this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                return true;
            case R.id.searchitemtype:
                intent = new Intent(this, AdditemByType.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                intent.putExtra("glposition", glposition);
                startActivity(intent);
                itemList = db.getItemList(glposition);
                itemAdapter.notifyDataSetChanged();
                return true;
            case R.id.additem:
                intent = new Intent(this, AddItem.class);
                startActivity(intent);
                return true;
            case R.id.deletel:
                return true;
            default:return super.onOptionsItemSelected(item);
        }
    }

    public class CustAdapter extends ArrayAdapter<Item_list> {
        ArrayList<Item_list> ilist;
        public CustAdapter(Context context, int textViewResourceId, ArrayList<Item_list> list) {
            super(context, textViewResourceId, list);
        }

        public class ListHolder{
            TextView iName;
            TextView quant;
            CheckBox checkBox;
        }

        public View getView(final int position, View convertView, ViewGroup parent) {

            Lists.CustAdapter.ListHolder holder;
            Log.v("ConvertView", String.valueOf(position));
            this.ilist = new ArrayList<>();
            this.ilist.addAll(itemList);


            if (convertView == null) {
                LayoutInflater vi = (LayoutInflater)getSystemService(
                        Context.LAYOUT_INFLATER_SERVICE);
                convertView = vi.inflate(R.layout.item_row, null);

                holder = new Lists.CustAdapter.ListHolder();
                holder.iName = (TextView) convertView.findViewById(R.id.name);
                holder.quant = (TextView)  convertView.findViewById(R.id.quant);
                holder.checkBox = (CheckBox) convertView.findViewById(R.id.checkBox);
                convertView.setTag(holder);

                holder.checkBox.setOnClickListener( new View.OnClickListener() {
                    public void onClick(View v) {
                        CheckBox cb = (CheckBox) v ;
                        Item_list l = itemList.get(position);
                        l.setSelected(cb.isChecked());
                        db.setSelected(glposition, l);
                    }
                });
            }
            else {
                holder = (Lists.CustAdapter.ListHolder) convertView.getTag();
            }

            Item_list l = itemList.get(position);
            holder.iName.setText(l.getName());
            holder.quant.setText(l.getQuant());
            holder.checkBox.setChecked(l.isSelected());
            holder.checkBox.setTag(itemList);
            return convertView;
        }
    }
}
