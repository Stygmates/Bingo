package com.bingo;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;

/**
 * Created by tit-a on 16/12/2016.
 */

public class ItemAdapter extends BaseAdapter {
    private Context mContext;
    private String[] nombres = {"1","2","3","4","5","6","7","8","9"};

    public ItemAdapter(Context c){
        mContext = c;
    }
    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView textView;
        if(convertView == null)
        {
            textView = new TextView(mContext);
        }
        else
        {
            textView = (TextView)convertView;
        }
        textView.setText(nombres[position]);
        return textView;
    }
}
