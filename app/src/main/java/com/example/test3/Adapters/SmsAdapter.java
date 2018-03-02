package com.example.test3.Adapters;

import android.content.Context;
import android.graphics.Color;
import android.text.Layout;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.test3.R;
import com.example.test3.SmsData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Андрей on 26.02.2018.
 */

public class SmsAdapter  extends BaseAdapter {

    private List<SmsData> data = new ArrayList<SmsData>();  //SMS list
    private LayoutInflater inflater;

    public SmsAdapter(Context mContext) {
        inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE); //Инфлейтер чтобы получить View из XML
    }

    public void setData(List<SmsData> data) {
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public SmsData getItem(int i) {
        return data.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflater.inflate(R.layout.sms_list_item, null);
        SmsData currentItem = getItem(i);

        if (currentItem.getSmsType() == 1) { //Input sms
            TextView.class.cast(view.findViewById(R.id.txt_text)).setTextColor(Color.GRAY);
            TextView.class.cast(view.findViewById(R.id.txt_text)).setTextAlignment(View.TEXT_ALIGNMENT_VIEW_START);
            LinearLayout.class.cast(view.findViewById(R.id.sms_layout)).setGravity(Gravity.LEFT);
            TextView.class.cast(view.findViewById(R.id.txt_text)).setPadding(15,15,15,15);



        } else if (currentItem.getSmsType() == 2) { //Output sms
            TextView.class.cast(view.findViewById(R.id.txt_text)).setTextColor(Color.BLUE);
            TextView.class.cast(view.findViewById(R.id.txt_text)).setTextAlignment(View.TEXT_ALIGNMENT_VIEW_END);
            LinearLayout.class.cast(view.findViewById(R.id.sms_layout)).setGravity(Gravity.RIGHT);
            TextView.class.cast(view.findViewById(R.id.txt_text)).setPadding(20,20,20,20);

        }
        TextView.class.cast(view.findViewById(R.id.txt_text)).setText(currentItem.getSmsText());

        TextView.class.cast(view.findViewById(R.id.txt_title)).setText(currentItem.getSmsNumber());
        TextView.class.cast(view.findViewById(R.id.txt_date)).setText(currentItem.getSmsDate());
        return view;
    }

}