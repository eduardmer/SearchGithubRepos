package com.android_paging.db;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class RemoteKeys {

    @PrimaryKey
    private Long repoId;
    private int prevKey;
    private int nextKey;

    public Long getRepoId() {
        return repoId;
    }

    public void setRepoId(Long repoId) {
        this.repoId = repoId;
    }

    public int getPrevKey() {
        return prevKey;
    }

    public void setPrevKey(int prevKey) {
        this.prevKey = prevKey;
    }

    public int getNextKey() {
        return nextKey;
    }

    public void setNextKey(int nextKey) {
        this.nextKey = nextKey;
    }
}
