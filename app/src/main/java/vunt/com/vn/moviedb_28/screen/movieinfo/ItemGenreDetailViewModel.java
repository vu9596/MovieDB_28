package vunt.com.vn.moviedb_28.screen.movieinfo;

import android.databinding.BaseObservable;
import android.databinding.ObservableField;

import vunt.com.vn.moviedb_28.data.model.Genre;

public class ItemGenreDetailViewModel extends BaseObservable {
    public final ObservableField<Genre> genreObservable = new ObservableField<>();

    public void setGenre(Genre genre) {
        genreObservable.set(genre);
    }
}
