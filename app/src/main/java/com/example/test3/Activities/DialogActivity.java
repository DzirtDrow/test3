package com.example.test3.Activities;

import android.content.Context;
import android.database.Cursor;
import android.database.CursorIndexOutOfBoundsException;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.test3.Adapters.DialogsAdapter;
import com.example.test3.Dialog;
import com.example.test3.Dialogs;
import com.example.test3.R;
import com.example.test3.SmsData;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DialogActivity extends AppCompatActivity {

    Button button;
    ListView dialogListView;

    List<Dialog> dialogsList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);

        dialogListView = findViewById(R.id.dialogListView);
//        button = findViewById(R.id.button);
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//            }
//        });

        DialogsAdapter dialogsAdapter = new DialogsAdapter(this);

        Dialogs dialogs = new Dialogs();



        //dialogsList = dialogs.getDialogs(dialogListView.getRootView());

        dialogsAdapter.setData(dialogs.getDialogs(dialogListView.getRootView())); // TODO ?????
        dialogListView.setAdapter(dialogsAdapter);
        dialogsAdapter.notifyDataSetChanged();
    }

//    public void getDialogs(View v){
//
//        Context context = this;
//
//        List<String> smsList = new ArrayList<>();
//
//        try
//        {
//            Uri uri = Uri.parse("content://sms");
//            Cursor c = v.getContext().getContentResolver().query(uri, new String[] {"thread_id", "person", "date", "body", "address"}, null ,null,null);
//
//
//            //Cursor c = v.getContext().getContentResolver().query(uri,null,"thread_id = ?", new String[] {"11"},null);
//            //Cursor c = v.getContext().getContentResolver().query(uri,null,"thread_id = ?", new String[] {"11"},null);
//
//            dialogsList.clear();
//            int i = 0;
//            while (c.moveToNext())
//            {
//
//                String thread_id = c.getString(c.getColumnIndex("thread_id"));
//                int thread_id_int = Integer.parseInt(thread_id);
//                //String person  = c.getString(c.getColumnIndex("person"));
//                String body = c.getString(c.getColumnIndex("body"));
//                long date = c.getLong(c.getColumnIndex("date"));
//                String address = c.getString(c.getColumnIndex("address"));
//
//                dialogsList.add(new Dialog(thread_id_int,address,body,Long.toString(date));
//
//            }
//        }
//        catch(CursorIndexOutOfBoundsException ee)
//        {
//            Toast.makeText(v.getContext(), "Error :"+ ee.getMessage() ,Toast.LENGTH_LONG).show();
//        }
//
//    }
}
