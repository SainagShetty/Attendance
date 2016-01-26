package com.awesome.sainag.attendence;

/**
 * Created by sainagshetty on 26/01/16.
 */

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.CardView;
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
public class DayAdapter extends BaseAdapter {

    private Context mContext;
    private LayoutInflater infalter;
    private ArrayList<String> mItems;
    private ArrayList<String> pOrA;
    // private Color cols[];
    static int co;


    private static final String TAG = "ItemAdapter";

    public DayAdapter(Context c, ArrayList<String> items,ArrayList<String> prAb) {
        infalter = (LayoutInflater) c
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mContext = c;
        mItems = items;
        pOrA = prAb;
        //cols = new Color[12];
        //setCol();
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
        View v = null;
        if (v == null) {

            LayoutInflater li = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = li.inflate(R.layout.subject_item, null);
            final CardView card = (CardView)v.findViewById(R.id.card_view);
            //card.setCardBackgroundColor(cols[]);

            Log.v(TAG, pOrA.get(position));
            if(pOrA.get(position).equals("a")){
                Log.v(TAG, "Red");
                card.setCardBackgroundColor(Color.parseColor("#FF4C4C"));

            }
            if(pOrA.get(position).equals("p")){
                Log.v(TAG, "Green");
                card.setCardBackgroundColor(Color.parseColor("#2DB82D"));

            }
            final TextView tv = (TextView)v.findViewById(R.id.stdname);
            //tv.setTextColor(Color.WHITE);
            tv.setText(mItems.get(position));
            tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.v(TAG, mItems.get(position));
                    Toast.makeText(mContext, mItems.get(position), Toast.LENGTH_SHORT).show();

                }
            });
        }
        return v;
    }

}

