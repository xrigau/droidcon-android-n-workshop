package me.oabbasi.droidcon.androidn;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by oubai on 3/23/16.
 */
public class NotesAdapter extends BaseAdapter {
    private Context context;
    private final String[] notes;

    public NotesAdapter(Context context, String[] notes) {
        this.context = context;
        this.notes = notes;
    }

    public View getView(int position, View convertView, ViewGroup parent) {

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
                Intent noteIntent = new Intent(context,NoteActivity.class);
                context.startActivity(noteIntent);
            }
        });

        //---------------------
        ImageButton bookmarkButton = (ImageButton) gridView
                .findViewById(R.id.bookmarkNote);
        bookmarkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO open notification for this note
            }
        });

        //---------------------
        ImageButton openNewWindowButton = (ImageButton) gridView
                .findViewById(R.id.openNoteInNewWindow);
        openNewWindowButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent noteIntent = new Intent(context,NoteActivity.class);
                noteIntent.addFlags(Intent.FLAG_ACTIVITY_LAUNCH_ADJACENT | Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(noteIntent);
            }
        });

        return gridView;
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
