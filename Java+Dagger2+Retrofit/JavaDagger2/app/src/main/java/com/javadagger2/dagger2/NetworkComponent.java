package com.javadagger2.dagger2;

import com.javadagger2.MainActivity;

import dagger.Component;
import retrofit2.Retrofit;

@Component(modules = {NetworkModule.class})
public interface NetworkComponent {
        public  void inject(MainActivity mainActivity);

        Retrofit getRetrofitObject();
}
