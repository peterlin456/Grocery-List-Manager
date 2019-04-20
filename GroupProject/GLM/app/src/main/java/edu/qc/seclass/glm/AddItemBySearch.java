package edu.qc.seclass.glm;

import android.content.Context;
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
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

//add item by searching
public class AddItemBySearch extends AppCompatActivity{
    Button button;
    EditText quantity;
    EditText name;
    int glposition;
    Item_list newItem;
    GLMDatabase db;
    double que;
    ArrayList<String> filteredList;
    ArrayList<String> filteredType;
    ArrayList<String> empty = new ArrayList<>();
    ArrayList<Integer> c;
    CustAdapter filterAdapter;
    String selectedName = "", selectedType = "";
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    //recyclerview listener : select search result item
    private View.OnClickListener onItemClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            RecyclerView.ViewHolder viewHolder = (RecyclerView.ViewHolder) view.getTag();
            int position = viewHolder.getAdapterPosition();
            selectedName = filteredList.get(position);
            selectedType = filteredType.get(position);
            name.setText(selectedName);
            closeKeyboard();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item_by_search);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setTitle("Search Item");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        buildRecyclerView();
        db = GLMDatabase.getInstance(MainActivity.context);
        glposition = getIntent().getIntExtra("glposition", -1);
        quantity = findViewById(R.id.quantity);
        quantity.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        name = findViewById(R.id.name);
        button = findViewById(R.id.addtolist);
        //when search bar edited
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
                //item name cannot be empty
                if(name.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(), "Please enter Item name", Toast.LENGTH_LONG).show();

                    //item is not in database
                }else if(filteredList.isEmpty() || !name.getText().toString().equals(filteredList.get(0))){
                    Intent intent = new Intent(AddItemBySearch.this, CreateItem.class);
                    intent.putExtra("name", name.getText().toString());
                    intent.putExtra("glposition", glposition);
                    startActivity(intent);

                    //add item to database
                }else if(quantity.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(), "Please enter quantity", Toast.LENGTH_LONG).show();

                    //add item to database
                }else{
                    que = Double.parseDouble(quantity.getText().toString());
                    if(que <= 0.0) {
                        Toast.makeText(getApplicationContext(), "Quantity must be greater than 0", Toast.LENGTH_LONG).show();
                        return;
                    }
                    newItem = new Item_list(name.getText().toString(), selectedType, que, false);
                    db.addItem(newItem, glposition);
                    // When clicked, jump back to list manager
                    Intent intent = new Intent(AddItemBySearch.this, Lists.class);
                    intent.putExtra("glposition", glposition);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                    //finish();
                }
            }
        });
    }

    //filter for item name input
    void filter(String text){
        if(!text.equals("")) {
            int count;
            filteredList = db.getSimilarItem(text);
            filteredType = new ArrayList<>();
            c = new ArrayList<>();
            //some item have multiple types
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
        private View.OnClickListener mOnItemClickListener;

        public class ViewHolder extends RecyclerView.ViewHolder {
            public TextView itemName, type;

            public ViewHolder(View itemView) {
                super(itemView);
                itemName = itemView.findViewById(R.id.textView);
                type = itemView.findViewById(R.id.textView2);
                itemView.setTag(this);
                itemView.setOnClickListener(mOnItemClickListener);
            }

            public String getItemName(){
                return itemName.toString();
            }

            public String getType(){
                return type.toString();
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
            filteredType.add(currentType.get(next.get(position)));
        }

        @Override
        public int getItemCount() {
            return filterList.size();
        }

        public void setOnItemClickListener(View.OnClickListener itemClickListener) {
            mOnItemClickListener = itemClickListener;
        }

        public void filterList(ArrayList<String> filteredList, ArrayList<Integer> c) {
            filterList = filteredList;
            next = c;
            notifyDataSetChanged();
        }
    }

    //build recycler view for search result
    private void buildRecyclerView() {
        filteredList = new ArrayList<>();
        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        filterAdapter = new CustAdapter(filteredList);
        filterAdapter.setOnItemClickListener(onItemClickListener);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(filterAdapter);
    }

    //when click on result hide virtual keyboard
    private void closeKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
}
