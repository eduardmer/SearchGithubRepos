package com.android_paging.data_model;

import androidx.annotation.NonNull;
import androidx.paging.LoadType;
import androidx.paging.PagingState;
import androidx.paging.rxjava3.RxRemoteMediator;

import com.android_paging.api.GithubService;
import com.android_paging.db.AppDatabase;
import com.android_paging.db.RemoteKeys;

import io.reactivex.rxjava3.core.Single;

public class GithubRemoteMediator extends RxRemoteMediator<Integer, RepositoryItems> {

    final GithubService service;
    final AppDatabase database;
    String query;

    public GithubRemoteMediator(GithubService service, AppDatabase database, String query){
        this.service=service;
        this.database=database;
        this.query=query;
    }

    @NonNull
    @Override
    public Single<MediatorResult> loadSingle(@NonNull LoadType loadType, @NonNull PagingState<Integer, RepositoryItems> pagingState) {

        return null;
    }

}
