package com.javadagger2.dagger2;

import com.javadagger2.retrofit.ApiServices;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class NetworkModule {

    @Provides
    Retrofit getRetrofit() {
        return new Retrofit.Builder().baseUrl("http://api.themoviedb.org/3/")
                .addConverterFactory(GsonConverterFactory.create()).build();
    }
    @Provides
    ApiServices getRetrofitService() {
        return new Retrofit.Builder().baseUrl("http://api.themoviedb.org/3/")
                .addConverterFactory(GsonConverterFactory.create()).build()
                .create(ApiServices.class);
    }
}
