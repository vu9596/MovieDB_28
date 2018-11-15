package vunt.com.vn.moviedb_28.screen.moviedetail;

import android.databinding.ObservableField;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import vunt.com.vn.moviedb_28.data.model.Movie;
import vunt.com.vn.moviedb_28.data.repository.MovieRepository;

public class MovieDetailViewModel {
    private static final String APPEND_TO_MOVIE_DETAIL = "videos,credits";
    public final ObservableField<Movie> movieObservable = new ObservableField<>();
    private MovieRepository mMovieRepository;
    private CompositeDisposable mCompositeDisposable = new CompositeDisposable();

    private OnChangeVideoListener mOnChangeVideoListener;

    public MovieDetailViewModel(int movieId, MovieRepository movieRepository) {
        mMovieRepository = movieRepository;
        loadMovie(movieId);
    }

    public void setOnChangeVideoListener(OnChangeVideoListener listener) {
        mOnChangeVideoListener = listener;
    }

    private void loadMovie(final int movieId) {
        Disposable disposable = mMovieRepository.getMovieDetail(movieId, APPEND_TO_MOVIE_DETAIL)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Movie>() {
                    @Override
                    public void accept(Movie movie) {
                        movieObservable.set(movie);
                        if (movie.getVideoResult().getVideos().size() > 0) {
                            mOnChangeVideoListener.setVideoId(
                                    movie.getVideoResult().getVideos().get(0).getKey());
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) {
                        handleError(throwable.getMessage());
                    }
                });
        mCompositeDisposable.add(disposable);
    }

    public Movie getMovie() {
        return movieObservable.get();
    }

    private void handleError(String message) {
        //TODO handle error
    }

    public void clear() {
        mCompositeDisposable.clear();
    }
}
