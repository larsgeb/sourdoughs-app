package com.example.swpsourdough;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;


import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.UUID;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Start in the main activity
        setContentView(R.layout.activity_main);

        // Set up notification channel
        createNotificationChannel();

        //
        notifyLowPriority(1234, "Breadalert!", "Heyo");

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, getString(R.string.loading_text),
                        Snackbar.LENGTH_SHORT).setAction("Action", FABCallback())
                        .show();
            }
        });

    }

    private View.OnClickListener FABCallback() {
        Log.d("Lars", "Clicked the FAB");
        return null;
    }

    @Override
    public void onResume() {

        super.onResume();


    }


    private void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = getString(R.string.channel_name);
            String description = getString(R.string.channel_description);
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel =
                    new NotificationChannel(getString(R.string.channel_ID), name,
                            importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager =
                    getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    public boolean notifyLowPriority(int notification_id, String notification_title,
                                     String notification_message) {
        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(this,
                        getString(R.string.channel_ID))
                        .setSmallIcon(R.drawable.ic_launcher_mono_foreground)
                        .setContentTitle(notification_title)
                        .setContentText(notification_message)
                        .setPriority(NotificationCompat.PRIORITY_LOW);

        NotificationManagerCompat notificationManager =
                NotificationManagerCompat.from(this);

        notificationManager.notify(notification_id, builder.build());

        return true;

    }

}

