package vunt.com.vn.moviedb_28.screen.home;

import android.databinding.BaseObservable;
import android.databinding.ObservableField;
import android.view.View;

import vunt.com.vn.moviedb_28.data.model.Movie;

public class ItemMovieListViewModel extends BaseObservable {
    public ObservableField<Movie> mMovieObservableField = new ObservableField<>();
    private CategoriesAdapter.ItemClickListener mItemClickListener;

    public ItemMovieListViewModel(CategoriesAdapter.ItemClickListener itemClickListener) {
        mItemClickListener = itemClickListener;
    }

    public void setMovie(Movie movie) {
        mMovieObservableField.set(movie);
    }

    public void onItemClicked(View view) {
        if (mItemClickListener == null || mMovieObservableField.get() == null) {
            return;
        }
        mItemClickListener.onMovieItemClick(mMovieObservableField.get());
    }
}
