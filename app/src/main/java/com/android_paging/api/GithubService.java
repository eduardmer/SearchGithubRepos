package com.android_paging.api;

import com.android_paging.data_model.RepositorySearchResponse;
import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GithubService {

    @GET("search/repositories?sort=stars")
    Observable<RepositorySearchResponse> search(@Query("q") String q,@Query("page") int page, @Query("per_page") int perPage);

}
