package com.android_paging.db;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Repository.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public static AppDatabase myDatabase;

    public static synchronized AppDatabase getInstance(Context context){
        if (myDatabase==null)
            myDatabase= Room.databaseBuilder(context, AppDatabase.class, "AppDatabase").fallbackToDestructiveMigration().build();
        return myDatabase;
    }

    public abstract AppDao appDao();

}
