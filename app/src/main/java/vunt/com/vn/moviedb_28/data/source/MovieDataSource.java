package vunt.com.vn.moviedb_28.data.source;

import java.util.List;

import io.reactivex.Single;
import vunt.com.vn.moviedb_28.data.model.Genre;
import vunt.com.vn.moviedb_28.data.model.Movie;

public interface MovieDataSource {

    interface Local extends MovieDataSource {

    }

    interface Remote extends MovieDataSource {
        Single<List<Movie>> getPopular(int page);

        Single<List<Movie>> getNowPlaying(int page);

        Single<List<Movie>> getUpComing(int page);

        Single<List<Movie>> getTopRate(int page);

        Single<List<Genre>> getGenres();

        Single<List<Movie>> getMoviesByGenre(int page, String genreId);

        Single<Movie> getMovieDetail(int movieId, String append);
    }

}
