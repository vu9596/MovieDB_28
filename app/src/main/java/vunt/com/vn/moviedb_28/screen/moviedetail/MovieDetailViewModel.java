package vunt.com.vn.moviedb_28.screen.moviedetail;

import android.databinding.ObservableField;

import io.reactivex.disposables.CompositeDisposable;
import vunt.com.vn.moviedb_28.data.model.Movie;
import vunt.com.vn.moviedb_28.data.repository.MovieRepository;

public class MovieDetailViewModel {
    public final ObservableField<Movie> mMovieObservable = new ObservableField<>();
    private MovieRepository mMovieRepository;
    private CompositeDisposable mCompositeDisposable = new CompositeDisposable();

    public MovieDetailViewModel(int movieId, MovieRepository movieRepository) {
        mMovieRepository = movieRepository;
        loadMovie(movieId);
    }

    private void loadMovie(int movieId) {
    }

    public Movie getMovie() {
        return mMovieObservable.get();
    }
}
