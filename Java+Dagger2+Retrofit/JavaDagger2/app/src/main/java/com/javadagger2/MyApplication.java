package com.javadagger2;

import android.app.Application;

import com.javadagger2.dagger2.DaggerNetworkComponent;
import com.javadagger2.dagger2.NetworkComponent;
import com.javadagger2.dagger2.NetworkModule;

public class MyApplication extends Application {
    NetworkComponent mComponent;
    @Override
    public void onCreate() {
        super.onCreate();

         mComponent= DaggerNetworkComponent.builder().networkModule(new NetworkModule()).build();
    }
    public NetworkComponent getComponent(){
        return mComponent;
    }
}
