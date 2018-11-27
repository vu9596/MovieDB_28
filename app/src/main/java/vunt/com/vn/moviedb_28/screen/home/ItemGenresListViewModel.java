package vunt.com.vn.moviedb_28.screen.home;

import android.databinding.BaseObservable;
import android.databinding.ObservableField;

import vunt.com.vn.moviedb_28.data.model.Genre;

public class ItemGenresListViewModel extends BaseObservable {
    public ObservableField<Genre> mGenresObservableField = new ObservableField<>();

    public void setGenres(Genre genre) {
        mGenresObservableField.set(genre);
    }
}
