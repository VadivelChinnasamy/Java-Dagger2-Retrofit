package com.javadagger2.retrofit;

import com.javadagger2.Constants;
import com.javadagger2.dataclass.PopularMovies;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiServices {
	//Note: Plase add your moviedb API-KEY then only it will work
    @GET("movie/popular?api_key=API-KEY")
    Call<PopularMovies> getPopularMovie(@Query("page") int page);
	

}
