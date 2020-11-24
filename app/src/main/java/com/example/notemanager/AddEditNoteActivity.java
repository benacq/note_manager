package com.example.notemanager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.notemanager.Entities.Note;
import com.google.android.material.snackbar.Snackbar;

import java.util.Objects;

public class AddEditNoteActivity extends AppCompatActivity {
    public static final String EXTRA_NEW_NOTE = "com.example.notemanager.EXTRA_NEW_NOTE";
    public static final String EXTRA_EDIT_NOTE = "com.example.notemanager.EXTRA_EDIT_NOTE";

    private RadioGroup radioGroup;
    private EditText editTextTitle;
    private EditText editDescription;
    private int priority;
    private boolean mHasExtra = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        editTextTitle = findViewById(R.id.note_title_edit_text);
        editDescription = findViewById(R.id.note_description_edit_text);
        radioGroup = findViewById(R.id.priorityRadioGroup);

        Objects.requireNonNull(getSupportActionBar()).setHomeAsUpIndicator(R.drawable.ic_close);
        Intent intent = getIntent();
        mHasExtra = intent.hasExtra(EXTRA_EDIT_NOTE);

        if (mHasExtra) {
            setTitle("Edit Note");
            Note note = intent.getParcelableExtra(EXTRA_EDIT_NOTE);
            editTextTitle.setText(note.getTitle());
            editDescription.setText(note.getDescription());
            switch (note.getPriority()) {
                case 0:
                    radioGroup.check(R.id.radio_least_important);
                    break;
                case 1:
                    radioGroup.check(R.id.radio_important);
                    break;
                case 2:
                    radioGroup.check(R.id.radio_very_important);
                    break;
            }
        } else {
            setTitle("Add Note");
        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.add_note_menu, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.save_note) {
            saveNote();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void saveNote() {
        String title = editTextTitle.getText().toString();
        String description = editDescription.getText().toString();

        if (title.trim().isEmpty() || description.trim().isEmpty()) {
            Snackbar.make(findViewById(R.id.add_note_constraint_layout), "Title and description is empty", Snackbar.LENGTH_LONG).show();
            return;
        }
        Intent data = new Intent();
        Note note = new Note(title, description, priority);
        if (!mHasExtra) {
            data.putExtra(EXTRA_NEW_NOTE, note);
        } else {
            Note passedNote = getIntent().getParcelableExtra(EXTRA_EDIT_NOTE);
            note.setId(passedNote.getId());
            data.putExtra(EXTRA_EDIT_NOTE, note);
        }
        setResult(RESULT_OK, data);
        finish();
    }

    public void checkButton(View view) {
        RadioButton radioButtonPriority = findViewById(radioGroup.getCheckedRadioButtonId());
        switch (radioButtonPriority.getText().toString()) {
            case "least important":
                priority = 0;
                break;
            case "important":
                priority = 1;
                break;
            case "very important":
                priority = 2;
                break;
        }

    }
}