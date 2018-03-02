package com.example.test3;

import android.database.Cursor;
import android.database.CursorIndexOutOfBoundsException;
import android.net.Uri;
import android.provider.Telephony;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeMap;

/**
 * Created by Андрей on 28.02.2018.
 */

public class Dialogs {
    List<Dialog> dialogsList;
    public Dialogs() {
        dialogsList = new ArrayList<>();
    }

    public List<Dialog> getDialogs(View v){

        Map<Integer, Dialog> threadMap = new LinkedHashMap<>();

        try
        {
            Uri uri = Telephony.Sms.CONTENT_URI;//Uri.parse("content://sms");
            Cursor c = v.getContext().getContentResolver().query(uri, new String[] {"thread_id", "person", "date", "body", "address"}, null ,null,null);

            //Cursor c = v.getContext().getContentResolver().query(uri, new String[] {"thread_id"}, null ,null,null);
            //Cursor c = v.getContext().getContentResolver().query(uri,null,"thread_id = ?", new String[] {"11"},null);
            //Cursor c = v.getContext().getContentResolver().query(uri,null,"thread_id = ?", new String[] {"11"},null);
            dialogsList.clear();
            while (c.moveToNext())
            {

                String thread_id = c.getString(c.getColumnIndex("thread_id"));
                int thread_id_int = Integer.parseInt(thread_id);

                //String person  = c.getString(c.getColumnIndex("person"));
                String body = c.getString(c.getColumnIndex("body"));
                long date = c.getLong(c.getColumnIndex("date"));
                String address = c.getString(c.getColumnIndex("address"));

                Dialog dialog = new Dialog(thread_id_int,address,body,Long.toString(date));
                //dialogsList.add(dialog);

                if (!threadMap.containsKey(thread_id_int)){
                    threadMap.put(thread_id_int, dialog);
                }
                //dialogsList.add(new Dialog(thread_id_int,"test","test","test"));

            }
            for (Map.Entry<Integer, Dialog> pair : threadMap.entrySet()) {
                dialogsList.add(pair.getValue());
                //if(pair.getValue().getLastSmsTime())
                //dialogsList.add(new Dialog(0,"","" ," "));

            }
            //dialogsList.add(0,new Dialog(0,"test", thread_list, "0000"));

        }
        catch(CursorIndexOutOfBoundsException ee)
        {
            Toast.makeText(v.getContext(), "Error: "+ ee.getMessage() ,Toast.LENGTH_LONG).show();
        }
        return  dialogsList;

    }

}
