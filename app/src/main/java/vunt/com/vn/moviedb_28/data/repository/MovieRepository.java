package vunt.com.vn.moviedb_28.data.repository;

import java.util.List;

import io.reactivex.Single;
import vunt.com.vn.moviedb_28.data.model.Genre;
import vunt.com.vn.moviedb_28.data.model.Movie;
import vunt.com.vn.moviedb_28.data.source.MovieDataSource;
import vunt.com.vn.moviedb_28.data.source.local.MovieLocalDataSource;
import vunt.com.vn.moviedb_28.data.source.remote.MovieRemoteDataSource;
import vunt.com.vn.moviedb_28.data.source.remote.config.response.CategoryResult;

public class MovieRepository implements MovieDataSource.Local,
        MovieDataSource.Remote {

    private static MovieRepository sInstance;
    private MovieRemoteDataSource mMovieRemoteDataSource;
    private MovieLocalDataSource mMovieLocalDataSource;

    private MovieRepository(MovieRemoteDataSource movieRemoteDataSource,
                            MovieLocalDataSource movieLocalDataSource) {
        mMovieRemoteDataSource = movieRemoteDataSource;
        mMovieLocalDataSource = movieLocalDataSource;
    }

    public static MovieRepository getInstance(MovieRemoteDataSource movieRemoteDataSource,
                                              MovieLocalDataSource movieLocalDataSource) {
        if (sInstance == null) {
            sInstance = new MovieRepository(movieRemoteDataSource, movieLocalDataSource);
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

    @Override
    public Single<List<Movie>> getMoviesByProduce(int page, String produceId) {
        return mMovieRemoteDataSource.getMoviesByProduce(page, produceId);
    }

    @Override
    public Single<List<Movie>> getMoviesByActor(int page, String actorId) {
        return mMovieRemoteDataSource.getMoviesByActor(page, actorId);
    }

    @Override
    public Single<CategoryResult> searchMovie(String type, String keyword, int page) {
        return mMovieRemoteDataSource.searchMovie(type, keyword, page);
    }

    @Override
    public List<Movie> getFavoriteMovies() {
        return mMovieLocalDataSource.getFavoriteMovies();
    }

    @Override
    public boolean addFavariteMovie(Movie movie) {
        return mMovieLocalDataSource.addFavariteMovie(movie);
    }

    @Override
    public boolean deleteFavoriteMovie(Movie movie) {
        return mMovieLocalDataSource.deleteFavoriteMovie(movie);
    }

    @Override
    public boolean canAddFavarite(Movie movie) {
        return mMovieLocalDataSource.canAddFavarite(movie);
    }

    @Override
    public boolean canAddFavarite(int movieId) {
        return mMovieLocalDataSource.canAddFavarite(movieId);
    }
}
