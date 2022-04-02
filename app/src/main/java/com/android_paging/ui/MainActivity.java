package com.android_paging.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import com.android_paging.R;
import com.android_paging.data_model.RepositoryItems;
import com.android_paging.databinding.ActivityMainBinding;
import javax.inject.Inject;
import dagger.android.AndroidInjection;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    @Inject
    ViewModelFactory viewModelFactory;
    final CompositeDisposable compositeDisposable=new CompositeDisposable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding= DataBindingUtil.setContentView(this, R.layout.activity_main);
        MainViewModel viewModel = new ViewModelProvider(this,viewModelFactory).get(MainViewModel.class);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
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
        binding.recyclerView.setAdapter(adapter.withLoadStateFooter(new LoadingStateAdapter()));
        binding.search.setOnClickListener(v -> {
            if (!binding.searchRepo.getText().toString().isEmpty()){
                compositeDisposable.clear();
                compositeDisposable.add(viewModel.getRepos(binding.searchRepo.getText().toString()).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(
                        pagingData -> {
                            adapter.submitData(getLifecycle(), pagingData);
                            binding.progressBar.setVisibility(View.GONE);
                        },
                        error -> Log.i("pergjigja",error.toString())
                ));
            }
        });
    }
}