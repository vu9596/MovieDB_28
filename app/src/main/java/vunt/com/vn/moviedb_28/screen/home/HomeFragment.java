package vunt.com.vn.moviedb_28.screen.home;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import vunt.com.vn.moviedb_28.R;
import vunt.com.vn.moviedb_28.data.repository.MovieRepository;
import vunt.com.vn.moviedb_28.data.source.remote.MovieRemoteDataSource;
import vunt.com.vn.moviedb_28.databinding.FragmentHomeBinding;
import vunt.com.vn.moviedb_28.screen.movies.MoviesActivity;

public class HomeFragment extends Fragment implements HomeNavigator {

    private HomeViewModel mViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        initViewModel();
        setLayoutManager();
        setNavigator();
        FragmentHomeBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home,
                container, false);
        binding.setViewModel(mViewModel);
        return binding.getRoot();
    }

    private void setNavigator() {
        if (mViewModel != null) {
            mViewModel.setNavigator(this);
        }
    }

    private void setLayoutManager() {
        if (mViewModel != null) {
            LinearLayoutManager genresLayoutManager = new LinearLayoutManager(getContext(),
                    LinearLayoutManager.HORIZONTAL, false);
            mViewModel.setGenresLayoutManager(genresLayoutManager);
        }
    }

    private void initViewModel() {
        MovieRepository movieRepository = MovieRepository.getInstance(
                MovieRemoteDataSource.getInstance());
        mViewModel = new HomeViewModel(movieRepository);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mViewModel.clear();
    }

    @Override
    public void showListMovies(Bundle bundle) {
        Intent intent = MoviesActivity.getListMovieIntent(getActivity(), bundle);
        startActivity(intent);
    }
}
