package me.oabbasi.droidcon.androidn;


import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.service.quicksettings.TileService;
import android.util.Log;

public class MyQSTileService extends TileService {
    public MyQSTileService() {
        super();
        Log.d("TileService","MyQSTileService");
    }

    /**
     * Called when the tile is added to the quick settings active list
     * @return
     */
    @Override
    public int onTileAdded() {
        Log.d("TileService","onTileAdded");
        return super.onTileAdded();
    }

    /**
     * Called when the tile is removed from the quick settings
     */
    @Override
    public void onTileRemoved() {
        super.onTileRemoved();
        Log.d("TileService","onTileRemoved");
    }

    /**
     * Called when the tile is visible/the menu is open
     */
    @Override
    public void onStartListening() {
        super.onStartListening();
        Log.d("TileService","onStartListening");
    }

    /**
     * Called when the tile is hidden/the menu is closed
     */
    @Override
    public void onStopListening() {
        super.onStopListening();
        Log.d("TileService","onStopListening");
    }

    /**
     * Called when the user clicks on the tile
     */
    @Override
    public void onClick() {
        super.onClick();
        Log.d("TileService","onClick");
        NotificationManager notificationManager = (NotificationManager)
                getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.cancelAll();
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.d("TileService","onBind");
        return super.onBind(intent);
    }
}
