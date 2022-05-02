package com.android_paging.db;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import io.reactivex.rxjava3.core.Single;

@Dao
public interface RemoteKeysDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertOrReplace(RemoteKeys remoteKey);

    @Query("SELECT * FROM RemoteKeys WHERE label = :query")
    Single<RemoteKeys> remoteKeyByQuerySingle(String query);

    @Query("DELETE FROM RemoteKeys WHERE label = :query")
    void deleteByQuery(String query);

}
