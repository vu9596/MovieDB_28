package vunt.com.vn.moviedb_28.screen.movies;

import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;

import vunt.com.vn.moviedb_28.data.model.Movie;

public class MoviesViewModel {
    private int mLoadBy;
    private String mKey;
    public final ObservableList<Movie> moviesObservable = new ObservableArrayList<>();

    public MoviesViewModel(int loadBy, String key) {
        mKey = key;
        mLoadBy = loadBy;
        loadMovies();
    }

    private void loadMovies() {
    }
}
