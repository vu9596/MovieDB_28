package vunt.com.vn.moviedb_28.screen.home;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import vunt.com.vn.moviedb_28.R;
import vunt.com.vn.moviedb_28.data.model.Genre;
import vunt.com.vn.moviedb_28.data.model.Movie;
import vunt.com.vn.moviedb_28.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {

    private HomeViewModel mViewModel;
    private CategoriesAdapter mPopularAdapter, mNowPlayingAdapter,
            mTopRateAdapter, mUpComingAdapter;
    private GenresAdapter mGenresAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        initViewModel();
        setLayoutManager();
        FragmentHomeBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home,
                container, false);
        binding.setViewModel(mViewModel);
        return binding.getRoot();
    }

    private void setLayoutManager() {
        if (mViewModel != null) {
            LinearLayoutManager genresLayoutManager = new LinearLayoutManager(getContext(),
                    LinearLayoutManager.HORIZONTAL, false);
            mViewModel.setGenresLayoutManager(genresLayoutManager);
        }
    }

    private void initViewModel() {
        List<Movie> popularMovie = new ArrayList<>();
        List<Movie> toprateMovie = new ArrayList<>();
        List<Movie> upComingMovie = new ArrayList<>();
        List<Movie> nowPlayingMovie = new ArrayList<>();
        List<Genre> genres = new ArrayList<>();

        mPopularAdapter = new CategoriesAdapter(popularMovie);
        mNowPlayingAdapter = new CategoriesAdapter(nowPlayingMovie);
        mTopRateAdapter = new CategoriesAdapter(toprateMovie);
        mUpComingAdapter = new CategoriesAdapter(upComingMovie);
        mGenresAdapter = new GenresAdapter(genres);

        mViewModel = new HomeViewModel(mPopularAdapter,
                mNowPlayingAdapter, mTopRateAdapter, mUpComingAdapter, mGenresAdapter);
    }
}
