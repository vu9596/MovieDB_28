package vunt.com.vn.moviedb_28.screen.home;

import android.databinding.BaseObservable;
import android.databinding.ObservableBoolean;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import vunt.com.vn.moviedb_28.data.model.Genres;
import vunt.com.vn.moviedb_28.data.model.Movie;

public class HomeViewModel extends BaseObservable {

    public ObservableBoolean mIsLoadMorePopular = new ObservableBoolean();
    public ObservableBoolean mIsLoadMoreNowPlaying = new ObservableBoolean();
    public ObservableBoolean mIsLoadMoreTopRate = new ObservableBoolean();
    public ObservableBoolean mIsLoadMoreUpComing = new ObservableBoolean();

    private CategoryAdapter mPopularAdapter, mNowPlayingAdapter, mTopRateAdapter, mUpComingAdapter;
    private GenresAdapter mGenresAdapter;
    private LinearLayoutManager mGenresLayoutManager;

    public HomeViewModel(CategoryAdapter popularAdapter, CategoryAdapter nowPlayingAdapter,
                         CategoryAdapter topRateAdapter, CategoryAdapter upComingAdapter,
                         GenresAdapter genresAdapter) {
        mPopularAdapter = popularAdapter;
        mNowPlayingAdapter = nowPlayingAdapter;
        mTopRateAdapter = topRateAdapter;
        mUpComingAdapter = upComingAdapter;
        mGenresAdapter = genresAdapter;
        initData();
    }

    public HomeViewModel(CategoryAdapter popularAdapter) {
        mPopularAdapter = popularAdapter;
    }

    public void initData() {
        List<Movie> movies = new ArrayList<>();
        Movie movie1 = new Movie();
        movies.add(movie1);
        movies.add(movie1);
        movies.add(movie1);
        mPopularAdapter.replaceData(movies);
        mNowPlayingAdapter.replaceData(movies);
        mTopRateAdapter.replaceData(movies);
        mUpComingAdapter.replaceData(movies);

        Genres genres = new Genres();
        List<Genres> genresList = new ArrayList<>();
        genresList.add(genres);
        genresList.add(genres);
        genresList.add(genres);
        genresList.add(genres);
        mGenresAdapter.addData(genresList);
        Log.e("aaa", "genres size" + genresList.size());
    }

    public LinearLayoutManager getGenresLayoutManager() {
        return mGenresLayoutManager;
    }

    public void setGenresLayoutManager(LinearLayoutManager genresLayoutManager) {
        Log.e("aaa", "genres layout manager");
        mGenresLayoutManager = genresLayoutManager;
    }

    public CategoryAdapter getPopularAdapter() {
        return mPopularAdapter;
    }

    public CategoryAdapter getNowPlayingAdapter() {
        return mNowPlayingAdapter;
    }

    public CategoryAdapter getTopRateAdapter() {
        return mTopRateAdapter;
    }

    public CategoryAdapter getUpComingAdapter() {
        return mUpComingAdapter;
    }

    public GenresAdapter getGenresAdapter() {
        return mGenresAdapter;
    }

    public void onClickLoadMoreNowPlaying(View view) {
        List<Movie> movies = new ArrayList<>();
        Movie movie1 = new Movie();
        movies.add(movie1);
        movies.add(movie1);
        movies.add(movie1);
        mNowPlayingAdapter.addData(movies);
        mIsLoadMoreNowPlaying.set(true);
    }

    public void onClickLoadMorePopular(View view) {
        List<Movie> movies = new ArrayList<>();
        Movie movie1 = new Movie();
        movies.add(movie1);
        movies.add(movie1);
        movies.add(movie1);
        mPopularAdapter.addData(movies);
        mIsLoadMorePopular.set(true);
    }

    public void onClickLoadMoreTopRate(View view) {
        List<Movie> movies = new ArrayList<>();
        Movie movie1 = new Movie();
        movies.add(movie1);
        movies.add(movie1);
        movies.add(movie1);
        mTopRateAdapter.addData(movies);
        mIsLoadMoreTopRate.set(true);
    }

    public void onClickLoadMoreUpComing(View view) {
        List<Movie> movies = new ArrayList<>();
        Movie movie1 = new Movie();
        movies.add(movie1);
        movies.add(movie1);
        movies.add(movie1);
        mUpComingAdapter.addData(movies);
        mIsLoadMoreUpComing.set(true);
    }
}
