package com.awesome.sainag.attendence;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ModifyAttendActivity extends AppCompatActivity {

    private static final String TAG = "ModifyAttendActvity";
    Button present,absent;
    SQLiteDatabase db;

    int noOfPres,noTotal,noOfAbs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Modify Attendence");
        setContentView(R.layout.activity_modify_attend);
        Bundle extras = getIntent().getExtras();
        final String mValue = extras.getString("SUBNAME");
        TextView tv = (TextView)findViewById(R.id.textView);
        tv.setText(mValue);

        db=openOrCreateDatabase("SubjectDB", Context.MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS subject(name VARCHAR, pres INTEGER, abs INTEGER, tot INTEGER);");
        present=(Button)findViewById(R.id.pre);
        absent=(Button)findViewById(R.id.abs);
        present.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.v(TAG, "Present");
                Cursor c=db.rawQuery("SELECT * FROM subject",null);
                while(c.moveToNext())
                {
                    Log.v(TAG, c.getString(0));
                    if(mValue.equals(c.getString(0))) {
                        int i= Integer.parseInt(c.getString(1));
                        noOfPres=i+1;
                        noTotal=c.getInt(3)+1;
                        db.execSQL("UPDATE subject SET pres = "+noOfPres+",tot = "+noTotal+" WHERE name = '"+mValue+"'");
                        Toast.makeText(getApplicationContext(), c.getString(1), Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });
        absent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor c=db.rawQuery("SELECT * FROM subject",null);
                while(c.moveToNext())
                {
                    Log.v(TAG, c.getString(0));
                    if(mValue.equals(c.getString(0))) {
                        int i= Integer.parseInt(c.getString(2));
                        noOfAbs=i+1;
                        noTotal=c.getInt(3)+1;
                        db.execSQL("UPDATE subject SET abs = "+noOfAbs+",tot = "+noTotal+" WHERE name = '"+mValue+"'");
                        Toast.makeText(getApplicationContext(),c.getString(2), Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

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
