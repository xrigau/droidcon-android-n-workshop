package me.oabbasi.droidcon.androidn;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.GridView;

public class MainActivity extends AppCompatActivity {

    GridView gridView;

    static final String[] notes = new String[] {
            "Android", "iOS","Windows", "Blackberry","Android", "iOS","Windows", "Blackberry","Android", "iOS","Windows", "Blackberry","Android", "iOS","Windows", "Blackberry" };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gridView = (GridView) findViewById(R.id.notesGridView);

        gridView.setAdapter(new NotesAdapter(this, notes));

    }
}
