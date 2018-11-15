package vunt.com.vn.moviedb_28.screen.home;

import android.databinding.BaseObservable;
import android.databinding.ObservableBoolean;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import vunt.com.vn.moviedb_28.data.model.Genre;
import vunt.com.vn.moviedb_28.data.model.Movie;

public class HomeViewModel extends BaseObservable {

    public ObservableBoolean mIsLoadMorePopular = new ObservableBoolean();
    public ObservableBoolean mIsLoadMoreNowPlaying = new ObservableBoolean();
    public ObservableBoolean mIsLoadMoreTopRate = new ObservableBoolean();
    public ObservableBoolean mIsLoadMoreUpComing = new ObservableBoolean();

    private CategoriesAdapter mPopularAdapter, mNowPlayingAdapter, mTopRateAdapter, mUpComingAdapter;
    private GenresAdapter mGenresAdapter;
    private LinearLayoutManager mGenresLayoutManager;

    public HomeViewModel(CategoriesAdapter popularAdapter, CategoriesAdapter nowPlayingAdapter,
                         CategoriesAdapter topRateAdapter, CategoriesAdapter upComingAdapter,
                         GenresAdapter genresAdapter) {
        mPopularAdapter = popularAdapter;
        mNowPlayingAdapter = nowPlayingAdapter;
        mTopRateAdapter = topRateAdapter;
        mUpComingAdapter = upComingAdapter;
        mGenresAdapter = genresAdapter;
        initData();
    }

    public HomeViewModel(CategoriesAdapter popularAdapter) {
        mPopularAdapter = popularAdapter;
    }

    public void initData() {
        List<Movie> movies = new ArrayList<>();
        Movie movie = new Movie();
        movies.add(movie);
        movies.add(movie);
        movies.add(movie);
        mPopularAdapter.replaceData(movies);
        mNowPlayingAdapter.replaceData(movies);
        mTopRateAdapter.replaceData(movies);
        mUpComingAdapter.replaceData(movies);

        Genre genre = new Genre();
        List<Genre> genres = new ArrayList<>();
        genres.add(genre);
        genres.add(genre);
        genres.add(genre);
        genres.add(genre);
        mGenresAdapter.addData(genres);
    }

    public LinearLayoutManager getGenresLayoutManager() {
        return mGenresLayoutManager;
    }

    public void setGenresLayoutManager(LinearLayoutManager genresLayoutManager) {
        mGenresLayoutManager = genresLayoutManager;
    }

    public CategoriesAdapter getPopularAdapter() {
        return mPopularAdapter;
    }

    public CategoriesAdapter getNowPlayingAdapter() {
        return mNowPlayingAdapter;
    }

    public CategoriesAdapter getTopRateAdapter() {
        return mTopRateAdapter;
    }

    public CategoriesAdapter getUpComingAdapter() {
        return mUpComingAdapter;
    }

    public GenresAdapter getGenresAdapter() {
        return mGenresAdapter;
    }

    public void onClickLoadMoreNowPlaying(View view) {
        List<Movie> movies = new ArrayList<>();
        Movie movie = new Movie();
        movies.add(movie);
        movies.add(movie);
        movies.add(movie);
        mNowPlayingAdapter.addData(movies);
        mIsLoadMoreNowPlaying.set(true);
    }

    public void onClickLoadMorePopular(View view) {
        List<Movie> movies = new ArrayList<>();
        Movie movie = new Movie();
        movies.add(movie);
        movies.add(movie);
        movies.add(movie);
        mPopularAdapter.addData(movies);
        mIsLoadMorePopular.set(true);
    }

    public void onClickLoadMoreTopRate(View view) {
        List<Movie> movies = new ArrayList<>();
        Movie movie = new Movie();
        movies.add(movie);
        movies.add(movie);
        movies.add(movie);
        mTopRateAdapter.addData(movies);
        mIsLoadMoreTopRate.set(true);
    }

    public void onClickLoadMoreUpComing(View view) {
        List<Movie> movies = new ArrayList<>();
        Movie movie = new Movie();
        movies.add(movie);
        movies.add(movie);
        movies.add(movie);
        mUpComingAdapter.addData(movies);
        mIsLoadMoreUpComing.set(true);
    }
}
