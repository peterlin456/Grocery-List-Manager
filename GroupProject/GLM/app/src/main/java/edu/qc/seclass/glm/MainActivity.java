package edu.qc.seclass.glm;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ListView;
import android.support.v7.app.ActionBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    CheckBox selectAll;
	public static ArrayList<Grocery_list> list;
    public static Context context;
	CustAdapter listAdapter;
	GLMDatabase db;
	Grocery_list renamedList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db = GLMDatabase.getInstance(getApplicationContext());
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        list = db.getList();
        listView = (ListView) findViewById(R.id.listView);
        selectAll = (CheckBox) findViewById(R.id.Selectall);
        setTitle("List Manager");
		listAdapter = new CustAdapter(this, android.R.layout.simple_list_item_1, list);
        listView.setAdapter(listAdapter);
        selectAll.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (list != null){
                    for (Grocery_list glist : list){
                        glist.setSelected(isChecked);
                    }
                    listView.setAdapter(listAdapter);
                }
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // When clicked, jump to the list actiivity
                Intent intent = new Intent(MainActivity.this, Lists.class);
                intent.putExtra("glposition", position);
                intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent);
            }
        });
        runOnUiThread(new Runnable() {
            public void run() {
                listAdapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.mainlist , menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent;
        int numSelected = 0;
        switch (item.getItemId()){
            case R.id.addlist:
                intent = new Intent(this, AddList.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent);
                return true;
            case R.id.renamelist:
                for (Grocery_list list : list){
                    if (list.isSelected()){
                        if (numSelected > 1){
                            Context context = getApplicationContext();
                            CharSequence text = "Only one list can be selected for renaming";
                            int duration = Toast.LENGTH_SHORT;
                            Toast toast = Toast.makeText(context, text, duration);
                            toast.show();
                            return true;
                        } else {
                            ++numSelected;
                        }
                    }
                }
                if (numSelected <= 0) {
                    Toast toast = Toast.makeText(getApplicationContext(), "Please select one list to name", Toast.LENGTH_LONG);
                    toast.show();
                }else {
                    intent = new Intent(this, RenameList.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                    startActivity(intent);
                    listAdapter.notifyDataSetChanged();
                }
                return true;
            case R.id.deletelist:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Delete Selected Lists");
                boolean toDelete = false;
                for(Grocery_list list : list){
                    if (list.isSelected()){
                        toDelete = true;
                    }
                }
                if (!toDelete){
                    Toast toast = Toast.makeText(getApplicationContext(), "Select at least one list for deletion", Toast.LENGTH_LONG);
                    toast.show();
                    return true;
                }
                else {
                    builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            for (int i = list.size() - 1; i >= 0; i--) {
                                if (list.get(i).isSelected()) {
                                    db.removeList(list.get(i).getName());
                                    list.remove(i);
                                    ArrayList<Grocery_list> text = db.getList();
                                    for(Grocery_list x:text){
                                        System.out.println(x.getName() + ":" + db.getListID(x.getName()));
                                    }
                                    for(String d:db.getItemList()){
                                        System.out.println(d);
                                    }
                                }
                            }
                            listAdapter.notifyDataSetChanged();
                        }
                    });
                    builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
                    builder.show();
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    public class CustAdapter extends ArrayAdapter<Grocery_list>{
        ArrayList<Grocery_list> glist;
        public CustAdapter(Context context, int textViewResourceId, ArrayList<Grocery_list> list) {
            super(context, textViewResourceId, list);
        }

        public class ListHolder{
            TextView listName;
            CheckBox checkBox;
        }

        public View getView(final int position, View convertView, ViewGroup parent) {

            ListHolder holder;
            Log.v("ConvertView", String.valueOf(position));
            this.glist = new ArrayList<Grocery_list>();
            this.glist.addAll(list);


            if (convertView == null) {
                LayoutInflater vi = (LayoutInflater)getSystemService(
                        Context.LAYOUT_INFLATER_SERVICE);
                convertView = vi.inflate(R.layout.list_row, null);

                holder = new ListHolder();
                holder.listName = (TextView) convertView.findViewById(R.id.name);
                holder.checkBox = (CheckBox) convertView.findViewById(R.id.checkBox);
                convertView.setTag(holder);

                holder.checkBox.setOnClickListener( new View.OnClickListener() {
                    public void onClick(View v) {
                        CheckBox cb = (CheckBox) v ;

                        Grocery_list l = list.get(position);

                        l.setSelected(cb.isChecked());
                    }
                });
            }
            else {
                holder = (ListHolder) convertView.getTag();
            }

            Grocery_list l = list.get(position);

            holder.listName.setText(l.getName());
            holder.checkBox.setChecked(l.isSelected());
            holder.checkBox.setTag(list);
            return convertView;

        }
    }
}
