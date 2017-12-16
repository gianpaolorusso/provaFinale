package com.example.utente5academy.provafinale.service;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v7.app.NotificationCompat;

import com.example.utente5academy.provafinale.MainActivity;
import com.example.utente5academy.provafinale.R;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by Utente on 16/12/2017.
 */

@SuppressWarnings("ALL")
public class ServiceCourrier extends Service {

    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference reference;
    private ChildEventListener childEventListener;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return Service.START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        SharedPreferences preferences = getSharedPreferences("login", MODE_PRIVATE);
        String tipo = preferences.getString("tipo", "");
        String username = preferences.getString("username", "");
        if (tipo != "") {

            String url = "https://provafinale-f0d57.firebaseio.com/Users/" + tipo + "/" + username + "/pacchi";
            reference = database.getReferenceFromUrl(url);
            childEventListener = new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    activePushValidation(dataSnapshot.getKey());
                }

                @Override
                public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                    activePushValidation(dataSnapshot.getKey());
                }

                @Override
                public void onChildRemoved(DataSnapshot dataSnapshot) {

                }

                @Override
                public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {


                }
            };
            reference.addChildEventListener(childEventListener);
        }
    }

    public void activePushValidation(String chiave) {
        Intent intent = new Intent(this, MainActivity.class);
        String messaggio = "Ti è stata assegnata una nuova spedizione";
        sendNotification(intent, messaggio);

    }

    public void sendNotification(Intent intent, String messggio) {
        Bitmap bitmap = BitmapFactory.decodeResource(this.getResources(), R.mipmap.ic_launcher_round);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        builder.setContentTitle("Spedizione assegnata");
        builder.setContentText(messggio);
        builder.setAutoCancel(true);
        builder.setSmallIcon(R.drawable.pacco);
        builder.setShowWhen(true);
        builder.setContentIntent(pendingIntent);
        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(0, builder.build());
    }
}