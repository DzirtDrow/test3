package com.example.test3.Activities;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.CursorIndexOutOfBoundsException;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.test3.Adapters.SmsAdapter;
import com.example.test3.R;
import com.example.test3.SmsData;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Button button1;
    Button button2;
    Button button3;
    ListView listView1;
    TextView textView1;

    List<SmsData> smsListA = new ArrayList<SmsData>();

    final private int REQUEST_CODE_ASK_PERMISSIONS = 123;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView1 = findViewById(R.id.textView1);
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_SMS)!= PackageManager.PERMISSION_GRANTED) {
            // Permission is not granted
            //textView1.setText("Permission denied");
            if (!shouldShowRequestPermissionRationale(Manifest.permission.WRITE_CONTACTS)) {
                showMessageOKCancel("You need to allow access to sms",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                requestPermissions(new String[] {Manifest.permission.READ_SMS},
                                        REQUEST_CODE_ASK_PERMISSIONS);
                            }
                        });

            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_SMS}, REQUEST_CODE_ASK_PERMISSIONS);
            }
        } else {
            //textView1.setText("Permission allowed");
        }

        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        listView1 = (ListView)findViewById(R.id.listView1);



        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("SMS", "Button Clicked");
                GetSMSList(view);
            }
        });

        final SmsAdapter smsAdapter = new SmsAdapter(this);

        GetSMSList(listView1.getRootView());
        smsAdapter.setData(smsListA);
        listView1.setAdapter(smsAdapter);
        smsAdapter.notifyDataSetChanged();

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //getPermissionReadSms();

//                List<SmsData> wrapperList = new ArrayList<SmsData>();
//                wrapperList.add(new SmsData(1,"Title1", "Text1"));
//                wrapperList.add(new SmsData(2,"Title2", "Text2"));
//                wrapperList.add(new SmsData(3,"Title3", "Text3"));
//                wrapperList.add(new SmsData(4,"Title4", "Text4"));
//                wrapperList.add(new SmsData(5,"Title5", "Text5"));
                //smsListA.add(new SmsData(1,"Title1", "Text1"));
                GetSMSList(view);
                smsAdapter.setData(smsListA);
                listView1.setAdapter(smsAdapter);
                smsAdapter.notifyDataSetChanged();
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, DialogActivity.class);
                startActivity(intent);
            }
        });
    }


    public void getPermissionReadSms () {
        ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.READ_SMS}, REQUEST_CODE_ASK_PERMISSIONS);
    }

    public  void GetSMSList(View v){

        Context context = this;

        List<String> smsList = new ArrayList<>();

        try
        {
            Uri uri = Uri.parse("content://sms");
            Cursor c = v.getContext().getContentResolver().query(uri, null, null ,null,null);
            //Cursor c = v.getContext().getContentResolver().query(uri,null,"thread_id = ?", new String[] {"11"},null);

            //Cursor c = v.getContext().getContentResolver().query(uri,null,"thread_id = ?", new String[] {"11"},null);


            smsListA.clear();
            int i = 0;
            while (c.moveToNext())
            {
                String _id = c.getString(c.getColumnIndex("_id"));
                String thread_id = c.getString(c.getColumnIndex("thread_id"));
                String person  = c.getString(c.getColumnIndex("person"));
                String protocol  = c.getString(c.getColumnIndex("protocol"));
                String read    = c.getString(c.getColumnIndex("read"));
                String status  = c.getString(c.getColumnIndex("status"));
                String type  = c.getString(c.getColumnIndex("type"));
                String subject   = c.getString(c.getColumnIndex("subject"));
                String service_center   = c.getString(c.getColumnIndex("service_center"));
                String locked  = c.getString(c.getColumnIndex("locked"));

                String body = c.getString(c.getColumnIndex("body"));

                long date = c.getLong(c.getColumnIndex("date"));

                String address = c.getString(c.getColumnIndex("address"));


                int typeInt = Integer.parseInt(type);
                smsListA.add(new SmsData(++i,address,body, date, typeInt));

                //editText1.append("\n " + address + " - "+ body + " : " + data);
                //smsList.add(address + " : " +body);
                smsList.add( "  \n_id: " +  _id + "  \nthread_id: " +  thread_id +"  \naddress: " + address + "  \nperson: " + person +
                "  \ndate: " + (new Date(date)).toString() +
                "  \nprotocol: " + protocol +
                "  \nread: " + read +
                "  \nstatus: " +  status +
                "  \ntype: " + type +
                "  \nsubject: " + subject +
                "  \nbody: " + body );

            }
        }
        catch(CursorIndexOutOfBoundsException ee)
        {
            //Toast.makeText(v.getContext(), "Error :"+ ee.getMessage() ,Toast.LENGTH_LONG).show();
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, smsList);

        listView1.setAdapter(adapter);
        //adapter.notifyDataSetChanged();
    }

    private void showMessageOKCancel(String message, DialogInterface.OnClickListener okListener) {
        new AlertDialog.Builder(MainActivity.this)
                .setMessage(message)
                .setPositiveButton("OK", okListener)
                .setNegativeButton("Cancel", null)
                .create()
                .show();
    }

//    public void getSmsDialogs() {
//
//        SQLiteDatabase rdb =
//
//    }
}
