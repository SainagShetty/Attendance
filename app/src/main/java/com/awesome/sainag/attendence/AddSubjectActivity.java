package com.awesome.sainag.attendence;

import android.app.ActionBar;
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
import android.widget.EditText;
import android.widget.Toast;

public class AddSubjectActivity extends AppCompatActivity {

    private static final String TAG = "AddSubjectActvity";
    EditText newSub;
    Button addSub;
    public SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Add Subject");
        //getActionBar().setHomeButtonEnabled(true);
//        getActionBar().setDisplayHomeAsUpEnabled(true);
 //       getActionBar().setHomeButtonEnabled(true);
        setContentView(R.layout.activity_add_subject);
        newSub=(EditText)findViewById(R.id.editName);
        addSub=(Button)findViewById(R.id.btnAdd);
        db=openOrCreateDatabase("SubjectDB", Context.MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS subject(name VARCHAR, pres INTEGER, abs INTEGER, tot INTEGER);");
        addSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(newSub.getText().toString().trim().length()==0)
                {
                    return;
                }


                db.beginTransaction();
                try {
                    db.execSQL("INSERT INTO subject VALUES('"+newSub.getText()+"',0,0,0);");
                    db.setTransactionSuccessful();
                } finally {
                    db.endTransaction();
                }
                Log.v(TAG,newSub.getText().toString() );
                clearText();
                Cursor c=db.rawQuery("SELECT * FROM subject", null);

                StringBuffer buffer=new StringBuffer();

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
                Intent intent = new Intent(this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }
    public void clearText()
    {

        newSub.setText("");


    }
}
