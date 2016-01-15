package com.awesome.sainag.attendence;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    ItemAdapter adap;
    //ListView listView1 = (ListView) findViewById(R.id.gridGallery);
    private ArrayList<String> mItems = new ArrayList<String>();
    SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ListView g = (ListView) findViewById(R.id.gridGallery);
        db=openOrCreateDatabase("SubjectDB", Context.MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS subject(name VARCHAR, pres INTEGER, abs INTEGER, tot INTEGER);");
        Cursor c=db.rawQuery("SELECT * FROM subject", null);

        StringBuffer buffer=new StringBuffer();
        Log.v(TAG, "I'm Here");
        if(c.getCount()==0)
        {
            Log.v(TAG, "No Records found");
        }
        while(c.moveToNext())
        {
			/*buffer.append("Rollno: "+c.getString(0)+"\n");
			buffer.append("Name: "+c.getString(1)+"\n");
			buffer.append("Marks: "+c.getString(2)+"\n\n");*/
            Log.v(TAG, c.getString(0));
            mItems.add(c.getString(0));
        }
        Cursor c1=db.rawQuery("SELECT * FROM subject", null);



        if(c1.getCount()==0)
        {
            Log.v(TAG, "No Records found");
        }
        while(c1.moveToNext())
        {
			/*buffer.append("Rollno: "+c.getString(0)+"\n");
			buffer.append("Name: "+c.getString(1)+"\n");
			buffer.append("Marks: "+c.getString(2)+"\n\n");*/
            Log.v(TAG, c1.getString(0));

        }
        adap = new ItemAdapter(this, mItems);
        g.setAdapter(adap);

        registerForContextMenu(g);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

                Log.v(TAG, "AddSubject");
                Intent intent = new Intent(getApplicationContext(), AddSubjectActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });




    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.about) {
            Log.v(TAG, "About");
            Intent intent = new Intent(getApplicationContext(), About.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
