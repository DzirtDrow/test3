package com.example.test3.Adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.test3.Dialog;
import com.example.test3.R;
import com.example.test3.SmsData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Андрей on 28.02.2018.
 */

public class DialogsAdapter extends BaseAdapter {

    private List<Dialog> data = new ArrayList<Dialog>();  //Dialogs list
    private LayoutInflater inflater;

    public DialogsAdapter(Context mContext) {
        inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE); //Инфлейтер чтобы получить View из XML
    }

    public void setData(List<Dialog> data) {
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Dialog getItem(int i) {
        return data.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Dialog currentItem = getItem(i);

        if(currentItem.getId()==0){
            view = inflater.inflate(R.layout.separator_item, null);
            TextView.class.cast(view.findViewById(R.id.txt_separator)).setClickable(false);
        } else {
            view = inflater.inflate(R.layout.dialog_list_item, null);
            TextView.class.cast(view.findViewById(R.id.txt_lastSms)).setText(currentItem.getLastSms());
            TextView.class.cast(view.findViewById(R.id.txt_title)).setText(currentItem.getTitle());
            TextView.class.cast(view.findViewById(R.id.txt_time)).setText(currentItem.getLastSmsTime());
            // ImageView.class.cast(view.findViewById(R.id.contactImageView)).setImage
        }
        return view;

    }
}
