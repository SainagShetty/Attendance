package com.awesome.sainag.attendence;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

public class About extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        getSupportActionBar().setTitle("About");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }
    public void send2Insta(View view){
        Uri uri = Uri.parse("https://www.instagram.com/sainagshetty");
        Intent likeIng = new Intent(Intent.ACTION_VIEW, uri);
        likeIng.setPackage("com.instagram.android");
        try {
            startActivity(likeIng);
        } catch (Exception e) {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.instagram.com/sainagshetty")));
        }
    }
    public void send2Fb(View view){
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/sainag.shetty"));
        startActivity(browserIntent);
    }
    public void sendMyPage(View view){
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://sainagshetty.github.io"));
        startActivity(browserIntent);
    }
    public void send2Git(View view){
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/SainagShetty"));
        startActivity(browserIntent);
    }
    public void send2Link(View view){
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://in.linkedin.com/in/sainagshetty"));
        startActivity(browserIntent);
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
}
