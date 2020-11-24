package com.example.notemanager.Repositories;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.notemanager.Database.NoteDatabase;
import com.example.notemanager.Entities.Note;
import com.example.notemanager.NoteDao;

import java.util.List;

import static com.example.notemanager.Database.NoteDatabase.databaseWriteExecutor;

public class NoteRepository {
    private final NoteDao noteDao;
    private final LiveData<List<Note>> allNotes;

    public NoteRepository(Application application){
        NoteDatabase database = NoteDatabase.getInstance(application);
        noteDao = database.noteDao();
        allNotes = noteDao.getAllNotes();
    }

    public void insert(Note note){
        databaseWriteExecutor.execute(() -> noteDao.insert(note));
    }

    public void update(Note note){
        databaseWriteExecutor.execute(() -> noteDao.update(note));
    }

    public void delete(Note note){
        databaseWriteExecutor.execute(() -> noteDao.delete(note));
    }

    public void deleteAllNotes(){
        databaseWriteExecutor.execute(noteDao::deleteAllNotes);
    }

    public LiveData<List<Note>> getAllNotes(){
        return allNotes;
    }
}
