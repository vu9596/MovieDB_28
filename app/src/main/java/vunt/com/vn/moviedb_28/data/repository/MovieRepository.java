package vunt.com.vn.moviedb_28.data.repository;

import java.util.List;

import io.reactivex.Single;
import vunt.com.vn.moviedb_28.data.model.Genre;
import vunt.com.vn.moviedb_28.data.model.Movie;
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

    @Override
    public Single<List<Movie>> getPopular(int page) {
        return mMovieRemoteDataSource.getPopular(page);
    }

    @Override
    public Single<List<Movie>> getNowPlaying(int page) {
        return mMovieRemoteDataSource.getNowPlaying(page);
    }

    @Override
    public Single<List<Movie>> getUpComing(int page) {
        return mMovieRemoteDataSource.getUpComing(page);
    }

    @Override
    public Single<List<Movie>> getTopRate(int page) {
        return mMovieRemoteDataSource.getTopRate(page);
    }

    @Override
    public Single<List<Genre>> getGenres() {
        return mMovieRemoteDataSource.getGenres();
    }

    @Override
    public Single<List<Movie>> getMoviesByGenre(int page, String genreId) {
        return mMovieRemoteDataSource.getMoviesByGenre(page, genreId);
    }

    @Override
    public Single<Movie> getMovieDetail(int movieId, String append) {
        return mMovieRemoteDataSource.getMovieDetail(movieId, append);
    }
}
