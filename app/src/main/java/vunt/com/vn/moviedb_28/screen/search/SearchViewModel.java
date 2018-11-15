package vunt.com.vn.moviedb_28.screen.search;

import android.databinding.BaseObservable;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;

import vunt.com.vn.moviedb_28.data.model.Movie;
import vunt.com.vn.moviedb_28.data.repository.MovieRepository;

public class SearchViewModel extends BaseObservable {
    private MovieRepository mMovieRepository;

    public final ObservableList<Movie> searchedMoviesObservable = new ObservableArrayList<>();

    private SearchNavigator mSearchNavigator;

    public SearchViewModel(MovieRepository movieRepository) {
        mMovieRepository = movieRepository;
        searchMovie();
    }

    public void setSearchNavigator(SearchNavigator searchNavigator) {
        mSearchNavigator = searchNavigator;
    }

    public void onFilterClick() {
        mSearchNavigator.showSearchFilter();
    }

    private void searchMovie() {
    }

    private void handleError(String message) {
        //TODO handle error
    }
}
