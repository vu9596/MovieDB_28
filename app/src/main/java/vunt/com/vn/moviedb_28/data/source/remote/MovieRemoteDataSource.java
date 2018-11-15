package vunt.com.vn.moviedb_28.data.source.remote;

import vunt.com.vn.moviedb_28.data.source.MovieDataSource;
import vunt.com.vn.moviedb_28.data.source.remote.config.service.Api;
import vunt.com.vn.moviedb_28.data.source.remote.config.service.NameServiceClient;

public class MovieRemoteDataSource implements MovieDataSource.Remote {

    private static MovieRemoteDataSource sInstance;
    private Api mApi;

    private MovieRemoteDataSource(Api api) {
        mApi = api;
    }

    public static MovieRemoteDataSource getInstance() {
        if (sInstance == null) {
            sInstance = new MovieRemoteDataSource(NameServiceClient.getInstance());
        }
        return sInstance;
    }
}
