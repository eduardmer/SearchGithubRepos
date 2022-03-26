package com.android_paging;

import android.util.Log;

import androidx.lifecycle.ViewModelKt;
import androidx.paging.Pager;
import androidx.paging.PagingConfig;
import androidx.paging.PagingData;
import androidx.paging.rxjava3.PagingRx;

import com.android_paging.api.GithubService;
import com.android_paging.data_model.RepositoryItems;
import io.reactivex.rxjava3.core.Flowable;

public class Repository {

    final GithubService service;

    public Repository(GithubService service){
        this.service=service;
    }

    public Flowable<PagingData<RepositoryItems>> getRepos(String query){
        Pager<Integer, RepositoryItems> pager = new Pager(new PagingConfig(20,20,true,20),
                () -> new GithubPagingSource(service, query));
        Flowable<PagingData<RepositoryItems>> flowable=PagingRx.getFlowable(pager);
        Log.i("pergjigja","repository");
        //PagingRx.cachedIn(flowable, ViewModelKt.getViewModelScope())
        return PagingRx.getFlowable(pager);
    }

}
