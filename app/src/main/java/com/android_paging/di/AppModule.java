package com.android_paging.di;

import android.app.Application;

import androidx.room.Room;

import com.android_paging.api.GithubService;
import com.android_paging.db.AppDatabase;

import javax.inject.Singleton;
import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class AppModule {

    @Singleton
    @Provides
    Retrofit provideRetrofit(){
        return new Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build();
    }

    @Singleton
    @Provides
    GithubService provideService(Retrofit retrofit){
        return retrofit.create(GithubService.class);
    }

    @Singleton
    @Provides
    AppDatabase provideDatabase(Application application){
        return Room.databaseBuilder(application.getApplicationContext(), AppDatabase.class, "AppDatabase").build();
    }

}
