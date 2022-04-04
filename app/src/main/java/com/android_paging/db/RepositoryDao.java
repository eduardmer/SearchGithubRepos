package com.android_paging.db;

import androidx.paging.PagingSource;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import com.android_paging.data_model.RepositoryItems;
import java.util.List;

@Dao
public interface RepositoryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<RepositoryItems> repos);

    @Query("SELECT * FROM Repository WHERE name LIKE :query OR description LIKE :query ORDER BY stars DESC, name ASC")
    PagingSource<Integer, RepositoryItems> getRepos(String query);

    @Query("DELETE FROM Repository")
    void deleteAll();

}
