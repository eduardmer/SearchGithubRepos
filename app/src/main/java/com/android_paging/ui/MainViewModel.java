package com.android_paging.ui;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelKt;
import androidx.paging.Pager;
import androidx.paging.PagingConfig;
import androidx.paging.PagingData;
import androidx.paging.rxjava3.PagingRx;
import com.android_paging.data_model.GithubPagingSource;
import com.android_paging.api.GithubService;
import com.android_paging.data_model.RepositoryItems;
import io.reactivex.rxjava3.core.Flowable;

public class MainViewModel extends ViewModel {

    final GithubService service;

    public MainViewModel(GithubService service){
        this.service=service;
    }

    public Flowable<PagingData<RepositoryItems>> getRepos(String query){
        Pager<Integer, RepositoryItems> pager = new Pager(
                new PagingConfig(20,20,false,60),
                () -> new GithubPagingSource(service, query));
        Flowable<PagingData<RepositoryItems>> flowable= PagingRx.getFlowable(pager);
        PagingRx.cachedIn(flowable, ViewModelKt.getViewModelScope(this));
        return flowable;
    }

}
