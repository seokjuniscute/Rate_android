package com.example.Rate;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapter extends BaseAdapter {

    ArrayList<Data> items= new ArrayList<>();

    Context mContext;

    public CustomAdapter(ArrayList<Data> arrayList, Context mContext) {
        this.items = arrayList;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, final View convertView, ViewGroup parent) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.item,null);


        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, AddListActivity.class);
                intent.putExtra("position",position);
                mContext.startActivity(intent);
                ((Activity)mContext).finish();
            }
        });

        TextView Day = v.findViewById(R.id.day);
        TextView Time = v.findViewById(R.id.time_remind);
        TextView Content = v.findViewById(R.id.content_remind);
        TextView Important = v.findViewById(R.id.important);


        Data data = items.get(position);

        Day.setText(data.getDay());
        Time.setText(data.getTime());
        Content.setText(data.getContnet());
        Important.setText(data.getImportance());

        return v;
    }
}
