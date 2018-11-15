package vunt.com.vn.moviedb_28;

import android.app.Application;

import vunt.com.vn.moviedb_28.data.source.remote.config.service.NameServiceClient;

public class MainApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        NameServiceClient.initialize(this);
    }
}
