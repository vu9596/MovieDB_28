package vunt.com.vn.moviedb_28.screen.home;

import android.databinding.BaseObservable;
import android.databinding.ObservableField;

import vunt.com.vn.moviedb_28.data.model.Movie;

public class ItemMovieListViewModel extends BaseObservable {
    public ObservableField<Movie> mMovieObservableField = new ObservableField<>();

    public void setMovie(Movie movie) {
        mMovieObservableField.set(movie);
    }
}
