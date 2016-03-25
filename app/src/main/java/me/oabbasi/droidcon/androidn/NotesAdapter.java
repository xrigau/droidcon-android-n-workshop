package me.oabbasi.droidcon.androidn;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.RemoteInput;
import android.content.ClipData;
import android.content.ClipDescription;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.service.notification.StatusBarNotification;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by oubai on 3/23/16.
 */
public class NotesAdapter extends BaseAdapter {
    private Context context;
    private final String[] notes;
    public static final String KEY_TEXT_REPLY = "key_text_reply";

    public NotesAdapter(Context context, String[] notes) {
        this.context = context;
        this.notes = notes;
    }

    public View getView(final int position, final View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View gridView;

        if (convertView == null) {

            gridView = inflater.inflate(R.layout.note_card_view_grid_item, null);

        } else {
            gridView = (View) convertView;
        }

        //---------------------
        TextView textView = (TextView) gridView
                .findViewById(R.id.noteTitle);
        textView.setText(notes[position]);

        //---------------------
        ImageView noteImage = (ImageView) gridView
                .findViewById(R.id.noteImage);
        noteImage.setClickable(true);
        noteImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent noteIntent = new Intent(context, NoteActivity.class);
                context.startActivity(noteIntent);
            }
        });

        //---------------------
        ImageButton bookmarkButton = (ImageButton) gridView
                .findViewById(R.id.bookmarkNote);
        bookmarkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NotificationManager notificationManager = (NotificationManager)
                        context.getSystemService(Context.NOTIFICATION_SERVICE);
                StatusBarNotification[] activeNotifications = notificationManager
                        .getActiveNotifications();

                // Since the notifications might include this note notification remove it instead of adding it
                boolean noteNotificationExists = false;
                for (StatusBarNotification notification : activeNotifications) {
                    if (notification.getId() == position) {
                        noteNotificationExists = true;
                        notificationManager.cancel(notification.getId());
                        break;
                    }
                }
                if (!noteNotificationExists) {
                    String replyLabel = "Enter item text";
                    RemoteInput remoteInput = new RemoteInput.Builder(KEY_TEXT_REPLY)
                            .setLabel(replyLabel)
                            .build();

                    PendingIntent replyPendingIntent = PendingIntent.getService(context, 0, new Intent(context, NoteNotificationService.class), 0);
                    Notification.Action action =
                            new Notification.Action.Builder(R.drawable.ic_add_white_24dp,
                                    "Add item", replyPendingIntent)
                                    .addRemoteInput(remoteInput)
                                    .build();
                    // Build the notification and add the action
                    Notification notification =
                            new Notification.Builder(context)
                                    .setSmallIcon(R.drawable.ic_bookmark_border_black_24dp)
                                    .setContentTitle("Android N Notes")
                                    .setContentText("Note: " + notes[position])
                                    .addAction(action)
                                    .setOngoing(true)
                                    .setGroup("notes").build();

                    // Issue the notification
                    notificationManager.notify(position, notification);
                }
                updateNotificationSummary();
            }
        });

        //---------------------
        ImageButton openNewWindowButton = (ImageButton) gridView
                .findViewById(R.id.openNoteInNewWindow);
        openNewWindowButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent noteIntent = new Intent(context, NoteActivity.class);
                noteIntent.addFlags(Intent.FLAG_ACTIVITY_LAUNCH_ADJACENT | Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(noteIntent);
            }
        });

        return gridView;
    }

    /**
     * Adds/updates/removes the notification summary as necessary.
     */
    protected void updateNotificationSummary() {
        NotificationManager notificationManager = (NotificationManager)
                context.getSystemService(Context.NOTIFICATION_SERVICE);
        final StatusBarNotification[] activeNotifications = notificationManager
                .getActiveNotifications();

        int numberOfNotifications = activeNotifications.length;
        // Since the notifications might include a summary notification remove it from the count if
        // it is present.
        for (StatusBarNotification notification : activeNotifications) {
            if (notification.getId() == 99) {
                numberOfNotifications--;
                break;
            }
        }

        if (numberOfNotifications > 1) {
            // Add/update the notification summary.
            String notificationContent = "Android N Bookmarked Notes: " + numberOfNotifications;
            final NotificationCompat.Builder builder = new NotificationCompat.Builder(context)
                    .setSmallIcon(R.drawable.ic_bookmark_black_24dp)
                    .setStyle(new NotificationCompat.BigTextStyle()
                            .setSummaryText(notificationContent))
                    .setGroup("notes")
                    .setOngoing(true)
                    .setGroupSummary(true);
            final Notification notification = builder.build();
            notificationManager.notify(99, notification);
        } else {
            // Remove the notification summary.
            notificationManager.cancel(99);
        }
    }

    @Override
    public int getCount() {
        return notes.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

}
