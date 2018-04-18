package com.example.sheetal.hp;

import android.app.Notification;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class AddChild extends AppCompatActivity {

   // ListView mlistView;

    TextView tDate;
    Toolbar addChildToolbar;

    NotificationHelper notificationHelper;

    Button button;
    private String stime=null;
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_child);

       // mlistView = findViewById(R.id.listView);

        notificationHelper = new NotificationHelper(this);

        addChildToolbar = findViewById(R.id.AddChild_Toolbar);
        setSupportActionBar(addChildToolbar);
        getSupportActionBar().setTitle("Add Child");
        getSupportActionBar().setHomeButtonEnabled(true);

        tDate = findViewById(R.id.dateTextView);
        Date currentTime = Calendar.getInstance().getTime();

        button = findViewById(R.id.button2);
       // stime = tDate.toString();
        tDate.setText(currentTime.toString());
        Notification.Builder builder = notificationHelper.getsheetalChannelNotification("Hello","world");
        notificationHelper.getManager().notify(new Random().nextInt(),builder.build());

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(AddChild.this,"Notification send",Toast.LENGTH_SHORT).show();
                Notification.Builder builder = notificationHelper.getsheetalChannelNotification("Hello","world");
                notificationHelper.getManager().notify(new Random().nextInt(),builder.build());
            }
        });
    }
}
