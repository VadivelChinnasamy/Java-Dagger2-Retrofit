package com.javadagger2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;

import com.javadagger2.dagger2.NetworkComponent;
import com.javadagger2.dataclass.PopularMovies;
import com.javadagger2.dataclass.Result;
import com.javadagger2.retrofit.ApiServices;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    @Inject
    Retrofit mRetrofit;

    @Inject
    ApiServices mApiServices;

    // Retrofit mRet;
    String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = findViewById(R.id.recyclerview);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        ((MyApplication) getApplication()).getComponent().inject(this);

        //    METHOD 1: ACCESS YOUR RETROFIT OBJECT FROM COMPONENT(OBJECT)
        //    mRetrofit1= ((MyApplication) getApplication()).getComponent().getRetrofitObject();

        //     METHOD 2 : HERE YOU CAN ACCESS THE APISERVICE BY PASSING ApiService.class
        //     THEN GET APISERVICE OBJECT THEN EXECUTE RETROFIT NETWORK CALL
        ApiServices mService = mRetrofit.create(ApiServices.class);
        mService.getPopularMovie(1).enqueue(new Callback<PopularMovies>() {
            @Override
            public void onResponse(Call<PopularMovies> call, Response<PopularMovies> response) {

                if (response != null) {
                    assert response.body() != null;
                    setMoviesAdapter(response.body().getResults());
                }

                Log.e(TAG, "Response");
            }

            @Override
            public void onFailure(Call<PopularMovies> call, Throwable t) {
                Log.e("TAG", "onFailure");
            }
        });

//     METHOD 3: HERE YOU CAN ACCESS THE APISERVICE DIRECTLY
//     mApiServices.getPopularMovie(1).enqueue(new Callback<PopularMovies>() {
//            @Override
//            public void onResponse(Call<PopularMovies> call, Response<PopularMovies> response) {
//
//
//                Log.e(TAG,"mApiServices - Response");
//            }
//
//            @Override
//            public void onFailure(Call<PopularMovies> call, Throwable t) {
//                Log.e(TAG,"mApiServices - onFailure");
//            }
//        });

    }

    private void setMoviesAdapter(List<Result> results) {
        PopularMoviesAdapter moviesAdapter = new PopularMoviesAdapter(MainActivity.this, results);
        mRecyclerView.setAdapter(moviesAdapter);

    }
}
