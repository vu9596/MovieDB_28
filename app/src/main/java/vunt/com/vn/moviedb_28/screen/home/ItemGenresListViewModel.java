package vunt.com.vn.moviedb_28.screen.home;

import android.databinding.BaseObservable;
import android.databinding.ObservableField;

import vunt.com.vn.moviedb_28.data.model.Genres;

public class ItemGenresListViewModel extends BaseObservable {
    public ObservableField<Genres> mGenresObservableField = new ObservableField<>();

    public void setGenres(Genres genres) {
        mGenresObservableField.set(genres);
    }
}
