//package com.example.university_application;
//
//import android.app.Notification;
//import android.app.Service;
//import android.content.Intent;
//import android.os.IBinder;
//import android.util.Log;
//
//import androidx.annotation.NonNull;
//import androidx.annotation.Nullable;
//import androidx.core.app.NotificationCompat;
//
//public class MyforegroundService extends Service {
//    public MyforegroundService(){
//
//    }
//
//    @Override
//    public void onCreate() {
//        super.onCreate();
//        // Perform any setup or initialization here
//    }
//
//    @Nullable
//    @Override
//    public IBinder onBind(Intent intent) {
//        throw new UnsupportedOperationException();
//    }
//
//    @Override
//    public int onStartCommand(Intent intent,int flag,int startId){
//        Notification notification = createNotification();
//        // Start the service in the foreground with the notification
//        Log.d("TAG", "onStartCommand: Notification start");
//        startForeground(1, notification);
//        // Perform your foreground service tasks here
//        return START_STICKY;
//
////        Thread thread=new Thread(new Runnable() {
////            @Override
////            public void run() {
////                Log.d("TAG", "run: Task is running");
////                int i=0;
////                while(i<=10){
////                    Log.d("TAG", "run: task in pogress"+(i+1));
////                    try{
////                      Thread.sleep(1000);
////                    }catch (Exception e){
////                        Log.d("TAG", "run: exception is "+e);
////                    }
////                }
////                Log.d("TAG", "run: running complete");
////            }
////        });
////        thread.start();
////        return  START_STICKY;
//    }
//
//    @Override
//    public void onDestroy() {
//        super.onDestroy();
//        // Clean up and stop the foreground service
//        stopForeground(true);
//    }
//
//    private Notification createNotification() {
//        Log.d("TAG", "createNotification: in side createNotification");
//        // Create and customize a notification for your foreground service
//        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "channel_id")
//                .setContentTitle("Foreground Service")
//                .setContentText("Service is running...");
////                .setSmallIcon(R.drawable.ic_notification);
//        return builder.build();
//    }
//}
