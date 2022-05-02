package com.android_paging.ui;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import com.android_paging.api.GithubService;
import com.android_paging.db.AppDatabase;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class ViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    final GithubService service;
    final AppDatabase database;

    @Inject
    public ViewModelFactory(GithubService service, AppDatabase database){
        this.service = service;
        this.database = database;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(MainViewModel.class))
            return (T) new MainViewModel(service, database);
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
