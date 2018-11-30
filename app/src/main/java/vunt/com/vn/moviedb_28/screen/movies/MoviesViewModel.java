package vunt.com.vn.moviedb_28.screen.movies;

import android.databinding.ObservableArrayList;
import android.databinding.ObservableBoolean;
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
    private int mLoadBy;
    private String mKey;
    public final ObservableList<Movie> moviesObservable = new ObservableArrayList<>();

    private MovieRepository mMovieRepository;
    private CompositeDisposable mCompositeDisposable = new CompositeDisposable();
    private int mCurrentPage;

    public final ObservableBoolean isLoadMore = new ObservableBoolean(false);

    public MoviesViewModel(MovieRepository movieRepository, int loadBy, String key) {
        mLoadBy = loadBy;
        mMovieRepository = movieRepository;
        mCurrentPage = Constant.FIRST_PAGE;
        mKey = key;
        isLoadMore.set(false);
        loadMovies(mLoadBy);
    }

    public int getLoadBy() {
        return mLoadBy;
    }

    public void loadMovies(int loadBy) {
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
        Disposable disposable = mMovieRepository.getTopRate(mCurrentPage)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<Movie>>() {
                    @Override
                    public void accept(List<Movie> movies) {
                        moviesObservable.addAll(movies);
                        isLoadMore.set(false);
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
        Disposable disposable = mMovieRepository.getUpComing(mCurrentPage)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<Movie>>() {
                    @Override
                    public void accept(List<Movie> movies) {
                        moviesObservable.addAll(movies);
                        isLoadMore.set(false);
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
        Disposable disposable = mMovieRepository.getNowPlaying(mCurrentPage)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<Movie>>() {
                    @Override
                    public void accept(List<Movie> movies) {
                        moviesObservable.addAll(movies);
                        isLoadMore.set(false);
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
        Disposable disposable = mMovieRepository.getPopular(mCurrentPage)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<Movie>>() {
                    @Override
                    public void accept(List<Movie> movies) {
                        moviesObservable.addAll(movies);
                        isLoadMore.set(false);
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
                        isLoadMore.set(false);
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
        isLoadMore.set(false);
    }

    public void increaseCurrentPage() {
        mCurrentPage += Constant.INDEX_UNIT;
    }
}
