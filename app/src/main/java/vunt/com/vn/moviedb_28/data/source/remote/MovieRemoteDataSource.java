package vunt.com.vn.moviedb_28.data.source.remote;

import java.util.List;

import io.reactivex.Single;
import io.reactivex.functions.Function;
import vunt.com.vn.moviedb_28.BuildConfig;
import vunt.com.vn.moviedb_28.data.model.Genre;
import vunt.com.vn.moviedb_28.data.model.Movie;
import vunt.com.vn.moviedb_28.data.source.MovieDataSource;
import vunt.com.vn.moviedb_28.data.source.remote.config.response.CategoryResult;
import vunt.com.vn.moviedb_28.data.source.remote.config.response.GenreResult;
import vunt.com.vn.moviedb_28.data.source.remote.config.service.Api;
import vunt.com.vn.moviedb_28.data.source.remote.config.service.NameServiceClient;

public class MovieRemoteDataSource implements MovieDataSource.Remote {

    private static final String API_KEY = BuildConfig.API_KEY;
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

    @Override
    public Single<List<Movie>> getPopular(int page) {
        return mApi.getPopular(page)
                .map(new Function<CategoryResult, List<Movie>>() {
                    @Override
                    public List<Movie> apply(CategoryResult categoryResult) {
                        return categoryResult.getMovies();
                    }
                });
    }

    @Override
    public Single<List<Movie>> getNowPlaying(int page) {
        return mApi.getNowPlaying(page)
                .map(new Function<CategoryResult, List<Movie>>() {
                    @Override
                    public List<Movie> apply(CategoryResult categoryResult) {
                        return categoryResult.getMovies();
                    }
                });
    }

    @Override
    public Single<List<Movie>> getUpComing(int page) {
        return mApi.getUpComing(page)
                .map(new Function<CategoryResult, List<Movie>>() {
                    @Override
                    public List<Movie> apply(CategoryResult categoryResult) {
                        return categoryResult.getMovies();
                    }
                });
    }

    @Override
    public Single<List<Movie>> getTopRate(int page) {
        return mApi.getTopRate(page)
                .map(new Function<CategoryResult, List<Movie>>() {
                    @Override
                    public List<Movie> apply(CategoryResult categoryResult) {
                        return categoryResult.getMovies();
                    }
                });
    }

    @Override
    public Single<List<Genre>> getGenres() {
        return mApi.getGenre()
                .map(new Function<GenreResult, List<Genre>>() {
                    @Override
                    public List<Genre> apply(GenreResult genreResult) {
                        return genreResult.getGenres();
                    }
                });
    }

    @Override
    public Single<List<Movie>> getMoviesByGenre(int page, String genreId) {
        return mApi.getMoviesByGenre(page, genreId)
                .map(new Function<CategoryResult, List<Movie>>() {
                    @Override
                    public List<Movie> apply(CategoryResult categoryResult) {
                        return categoryResult.getMovies();
                    }
                });
    }

    @Override
    public Single<Movie> getMovieDetail(int movieId, String append) {
        return mApi.getMovieDetail(movieId, append);
    }

    @Override
    public Single<List<Movie>> getMoviesByProduce(int page, String produceId) {
        return mApi.getMoviesByProduce(page, produceId)
                .map(new Function<CategoryResult, List<Movie>>() {
                    @Override
                    public List<Movie> apply(CategoryResult categoryResult) {
                        return categoryResult.getMovies();
                    }
                });
    }

    @Override
    public Single<List<Movie>> getMoviesByActor(int page, String actorId) {
        return mApi.getMoviesByActor(page, actorId)
                .map(new Function<CategoryResult, List<Movie>>() {
                    @Override
                    public List<Movie> apply(CategoryResult categoryResult) {
                        return categoryResult.getMovies();
                    }
                });
    }

    @Override
    public Single<CategoryResult> searchMovie(String type, String keyword, int page) {
        return mApi.searchMovie(type, keyword, page);
    }
}
