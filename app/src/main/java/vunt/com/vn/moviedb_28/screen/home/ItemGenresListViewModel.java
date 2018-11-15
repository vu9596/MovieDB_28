package vunt.com.vn.moviedb_28.screen.home;

import android.databinding.BaseObservable;
import android.databinding.ObservableField;
import android.view.View;

import vunt.com.vn.moviedb_28.data.model.Genre;

public class ItemGenresListViewModel extends BaseObservable {
    public ObservableField<Genre> mGenresObservableField = new ObservableField<>();
    private GenresAdapter.ItemClickListener mItemClickListener;

    public ItemGenresListViewModel(GenresAdapter.ItemClickListener listener) {
        mItemClickListener = listener;
    }

    public void setGenres(Genre genre) {
        mGenresObservableField.set(genre);
    }

    public void onItemClicked(View view) {
        if (mItemClickListener == null || mGenresObservableField.get() == null) {
            return;
        }
        mItemClickListener.onItemClick(mGenresObservableField.get());
    }
}
