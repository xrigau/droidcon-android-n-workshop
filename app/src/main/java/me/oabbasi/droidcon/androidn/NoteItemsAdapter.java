package me.oabbasi.droidcon.androidn;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by oubai on 3/23/16.
 */
public class NoteItemsAdapter extends BaseAdapter {
    private Context context;
    private final String[] notes;

    public NoteItemsAdapter(Context context, String[] notes) {
        this.context = context;
        this.notes = notes;
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View gridView;

        if (convertView == null) {

            gridView = inflater.inflate(R.layout.note_item_list_item, null);

        } else {
            gridView = (View) convertView;
        }

        //---------------------
        TextView textView = (TextView) gridView
                .findViewById(R.id.noteItemText);
        textView.setText(notes[position]);

        //---------------------
        CheckBox noteItemCheckBox = (CheckBox) gridView
                .findViewById(R.id.noteItemCheck);
        noteItemCheckBox.setChecked(position % 2 == 0);

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
