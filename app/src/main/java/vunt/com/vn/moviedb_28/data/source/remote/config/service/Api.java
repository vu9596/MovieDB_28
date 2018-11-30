package vunt.com.vn.moviedb_28.data.source.remote.config.service;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;
import vunt.com.vn.moviedb_28.data.source.remote.config.response.CategoryResult;
import vunt.com.vn.moviedb_28.data.source.remote.config.response.GenreResult;

public interface Api {

    @GET("/3/movie/popular")
    Single<CategoryResult> getPopular(@Query("page") int page);

    @GET("/3/movie/now_playing")
    Single<CategoryResult> getNowPlaying(@Query("page") int page);

    @GET("/3/movie/upcoming")
    Single<CategoryResult> getUpComing(@Query("page") int page);

    @GET("/3/movie/top_rated")
    Single<CategoryResult> getTopRate(@Query("page") int page);

    @GET("/3/genre/movie/list")
    Single<GenreResult> getGenre();

    @GET("/3/discover/movie")
    Single<CategoryResult> getMoviesByGenre(@Query("page") int page,
                                            @Query("with_genres") String genreId);
}
