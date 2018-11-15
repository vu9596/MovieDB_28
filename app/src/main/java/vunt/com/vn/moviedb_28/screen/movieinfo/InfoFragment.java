package vunt.com.vn.moviedb_28.screen.movieinfo;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import vunt.com.vn.moviedb_28.R;
import vunt.com.vn.moviedb_28.data.model.Genre;
import vunt.com.vn.moviedb_28.databinding.FragmentInfoBinding;
import vunt.com.vn.moviedb_28.screen.BaseFragment;
import vunt.com.vn.moviedb_28.screen.movies.MoviesActivity;

import static vunt.com.vn.moviedb_28.screen.home.HomeViewModel.GENRE_SOURCE;

public class InfoFragment extends BaseFragment
        implements GenresDetailAdapter.ItemClickListener, InfoNavigator {
    private FragmentInfoBinding mBinding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_info, container, false);
        mBinding = FragmentInfoBinding.bind(view);
        mBinding.setViewModel(mViewModel);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(),
                LinearLayoutManager.HORIZONTAL, false);
        GenresDetailAdapter adapter = new GenresDetailAdapter(new ArrayList<Genre>());
        adapter.setItemClickListener(this);
        setupAdapters(mBinding.recyclerGenre,
                adapter,
                layoutManager);
        return view;
    }

    @Override
    public void showMovies(Genre genre, int getBy) {
        Intent intent = MoviesActivity.getMoviesIntent(getActivity(), genre, getBy);
        startActivity(intent);
    }

    @Override
    public void onGenreItemClick(Genre genre) {
        showMovies(genre, GENRE_SOURCE);
    }
}
