package edu.qc.seclass.listmanager;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.CheckBox;
import android.widget.ListView;



public class MainActivity extends AppCompatActivity {
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        listView = (ListView) findViewById(R.id.listView);
        CheckBox selectAll = (CheckBox) findViewById(R.id.Selectall);
        getActionBar().setTitle("List Manager");
        getSupportActionBar().setTitle("List Manager");
        edu.qc.seclass.glm.GLMDatabase glmDatabase = new edu.qc.seclass.glm.GLMDatabase(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.mainlist , menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.addlist:
                //do something for everycase
                return true;
            default:return super.onOptionsItemSelected(item);
        }

    }
}
