package com.bingo;

import android.content.Context;
import android.view.Gravity;
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
    private int []grille;

    public ItemAdapter(Context c, int []grille){
        mContext = c;
        this.grille = grille;
    }
    @Override
    public int getCount() {
        return grille.length;
    }

    public void setGrille(int[] grille)
    {
        this.grille = grille;
    }

    @Override
    public Object getItem(int i) {
        return grille[i];
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView textView;
        if(convertView == null)
        {
            textView = new TextView(mContext);
            textView.setHeight(200);
            textView.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL);
        }
        else
        {
            textView = (TextView)convertView;
        }
        textView.setText(Integer.toString(grille[position]));
        return textView;
    }
}
