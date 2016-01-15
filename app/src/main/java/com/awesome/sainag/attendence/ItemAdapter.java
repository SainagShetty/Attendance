package com.awesome.sainag.attendence;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by sainagshetty on 10/01/16.
 */
public class ItemAdapter extends BaseAdapter {

    private Context mContext;
    private LayoutInflater infalter;
    private ArrayList<String> mItems;


    private static final String TAG = "ItemAdapter";

    public ItemAdapter(Context c, ArrayList<String> items) {
        infalter = (LayoutInflater) c
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mContext = c;
        mItems = items;

        // clearCache();
    }

    @Override
    public int getCount() {
        return mItems.size();
    }

    @Override
    public Object getItem(int position) {
        return mItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View v = convertView;
        if (v == null) {

            LayoutInflater li = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = li.inflate(R.layout.subject_item, null);
            final TextView tv = (TextView)v.findViewById(R.id.stdname);
            tv.setText(mItems.get(position));
            tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.v(TAG, mItems.get(position));
                    Toast.makeText(mContext, mItems.get(position), Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(mContext,ModifyAttendActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    String message = mItems.get(position);
                    intent.putExtra("SUBNAME", message);
                    mContext.startActivity(intent);
                }
            });
        }
        return v;
    }
}

