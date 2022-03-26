package com.android_paging;

import androidx.databinding.ObservableField;
import androidx.lifecycle.ViewModel;
import androidx.paging.PagingData;

import com.android_paging.data_model.RepositoryItems;

import io.reactivex.rxjava3.core.Flowable;

public class MainViewModel extends ViewModel {

    final Repository repository;

    public MainViewModel(Repository repository){
        this.repository=repository;
    }

    public Flowable<PagingData<RepositoryItems>> getRepos(String query){
        return repository.getRepos(query);
    }

}
