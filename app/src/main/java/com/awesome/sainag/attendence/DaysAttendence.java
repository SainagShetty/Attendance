package com.awesome.sainag.attendence;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ListView;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class DaysAttendence extends AppCompatActivity {

    private static final String TAG = "DaysAttendence";
    private ArrayList<String> mItems = new ArrayList<String>();
    private ArrayList<String> pOrA = new ArrayList<String>();
   // int time = (int) (Date.);
    //final Timestamp tsTemp = new Timestamp(time);
    DayAdapter adap;
    SQLiteDatabase db2;

    //SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
    //String formattedDate = df.format(DateFormat.getDateInstance());
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_days_attendence);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Day's List");
        ListView g = (ListView) findViewById(R.id.listGal);
        db2=openOrCreateDatabase("SubjectDB", Context.MODE_PRIVATE, null);
        db2.execSQL("CREATE TABLE IF NOT EXISTS entrydate(name VARCHAR, timeStamp TIMESTAMP, pOrA varchar);");
        Cursor c=db2.rawQuery("SELECT * FROM entrydate", null);
        Calendar c1 = Calendar.getInstance();
        String d = new Integer(c1.get(Calendar.DATE)).toString()+new Integer(c1.get(Calendar.MONTH)).toString()+new Integer(c1.get(Calendar.YEAR)).toString();

        if(c.getCount()==0)
        {
            Log.v(TAG, "No Records found");
        }
        while(c.moveToNext())
        {
             Log.v(TAG, d);
            Log.v(TAG, c.getString(1));
            Log.v(TAG, c.getString(0) + c.getString(2));
            Log.v(TAG, c.getString(0));
            if(c.getString(2)!=""&&c.getString(1).equals(d)){
                mItems.add(c.getString(0));
                pOrA.add(c.getString(2));
            }
        }
        adap = new DayAdapter(this, mItems, pOrA);
        g.setAdapter(adap);
        db2.close();
    }
    @Override
      public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();


        switch (item.getItemId()) {
            case android.R.id.home:
                Log.v(TAG, "HomeClicked");

                Intent intent = new Intent(this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

}
