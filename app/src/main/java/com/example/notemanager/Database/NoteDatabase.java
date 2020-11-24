package com.example.notemanager.Database;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.notemanager.Entities.Note;
import com.example.notemanager.NoteDao;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = Note.class, version = 1, exportSchema = false)
public abstract class NoteDatabase extends RoomDatabase{
    public abstract NoteDao noteDao();

    protected static volatile NoteDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    public static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static NoteDatabase getInstance(final Context context){
        if (INSTANCE == null){
            synchronized (NoteDatabase.class){
                if (INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), NoteDatabase.class, "note_database")
                            .fallbackToDestructiveMigration()
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static final RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);

            databaseWriteExecutor.execute(() -> {
                NoteDao dao = INSTANCE.noteDao();
                dao.insert(new Note("Note title 1", "Note description 1", 1));
                dao.insert(new Note("Note title 2", "Note description 2", 2));
                dao.insert(new Note("Note title 3", "Note description 3", 2));
                dao.insert(new Note("Note title 4", "Note description 4", 2));
                dao.insert(new Note("Note title 5", "Note description 5", 0));
                dao.insert(new Note("Note title 6", "Note description 6", 1));
                dao.insert(new Note("Note title 7", "Note description 7", 1));
                dao.insert(new Note("Note title 8", "Note description 8", 1));
                dao.insert(new Note("Note title 9", "Note description 9", 0));
                dao.insert(new Note("Note title 10", "Note description 10", 0));
                dao.insert(new Note("Note title 11", "Note description 11", 1));
                dao.insert(new Note("Note title 12", "Note description 12", 0));
                dao.insert(new Note("Note title 13", "Note description 13", 2));
                dao.insert(new Note("Note title 14", "Note description 14", 1));
                dao.insert(new Note("Note title 15", "Note description 15", 1));
                dao.insert(new Note("Note title 16", "Note description 16", 0));

                Log.d("DATABASE CREATED", "****************** DATABASE CREATED AND POPULATED SUCCESSFULLY ***********************");

            });
        }
    };

}
