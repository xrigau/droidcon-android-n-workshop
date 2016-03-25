package me.oabbasi.droidcon.androidn;

import android.app.RemoteInput;
import android.app.Service;
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
            Log.d("NoteNotification","remoteInput != null");
            Toast.makeText(getApplicationContext(),remoteInput.getCharSequence(NotesAdapter.KEY_TEXT_REPLY),Toast.LENGTH_SHORT).show();
            Log.d("NoteNotification",remoteInput.getCharSequence(NotesAdapter.KEY_TEXT_REPLY).toString());
        }
        return super.onStartCommand(intent, flags, startId);
    }
}
