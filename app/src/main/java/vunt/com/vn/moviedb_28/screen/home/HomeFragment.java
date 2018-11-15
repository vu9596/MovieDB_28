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
import vunt.com.vn.moviedb_28.data.model.Genres;
import vunt.com.vn.moviedb_28.data.model.Movie;
import vunt.com.vn.moviedb_28.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {

    private HomeViewModel mViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        List<Movie> popularMovie = new ArrayList<>();
        List<Movie> toprateMovie = new ArrayList<>();
        List<Movie> upComingMovie = new ArrayList<>();
        List<Movie> nowPlayingMovie = new ArrayList<>();
        List<Genres> genres = new ArrayList<>();

        CategoryAdapter popularAdapter = new CategoryAdapter(popularMovie);
        CategoryAdapter nowPlayingAdapter = new CategoryAdapter(nowPlayingMovie);
        CategoryAdapter topRateAdapter = new CategoryAdapter(toprateMovie);
        CategoryAdapter upComingAdapter = new CategoryAdapter(upComingMovie);
        GenresAdapter genresAdapter = new GenresAdapter(genres);
        LinearLayoutManager genresLayoutManager = new LinearLayoutManager(getContext(),
                LinearLayoutManager.HORIZONTAL, false);

        mViewModel = new HomeViewModel(popularAdapter,
                nowPlayingAdapter, topRateAdapter, upComingAdapter, genresAdapter);
        mViewModel.setGenresLayoutManager(genresLayoutManager);

        FragmentHomeBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home,
                container, false);
        binding.setViewModel(mViewModel);
        return binding.getRoot();
    }
}
