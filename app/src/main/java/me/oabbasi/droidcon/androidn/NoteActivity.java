package me.oabbasi.droidcon.androidn;

import android.content.ClipData;
import android.content.ClipDescription;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.DragEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridView;
import android.widget.Toast;

public class NoteActivity extends AppCompatActivity implements View.OnDragListener {

    GridView gridView;

    static final String[] notes = new String[]{
            "Apples",
            "Bananas",
            "Berries",
            "Grapes",
            "Lemons",
            "Lime",
            "Melons",
            "Nectarines",
            "Oranges",
            "Peaches",
            "Pears",
            "Plums",
            "Strawberries",
            "Watermelon"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        myToolbar.setBackgroundColor(Color.LTGRAY);
        setSupportActionBar(myToolbar);
        setTitle(getIntent().getStringExtra("noteTitle"));
        getWindow().setStatusBarColor(Color.DKGRAY);

        gridView = (GridView) findViewById(R.id.notesGridView);
        gridView.setAdapter(new NoteItemsAdapter(this, notes));
        gridView.setOnDragListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.note_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_close) {
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    // This is the method that the system calls when it dispatches a drag event to the
    // listener.
    public boolean onDrag(View v, DragEvent event) {

        // Defines a variable to store the action type for the incoming event
        final int action = event.getAction();

        // Handles each of the expected events
        switch (action) {

            case DragEvent.ACTION_DRAG_STARTED:

                // Determines if this View can accept the dragged data
                if (event.getClipDescription().hasMimeType(ClipDescription.MIMETYPE_TEXT_PLAIN)) {

                    // As an example of what your application might do,
                    // applies a blue color tint to the View to indicate that it can accept
                    // data.
                    v.setBackgroundColor(Color.BLUE);

                    // Invalidate the view to force a redraw in the new tint
                    v.invalidate();

                    // returns true to indicate that the View can accept the dragged data.
                    return true;

                }

                // Returns false. During the current drag and drop operation, this View will
                // not receive events again until ACTION_DRAG_ENDED is sent.
                return false;

            case DragEvent.ACTION_DRAG_ENTERED:

                // Applies a green tint to the View. Return true; the return value is ignored.

                v.setBackgroundColor(Color.GREEN);

                // Invalidate the view to force a redraw in the new tint
                v.invalidate();

                return true;

            case DragEvent.ACTION_DRAG_LOCATION:

                // Ignore the event
                return true;

            case DragEvent.ACTION_DRAG_EXITED:

                // Re-sets the color tint to blue. Returns true; the return value is ignored.
                v.setBackgroundColor(Color.BLUE);

                // Invalidate the view to force a redraw in the new tint
                v.invalidate();

                return true;

            case DragEvent.ACTION_DROP:

                // Gets the item containing the dragged data
                ClipData.Item item = event.getClipData().getItemAt(0);

                // Gets the text data from the item.
                String dragData = item.getText().toString();

                // Displays a message containing the dragged data.
                Toast.makeText(this, "Dragged data is " + dragData, Toast.LENGTH_LONG).show();

                // Turns off any color tints
                v.setBackgroundColor(Color.TRANSPARENT);

                // Invalidates the view to force a redraw
                v.invalidate();

                // Returns true. DragEvent.getResult() will return true.
                return true;

            case DragEvent.ACTION_DRAG_ENDED:

                // Turns off any color tinting
                v.setBackgroundColor(Color.TRANSPARENT);

                // Invalidates the view to force a redraw
                v.invalidate();

                // Does a getResult(), and displays what happened.
                if (event.getResult()) {
                    Toast.makeText(this, "The drop was handled.", Toast.LENGTH_LONG).show();

                } else {
                    Toast.makeText(this, "The drop didn't work.", Toast.LENGTH_LONG).show();

                }

                // returns true; the value is ignored.
                return true;

            // An unknown action type was received.
            default:
                Log.e("DragDrop Example", "Unknown action type received by OnDragListener.");
                break;
        }

        return false;
    }
}
