package com.android_paging;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.android_paging.api.GithubService;
import com.android_paging.data_model.RepositoryItems;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    @Inject
    GithubService service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MainViewModel viewModel=new MainViewModel(new Repository(new GithubPagingSource(service, "androidpaging2")));
        RecyclerView recyclerView=findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        ReposAdapter adapter=new ReposAdapter(new DiffUtil.ItemCallback<RepositoryItems>() {
            @Override
            public boolean areItemsTheSame(@NonNull RepositoryItems oldItem, @NonNull RepositoryItems newItem) {
                return false;
            }

            @Override
            public boolean areContentsTheSame(@NonNull RepositoryItems oldItem, @NonNull RepositoryItems newItem) {
                return false;
            }
        });
        recyclerView.setAdapter(adapter);
        viewModel.getRepos().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(
                pagingData -> adapter.submitData(getLifecycle(),pagingData),
                error -> Log.i("pergjigja",error.toString())
        );
    }
}