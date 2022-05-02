package com.android_paging.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.android_paging.data_model.RepositoryItems;

@Database(entities = {RepositoryItems.class, RemoteKeys.class}, version = 2, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    public abstract RepositoryDao repositoryDao();

    public abstract RemoteKeysDao remoteKeysDao();

}
