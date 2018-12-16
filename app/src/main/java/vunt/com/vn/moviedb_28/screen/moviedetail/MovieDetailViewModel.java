package vunt.com.vn.moviedb_28.screen.moviedetail;

import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import vunt.com.vn.moviedb_28.data.model.Movie;
import vunt.com.vn.moviedb_28.data.repository.MovieRepository;
import vunt.com.vn.moviedb_28.screen.producer.ProduceNavigator;

public class MovieDetailViewModel {
    private static final String APPEND_TO_MOVIE_DETAIL = "videos,credits";
    public final ObservableField<Movie> movieObservable = new ObservableField<>();

    public final ObservableBoolean isFavoriteMovieObservable = new ObservableBoolean();

    public final ObservableBoolean isLoadingSuccess = new ObservableBoolean();

    private MovieRepository mMovieRepository;
    private CompositeDisposable mCompositeDisposable = new CompositeDisposable();

    private OnChangeVideoListener mOnChangeVideoListener;

    private MovieDetailNavigator mMovieDetailNavigator;

    private ProduceNavigator mProduceNavigator;

    public MovieDetailViewModel(int movieId, MovieRepository movieRepository) {
        mMovieRepository = movieRepository;
        loadMovie(movieId);
        checkFavoriteMovie(movieId);
    }

    public void setNavigator(MovieDetailNavigator navigator) {
        mMovieDetailNavigator = navigator;
    }

    public void setProduceNavigator(ProduceNavigator produceNavigator) {
        mProduceNavigator = produceNavigator;
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
                        isLoadingSuccess.set(true);
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
        isLoadingSuccess.set(true);
    }

    public void back() {
        mMovieDetailNavigator.back();
    }

    private void checkFavoriteMovie(int movieId) {
        isFavoriteMovieObservable.set(!mMovieRepository.canAddFavarite(movieId));
    }

    public void clear() {
        mCompositeDisposable.clear();
    }

    public void onFavoriteClick(Movie movie) {
        if (movie == null) {
            return;
        }
        if (isFavoriteMovieObservable.get()) {
            deleteFavoriteMovie(movie);
            return;
        }
        adddFavoriteMovie(movie);
    }

    private void adddFavoriteMovie(Movie movie) {
        boolean isSuccess = mMovieRepository.addFavariteMovie(movie);
        if (isSuccess) {
            isFavoriteMovieObservable.set(true);
        }
    }

    private void deleteFavoriteMovie(Movie movie) {
        boolean isSuccess = mMovieRepository.deleteFavoriteMovie(movie);
        if (isSuccess) {
            isFavoriteMovieObservable.set(false);
        }
    }
}
