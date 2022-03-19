package com.android_paging.data_model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RepositorySearchResponse {

    @SerializedName("total_count")
    private int total;
    @SerializedName("items")
    private List<RepositoryItems> items;
    private int nextPage;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<RepositoryItems> getItems() {
        return items;
    }

    public void setItems(List<RepositoryItems> items) {
        this.items = items;
    }
}
