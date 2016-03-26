package me.oabbasi.droidcon.androidn;

import android.content.ClipData;
import android.content.ClipDescription;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
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

    public View getView(final int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View noteItem;

        if (convertView == null) {

            noteItem = inflater.inflate(R.layout.note_item_list_item, null);

        } else {
            noteItem = (View) convertView;
        }
        noteItem.setTag(notes[position]);
        //---------------------
        TextView textView = (TextView) noteItem
                .findViewById(R.id.noteItemText);
        textView.setText(notes[position]);

        //---------------------
        CheckBox noteItemCheckBox = (CheckBox) noteItem
                .findViewById(R.id.noteItemCheck);
        noteItemCheckBox.setChecked(position % 3 == 0);

//        noteItem.setOnLongClickListener(new View.OnLongClickListener() {
//            @Override
//            public boolean onLongClick(View v) {
//                ClipData.Item item = new ClipData.Item(v.getTag().toString());
//
//                // Create a new ClipData using the note as a label, the plain text MIME type, and
//                // the already-created item. This will create a new ClipDescription object within the
//                // ClipData, and set its MIME type entry to "text/plain"
//                ClipData dragData = new ClipData(v.getTag().toString(), new String[]{ClipDescription.MIMETYPE_TEXT_PLAIN},item);
//
//                // Instantiates the drag shadow builder.
//                View.DragShadowBuilder myShadow = new MyDragShadowBuilder(v);
//
//                // Starts the drag
//
//                v.startDragAndDrop(dragData,  // the data to be dragged
//                        myShadow,  // the drag shadow builder
//                        null,      // no need to use local data
//                        View.DRAG_FLAG_GLOBAL
//                );
//                return true;
//            }
//        });

        return noteItem;
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

//    private static class MyDragShadowBuilder extends View.DragShadowBuilder {
//
//        // The drag shadow image, defined as a drawable thing
//        private static Drawable shadow;
//
//        // Defines the constructor for myDragShadowBuilder
//        public MyDragShadowBuilder(View v) {
//
//            // Stores the View parameter passed to myDragShadowBuilder.
//            super(v);
//
//            // Creates a draggable image that will fill the Canvas provided by the system.
//            shadow = new ColorDrawable(Color.LTGRAY);
//        }
//
//        // Defines a callback that sends the drag shadow dimensions and touch point back to the
//        // system.
//        @Override
//        public void onProvideShadowMetrics (Point size, Point touch) {
//            // Defines local variables
//            int width, height;
//
//            // Sets the width of the shadow to half the width of the original View
//            width = getView().getWidth() / 2;
//
//            // Sets the height of the shadow to half the height of the original View
//            height = getView().getHeight() / 2;
//
//            // The drag shadow is a ColorDrawable. This sets its dimensions to be the same as the
//            // Canvas that the system will provide. As a result, the drag shadow will fill the
//            // Canvas.
//            shadow.setBounds(0, 0, width, height);
//
//            // Sets the size parameter's width and height values. These get back to the system
//            // through the size parameter.
//            size.set(width, height);
//
//            // Sets the touch point's position to be in the middle of the drag shadow
//            touch.set(width / 2, height / 2);
//        }
//
//        // Defines a callback that draws the drag shadow in a Canvas that the system constructs
//        // from the dimensions passed in onProvideShadowMetrics().
//        @Override
//        public void onDrawShadow(Canvas canvas) {
//
//            // Draws the ColorDrawable in the Canvas passed in from the system.
//            shadow.draw(canvas);
//        }
//    }

}
