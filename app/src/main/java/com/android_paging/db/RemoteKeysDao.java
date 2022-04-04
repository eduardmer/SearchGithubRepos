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
    void insertAll(List<RemoteKeys> remoteKeys);

    @Query("SELECT * FROM RemoteKeys WHERE repoId = :repoId")
    Single<RemoteKeys> remoteKeysRepoId(Long repoId);

    @Query("DELETE FROM RemoteKeys")
    void clearRemoteKeys();

}
