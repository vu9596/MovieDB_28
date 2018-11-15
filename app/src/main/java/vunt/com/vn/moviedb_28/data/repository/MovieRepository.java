package vunt.com.vn.moviedb_28.data.repository;

import vunt.com.vn.moviedb_28.data.source.MovieDataSource;
import vunt.com.vn.moviedb_28.data.source.remote.MovieRemoteDataSource;

public class MovieRepository implements MovieDataSource.Local,
        MovieDataSource.Remote {

    private static MovieRepository sInstance;
    private MovieRemoteDataSource mMovieRemoteDataSource;

    private MovieRepository(MovieRemoteDataSource movieRemoteDataSource) {
        mMovieRemoteDataSource = movieRemoteDataSource;
    }

    public static MovieRepository getInstance(MovieRemoteDataSource movieRemoteDataSource) {
        if (sInstance == null) {
            sInstance = new MovieRepository(movieRemoteDataSource);
        }
        return sInstance;
    }
}
