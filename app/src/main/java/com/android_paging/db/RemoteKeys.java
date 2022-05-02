package com.android_paging.db;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class RemoteKeys {

    @PrimaryKey()
    @NonNull
    private String label;
    private String nextKey;

    public RemoteKeys(@NonNull String label, String nextKey){
        this.label = label;
        this.nextKey = nextKey;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getNextKey() {
        return nextKey;
    }

    public void setNextKey(String nextKey) {
        this.nextKey = nextKey;
    }
}
