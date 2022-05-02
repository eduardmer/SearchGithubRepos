package com.android_paging.data_model;

import androidx.annotation.NonNull;
import androidx.paging.LoadType;
import androidx.paging.PagingState;
import androidx.paging.rxjava3.RxRemoteMediator;
import com.android_paging.api.GithubService;
import com.android_paging.db.AppDatabase;
import com.android_paging.db.RemoteKeys;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class GithubRemoteMediator extends RxRemoteMediator<Integer, RepositoryItems> {

    final String GITHUB_STARTING_PAGE_INDEX = "1";
    final GithubService service;
    final AppDatabase database;
    final String query;

    public GithubRemoteMediator(GithubService service, AppDatabase database, String query){
        this.service=service;
        this.database=database;
        this.query=query;
    }

    @NonNull
    @Override
    public Single<MediatorResult> loadSingle(@NonNull LoadType loadType, @NonNull PagingState<Integer, RepositoryItems> pagingState) {

        Single<RemoteKeys> remoteKey=null;
        switch (loadType){
            case REFRESH:
                remoteKey = Single.just(new RemoteKeys(query, GITHUB_STARTING_PAGE_INDEX));
                break;
            case PREPEND:
                return Single.just(new MediatorResult.Success(true));
            case APPEND:
                remoteKey = database.remoteKeysDao().remoteKeyByQuerySingle(query);
                break;
        }

        return remoteKey.subscribeOn(Schedulers.io())
                .flatMap(remoteKeys -> {
                    if (loadType != LoadType.REFRESH && remoteKeys.getNextKey() == null)
                        return Single.just(new MediatorResult.Success(true));

                    return service.search(query, Integer.parseInt(remoteKeys.getNextKey()), pagingState.getConfig().pageSize).map(
                            searchResponse -> {
                                database.runInTransaction(() -> {
                                    if (loadType == LoadType.REFRESH){
                                        database.repositoryDao().deleteAll();
                                        database.remoteKeysDao().deleteByQuery(query);
                                    }
                                    String nextKey = searchResponse.getItems().size()==0 ? null : (Integer.parseInt(remoteKeys.getNextKey())+1)+"";
                                    database.remoteKeysDao().insertOrReplace(new RemoteKeys(query, nextKey));
                                    database.repositoryDao().insertAll(searchResponse.getItems());
                                });
                                return new MediatorResult.Success(searchResponse.getItems().size() == 0);
                            }
                    );
                });
    }

}
