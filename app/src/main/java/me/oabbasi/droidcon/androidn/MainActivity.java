package me.oabbasi.droidcon.androidn;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.GridView;

public class MainActivity extends AppCompatActivity {

    GridView gridView;

    static final String[] notes = new String[] {
            "Groceries", "Assignments","Home Chores", "Work tasks", "Contacts", "App ideas" };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        myToolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(myToolbar);

        gridView = (GridView) findViewById(R.id.notesGridView);
        gridView.setAdapter(new NotesAdapter(this, notes));

    }
}
