package edu.qc.seclass.glm;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class AddItemBySearch extends AppCompatActivity{
    Button button;
    EditText quantity;
    EditText name;
    String item;
    int glposition;
    Item_list newItem;
    GLMDatabase db;
    double que;
    ArrayList<String> filteredList;
    ArrayList<String> empty = new ArrayList<>();
    ArrayList<Integer> c;
    CustAdapter filterAdapter;
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item_by_search);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setTitle("Add Item to List");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        buildRecyclerView();
        db = GLMDatabase.getInstance(MainActivity.context);
        glposition = getIntent().getIntExtra("glposition", -1);
        quantity = (EditText)findViewById(R.id.quantity);
        quantity.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        name = (EditText) findViewById(R.id.name);
        button = (Button)findViewById(R.id.addtolist);
        name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                filter(s.toString());
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(quantity.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(), "Please enter quantity", Toast.LENGTH_LONG).show();
                }else if(filteredList.isEmpty()){
                    Toast.makeText(getApplicationContext(), "no such item", Toast.LENGTH_LONG).show();
                }else{
                    que = Double.parseDouble(quantity.getText().toString());
                    newItem = new Item_list(item, db.getItemType(db.getItemID(item)), que, false);
                    db.addItem(newItem, glposition);
                    // When clicked, jump back to list manager
                    Intent intent = new Intent(AddItemBySearch.this, Lists.class);
                    intent.putExtra("glposition", glposition);
                    startActivity(intent);
                    //finish();
                }
            }
        });
    }

    void filter(String text){
        int count;
        if(!text.equals("")) {
            filteredList = db.getSimilarItem(text);
            c = new ArrayList<>();
            for(int i=0; i<filteredList.size();++i){
                count = 0;
                for(int j=0; j<=i;++j){
                    if(i != j && filteredList.get(j).equals(filteredList.get(i)))
                        ++count;
                }
                c.add(count);
            }
        }else{
            filteredList = empty;
        }
        filterAdapter.filterList(filteredList, c);
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

    public class CustAdapter extends RecyclerView.Adapter<CustAdapter.ViewHolder> {
        private ArrayList<String> filterList;
        private ArrayList<Integer> next;

        public class ViewHolder extends RecyclerView.ViewHolder {
            public TextView itemName, type;

            public ViewHolder(View itemView) {
                super(itemView);
                itemName = itemView.findViewById(R.id.textView);
                type = itemView.findViewById(R.id.textView2);
            }
        }

        public CustAdapter(ArrayList<String> fL) {
            filterList = fL;
            next = null;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.filter_item,
                    parent, false);
            ViewHolder evh = new ViewHolder(v);
            return evh;
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            String currentItem = filterList.get(position);
            ArrayList<String> currentType = db.getItemType(currentItem);
            holder.itemName.setText(currentItem);
            holder.type.setText(currentType.get(next.get(position)));
        }

        @Override
        public int getItemCount() {
            return filterList.size();
        }

        public void filterList(ArrayList<String> filteredList, ArrayList<Integer> c) {
            filterList = filteredList;
            next = c;
            notifyDataSetChanged();
        }
    }

    public interface OnItemListenner{
        void onItemClick(int position);
    }

    private void buildRecyclerView() {
        filteredList = new ArrayList<>();
        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        filterAdapter = new CustAdapter(filteredList);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(filterAdapter);
    }
}
