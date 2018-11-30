package vunt.com.vn.moviedb_28.screen.movies;

import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import vunt.com.vn.moviedb_28.data.model.CategoryKey;
import vunt.com.vn.moviedb_28.data.model.Movie;
import vunt.com.vn.moviedb_28.data.repository.MovieRepository;
import vunt.com.vn.moviedb_28.screen.home.HomeViewModel;
import vunt.com.vn.moviedb_28.util.Constant;

public class MoviesViewModel {
    private String mKey;
    public final ObservableList<Movie> moviesObservable = new ObservableArrayList<>();

    private MovieRepository mMovieRepository;
    private CompositeDisposable mCompositeDisposable = new CompositeDisposable();
    private int mCurrentPage;

    public MoviesViewModel(MovieRepository movieRepository, int loadBy, String key) {
        mMovieRepository = movieRepository;
        mCurrentPage = Constant.FIRST_PAGE;
        mKey = key;
        loadMovies(loadBy);
    }

    private void loadMovies(int loadBy) {
        if (loadBy == HomeViewModel.GENRE_SOURCE) {
            loadMoviesByGenre();
            return;
        }
        loadMoviesByCategory();
    }

    private void loadMoviesByCategory() {
        switch (mKey) {
            case CategoryKey.CATEGORY_POPULAR:
                loadPopularMovies();
                break;
            case CategoryKey.CATEGORY_NOW_PLAYING:
                loadNowPlayingMovies();
                break;
            case CategoryKey.CATEGORY_UP_COMING:
                loadUpComingMovies();
                break;
            case CategoryKey.CATEGORY_TOP_RATE:
                loadTopRateMovies();
                break;
        }
    }

    private void loadTopRateMovies() {
        Disposable disposable = mMovieRepository.getTopRate(Constant.FIRST_PAGE)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<Movie>>() {
                    @Override
                    public void accept(List<Movie> movies) {
                        moviesObservable.addAll(movies);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) {
                        handleError(throwable.getMessage());
                    }
                });
        mCompositeDisposable.add(disposable);
    }

    private void loadUpComingMovies() {
        Disposable disposable = mMovieRepository.getUpComing(Constant.FIRST_PAGE)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<Movie>>() {
                    @Override
                    public void accept(List<Movie> movies) {
                        moviesObservable.addAll(movies);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) {
                        handleError(throwable.getMessage());
                    }
                });
        mCompositeDisposable.add(disposable);
    }

    private void loadNowPlayingMovies() {
        Disposable disposable = mMovieRepository.getNowPlaying(Constant.FIRST_PAGE)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<Movie>>() {
                    @Override
                    public void accept(List<Movie> movies) {
                        moviesObservable.addAll(movies);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) {
                        handleError(throwable.getMessage());
                    }
                });
        mCompositeDisposable.add(disposable);
    }

    private void loadPopularMovies() {
        Disposable disposable = mMovieRepository.getPopular(Constant.FIRST_PAGE)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<Movie>>() {
                    @Override
                    public void accept(List<Movie> movies) {
                        moviesObservable.addAll(movies);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) {
                        handleError(throwable.getMessage());
                    }
                });
        mCompositeDisposable.add(disposable);
    }

    private void loadMoviesByGenre() {
        Disposable disposable = mMovieRepository.getMoviesByGenre(mCurrentPage, mKey)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<Movie>>() {
                    @Override
                    public void accept(List<Movie> movies) {
                        moviesObservable.addAll(movies);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) {
                        handleError(throwable.getMessage());
                    }
                });
        mCompositeDisposable.add(disposable);
    }

    public void clear() {
        mCompositeDisposable.clear();
    }

    private void handleError(String message) {
        //TODO handle error
    }
}
