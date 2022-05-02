package com.android_paging.ui;

import androidx.lifecycle.ViewModel;
import androidx.paging.Pager;
import androidx.paging.PagingConfig;
import androidx.paging.PagingData;
import androidx.paging.rxjava3.PagingRx;
import com.android_paging.api.GithubService;
import com.android_paging.data_model.GithubRemoteMediator;
import com.android_paging.data_model.RepositoryItems;
import com.android_paging.db.AppDatabase;
import io.reactivex.rxjava3.core.Flowable;

public class MainViewModel extends ViewModel {

    final String GITHUB_STARTING_PAGE_INDEX = "1";
    final GithubService service;
    final AppDatabase database;

    public MainViewModel(GithubService service, AppDatabase database){
        this.service = service;
        this.database = database;
    }

    public Flowable<PagingData<RepositoryItems>> getRepos(String query){
        Pager<Integer, RepositoryItems> pager = new Pager(
                new PagingConfig(50,50, false),
                1,
                new GithubRemoteMediator(service, database, query),
                () -> database.repositoryDao().getRepos("%" + query + "%"));
        Flowable<PagingData<RepositoryItems>> flowable= PagingRx.getFlowable(pager);
        //PagingRx.cachedIn(flowable, ViewModelKt.getViewModelScope(this));
        return flowable;
    }

}
