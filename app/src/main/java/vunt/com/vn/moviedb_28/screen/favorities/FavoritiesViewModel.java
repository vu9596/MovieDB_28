package vunt.com.vn.moviedb_28.screen.favorities;

import android.databinding.BaseObservable;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;

import io.reactivex.disposables.CompositeDisposable;
import vunt.com.vn.moviedb_28.data.model.Movie;
import vunt.com.vn.moviedb_28.data.repository.MovieRepository;

public class FavoritiesViewModel extends BaseObservable {
    private MovieRepository mMovieRepository;
    private CompositeDisposable mCompositeDisposable = new CompositeDisposable();

    public final ObservableList<Movie> favoriteMoviesObservable = new ObservableArrayList<>();

    public FavoritiesViewModel(MovieRepository movieRepository) {
        mMovieRepository = movieRepository;
        loadFavoriteMovies();
    }

    private void loadFavoriteMovies() {
        favoriteMoviesObservable.addAll(mMovieRepository.getFavoriteMovies());
    }

    public void refreshFavoriteMovies() {
        favoriteMoviesObservable.clear();
        favoriteMoviesObservable.addAll(mMovieRepository.getFavoriteMovies());
    }

    public boolean deleteFavoriteMovie(Movie movie) {
        boolean isSuccess = mMovieRepository.deleteFavoriteMovie(movie);
        if (isSuccess) {
            refreshFavoriteMovies();
        }
        return isSuccess;
    }

    public void clear() {
        mCompositeDisposable.clear();
    }

    private void handleError(String message) {
        //TODO handle error
    }
}
