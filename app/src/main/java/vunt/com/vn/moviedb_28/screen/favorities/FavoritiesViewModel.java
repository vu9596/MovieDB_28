package vunt.com.vn.moviedb_28.screen.favorities;

import android.databinding.BaseObservable;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;

import vunt.com.vn.moviedb_28.data.model.Movie;
import vunt.com.vn.moviedb_28.data.repository.MovieRepository;

public class FavoritiesViewModel extends BaseObservable {
    private MovieRepository mMovieRepository;

    public final ObservableList<Movie> favoriteMoviesObservable = new ObservableArrayList<>();

    public FavoritiesViewModel(MovieRepository movieRepository) {
        mMovieRepository = movieRepository;
        loadFavoriteMovie();
    }

    private void loadFavoriteMovie() {
    }
}
