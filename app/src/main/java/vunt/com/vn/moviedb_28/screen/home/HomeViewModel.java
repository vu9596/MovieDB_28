package vunt.com.vn.moviedb_28.screen.home;

import android.databinding.BaseObservable;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableList;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import vunt.com.vn.moviedb_28.data.model.Genre;
import vunt.com.vn.moviedb_28.data.model.Movie;
import vunt.com.vn.moviedb_28.data.repository.MovieRepository;
import vunt.com.vn.moviedb_28.util.Constant;

public class HomeViewModel extends BaseObservable {

    public static final String BUNDLE_SOURCE = "BUNDLE_SOURCE";
    public static final String BUNDLE_KEY = "BUNDLE_KEY";
    public static final String BUNDLE_NAME = "BUNDLE_NAME";
    public static final int GENRE_SOURCE = 0;
    public static final int CATEGORY_SOURCE = 1;

    public ObservableBoolean mIsLoadMorePopular = new ObservableBoolean();
    public ObservableBoolean mIsLoadMoreNowPlaying = new ObservableBoolean();
    public ObservableBoolean mIsLoadMoreTopRate = new ObservableBoolean();
    public ObservableBoolean mIsLoadMoreUpComing = new ObservableBoolean();

    private MovieRepository mMovieRepository;
    private CompositeDisposable mCompositeDisposable = new CompositeDisposable();

    private List<Movie> mMorePopularMovies = new ArrayList<>();
    private List<Movie> mMoreNowPlayingMovies = new ArrayList<>();
    private List<Movie> mMoreUpComingMovies = new ArrayList<>();
    private List<Movie> mMoreTopRateMovies = new ArrayList<>();

    public final ObservableList<Movie> popularMoviesObservable = new ObservableArrayList<>();
    public final ObservableList<Movie> nowPlayingMoviesObservable = new ObservableArrayList<>();
    public final ObservableList<Movie> upComingMoviesObservable = new ObservableArrayList<>();
    public final ObservableList<Movie> topRateMoviesObservable = new ObservableArrayList<>();
    public final ObservableList<Genre> genresObservable = new ObservableArrayList<>();

    private HomeNavigator mNavigator;

    public HomeViewModel(MovieRepository movieRepository) {
        mMovieRepository = movieRepository;
        initData();
    }

    public void setNavigator(HomeNavigator navigator) {
        mNavigator = navigator;
    }

    public void initData() {
        loadPopularMovies();
        loadNowPlayingMovies();
        loadUpComingMovies();
        loadTopRateMovies();
        loadGenre();
    }

    private void loadGenre() {
        Disposable disposable = mMovieRepository.getGenres()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<Genre>>() {
                    @Override
                    public void accept(List<Genre> genres) {
                        genresObservable.addAll(genres);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) {
                        handleError(throwable.getMessage());
                    }
                });
        mCompositeDisposable.add(disposable);
    }

    private void loadTopRateMovies() {
        Disposable disposable = mMovieRepository.getTopRate(Constant.FIRST_PAGE)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<Movie>>() {
                    @Override
                    public void accept(List<Movie> movies) {
                        topRateMoviesObservable.addAll(movies.subList(
                                0,
                                movies.size() / Constant.SEPARATE_UNIT));
                        mMoreTopRateMovies.addAll(movies.subList(
                                movies.size() / Constant.SEPARATE_UNIT + Constant.INDEX_UNIT,
                                movies.size() - Constant.INDEX_UNIT));
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
                        upComingMoviesObservable.addAll(movies.subList(
                                0,
                                movies.size() / Constant.SEPARATE_UNIT));
                        mMoreUpComingMovies.addAll(movies.subList(
                                movies.size() / Constant.SEPARATE_UNIT + Constant.INDEX_UNIT,
                                movies.size() - Constant.INDEX_UNIT));
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
                        nowPlayingMoviesObservable.addAll(movies.subList(
                                0,
                                movies.size() / Constant.SEPARATE_UNIT));
                        mMoreNowPlayingMovies.addAll(movies.subList(
                                movies.size() / Constant.SEPARATE_UNIT + Constant.INDEX_UNIT,
                                movies.size() - Constant.INDEX_UNIT));
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
                        popularMoviesObservable.addAll(movies.subList(
                                0,
                                movies.size() / Constant.SEPARATE_UNIT));
                        mMorePopularMovies.addAll(movies.subList(
                                movies.size() / Constant.SEPARATE_UNIT + Constant.INDEX_UNIT,
                                movies.size() - Constant.INDEX_UNIT));
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) {
                        handleError(throwable.getMessage());
                    }
                });
        mCompositeDisposable.add(disposable);
    }

    public void onClickLoadMoreNowPlaying(View view) {
        nowPlayingMoviesObservable.addAll(mMoreNowPlayingMovies);
        mIsLoadMoreNowPlaying.set(true);
    }

    public void onClickLoadMorePopular(View view) {
        popularMoviesObservable.addAll(mMorePopularMovies);
        mIsLoadMorePopular.set(true);
    }

    public void onClickLoadMoreTopRate(View view) {
        topRateMoviesObservable.addAll(mMoreTopRateMovies);
        mIsLoadMoreTopRate.set(true);
    }

    public void onClickLoadMoreUpComing(View view) {
        upComingMoviesObservable.addAll(mMoreUpComingMovies);
        mIsLoadMoreUpComing.set(true);
    }

    public void clear() {
        mCompositeDisposable.clear();
    }

    private void handleError(String message) {
        //TODO handle error
    }

    public void onCategoryClick(View view, String key, String name) {
        Bundle bundle = new Bundle();
        bundle.putInt(BUNDLE_SOURCE, CATEGORY_SOURCE);
        bundle.putString(BUNDLE_KEY, key);
        bundle.putString(BUNDLE_NAME, name);
        mNavigator.showMovies(bundle);
    }
}
