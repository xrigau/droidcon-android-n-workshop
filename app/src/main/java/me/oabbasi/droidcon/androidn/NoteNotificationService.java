package me.oabbasi.droidcon.androidn;

import android.app.NotificationManager;
import android.app.RemoteInput;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

public class NoteNotificationService extends Service {
    public NoteNotificationService() {
        Log.d("NoteNotification","NoteNotificationService");
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("NoteNotification","onStartCommand");
        Bundle remoteInput = RemoteInput.getResultsFromIntent(intent);
        if (remoteInput != null) {
            Toast.makeText(getApplicationContext(),remoteInput.getCharSequence(NotesAdapter.KEY_TEXT_REPLY),Toast.LENGTH_SHORT).show();
            Log.d("NoteNotification",remoteInput.getCharSequence(NotesAdapter.KEY_TEXT_REPLY).toString());
            NotificationManager notificationManager = (NotificationManager)
                    getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.cancel(intent.getIntExtra("notificationId",0));
        }
        return super.onStartCommand(intent, flags, startId);
    }
}
