package com.example.university_application;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.android.volley.VolleyError;
import com.example.university_application.api.ApiResponseModel;
import com.example.university_application.api.MyApiHelper;

import java.util.List;


public class DataRefreshForegroundService extends Service {
    private static final int NOTIFICATION_ID = 1;
    private static final String CHANNEL_ID = "DataRefreshChannel";

    @Override
    public void onCreate() {
        super.onCreate();
        createNotificationChannel();
        startForeground(NOTIFICATION_ID, createNotification(0));
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(
                    CHANNEL_ID,
                    "Data Refresh Service Channel",
                    NotificationManager.IMPORTANCE_DEFAULT
            );
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    private Notification createNotification(int records) {
        Intent notificationIntent = new Intent(this, MainActivity.class);

        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, notificationIntent, PendingIntent.FLAG_IMMUTABLE);

        return new NotificationCompat.Builder(this, CHANNEL_ID)
                .setContentTitle("Data Refresh Service")
                .setContentText("Refreshing data from the API. \n" + records + " Records fetched")
                .setSmallIcon(R.drawable.ic_refresh)
                .setContentIntent(pendingIntent)
                .build();
    }

    private void updateNotification(int records) {
        Notification notification = createNotification(records);
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        notificationManager.notify(NOTIFICATION_ID, notification);
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        new DataRefreshThread().start();
        return START_STICKY;
    }

    private class DataRefreshThread extends Thread {
        @Override
        public void run() {
            while (true) {
                try {
                    fetchDataFromApi();
                    // Sleep for 10 seconds
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    break;
                }
            }
        }
    }

    private void fetchDataFromApi() {
        MyApiHelper.fetchDataFromApi(this, new MyApiHelper.MyApiCallback() {
            @Override
            public void onSuccess(List<ApiResponseModel> dataList) {

                for (int i = 0; i < 4; i++) {
                    ApiResponseModel item = dataList.get(i);
//                    items.add(new Item(item.getName(), item.getCountry(), item.getWeb_pages().get(0)));
                }
                Log.d("ApiResponse", dataList.get(0).toString());
//                recyclerView.setAdapter(new MyAdapter(getApplicationContext(), items));
                updateNotification(dataList.size());
            }

            @Override
            public void onError(VolleyError error) {
                if (error != null) {
                    Log.e("Api Error: ", error.getMessage() + " ");
                }

            }
        });

    }

}

