package com.example.ashish.databasepractice;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    DataBaseHelper dataBaseHelper;
    EditText editName,editMarks,editId;
    Button button,button2,button3,button4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dataBaseHelper = new DataBaseHelper(this);

        editMarks = (EditText) findViewById(R.id.editMarks);
        editName = (EditText) findViewById(R.id.editName);
        button = (Button) findViewById(R.id.button);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
        editId = (EditText) findViewById(R.id.editId);
        button4 = (Button) findViewById(R.id.button4);

        final AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        final PendingIntent pi = PendingIntent.getBroadcast(this,1,new Intent(this,TaskReceiver.class),PendingIntent.FLAG_UPDATE_CURRENT);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               boolean isInserted = dataBaseHelper.insertData(editName.getText().toString(),editMarks.getText().toString());

                if(isInserted == true){
                    Toast.makeText(getBaseContext(), "Values inserted", Toast.LENGTH_SHORT).show();
                }
                else
                    Toast.makeText(getBaseContext(),"Values not inserted",Toast.LENGTH_SHORT).show();
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Cursor res = dataBaseHelper.getAllData();
                if(res.getCount() == 0){
                    Toast.makeText(MainActivity.this,"No Data Found",Toast.LENGTH_SHORT).show();
                    return;
                }
                StringBuffer buffer = new StringBuffer();
             while(res.moveToNext()) {
                 buffer.append("Id-" + res.getString(0) + "\n");
                 buffer.append("Name:" + res.getString(1) + "\n");
                 buffer.append("Marks:" + res.getString(2) + "\n");
                 buffer.append("\n");
             }
                 showMessage("Data",buffer.toString());


            }
        });
    button3.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            boolean isupdated = dataBaseHelper.updateData(editId.getText().toString(),editName.getText().toString(),editMarks.getText().toString());
            if(isupdated == true){
                Toast.makeText(MainActivity.this,"Data Updated",Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(MainActivity.this,"Data not updated",Toast.LENGTH_SHORT).show();

            }
            alarmManager.set(AlarmManager.RTC_WAKEUP,System.currentTimeMillis() + 10000,pi);

        }
    });

  button4.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
          int isDeleted = dataBaseHelper.deleteData(editId.getText().toString());

          if(isDeleted > 0){
              Toast.makeText(MainActivity.this,"Data deleted",Toast.LENGTH_SHORT).show();
          }else {
              Toast.makeText(MainActivity.this,"Data not deleted",Toast.LENGTH_SHORT).show();

          }

      }
  });

    }



public void showMessage(String title,String message) {
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
            alertDialog.setTitle(title);
            alertDialog.setCancelable(true);
            alertDialog.setMessage(message);
            alertDialog.show();
        }

    }


