package com.android_paging.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.android_paging.data_model.RepositoryItems;

@Database(entities = {RepositoryItems.class, RemoteKeys.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    abstract RepositoryDao repositoryDao();

    abstract RemoteKeysDao remoteKeysDao();

}
