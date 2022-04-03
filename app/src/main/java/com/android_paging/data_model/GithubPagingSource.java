package com.android_paging.data_model;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.paging.PagingState;
import androidx.paging.rxjava3.RxPagingSource;
import com.android_paging.api.GithubService;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class GithubPagingSource extends RxPagingSource<Integer, RepositoryItems> {

    final GithubService service;
    final String query;
    final int GITHUB_STARTING_PAGE_INDEX = 1;

    public GithubPagingSource(GithubService service, String query){
        this.service=service;
        this.query=query;
    }

    @NonNull
    @Override
    public Single<LoadResult<Integer, RepositoryItems>> loadSingle(@NonNull LoadParams<Integer> loadParams) {
        int position=loadParams.getKey() != null ? loadParams.getKey() : GITHUB_STARTING_PAGE_INDEX;
        return service.search(query, position, loadParams.getLoadSize())
                .subscribeOn(Schedulers.io())
                .map(repositorySearchResponse -> toLoadResult(repositorySearchResponse, position, loadParams))
                .onErrorReturn(LoadResult.Error::new);
    }

    private LoadResult<Integer, RepositoryItems> toLoadResult(RepositorySearchResponse searchResponse, int currentPosition, LoadParams<Integer> loadParams){
        return new LoadResult.Page<>(searchResponse.getItems(), currentPosition == GITHUB_STARTING_PAGE_INDEX ? null : currentPosition-1, searchResponse.getItems().size()==0 ? null : currentPosition+loadParams.getLoadSize()/20);
    }

    @Nullable
    @Override
    public Integer getRefreshKey(@NonNull PagingState<Integer, RepositoryItems> pagingState) {
        return null;
    }
}
