package com.android_paging;

import androidx.paging.Pager;
import androidx.paging.PagingConfig;
import androidx.paging.PagingData;
import androidx.paging.rxjava3.PagingRx;
import com.android_paging.data_model.RepositoryItems;
import javax.inject.Inject;
import io.reactivex.rxjava3.core.Flowable;

public class Repository {

    final GithubPagingSource pagingSource;

    public Repository(GithubPagingSource pagingSource){
        this.pagingSource=pagingSource;
    }

    public Flowable<PagingData<RepositoryItems>> getRepos(){
        Pager<Integer, RepositoryItems> pager = new Pager(new PagingConfig(20,20,true,20,300),
                () -> pagingSource);
        return PagingRx.getFlowable(pager);
    }

}
