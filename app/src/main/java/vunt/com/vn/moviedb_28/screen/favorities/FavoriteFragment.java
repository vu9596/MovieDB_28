package vunt.com.vn.moviedb_28.screen.favorities;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import vunt.com.vn.moviedb_28.R;
import vunt.com.vn.moviedb_28.data.model.Movie;
import vunt.com.vn.moviedb_28.data.repository.MovieRepository;
import vunt.com.vn.moviedb_28.data.source.local.FavoriteReaderDbHelper;
import vunt.com.vn.moviedb_28.data.source.local.MovieLocalDataSource;
import vunt.com.vn.moviedb_28.data.source.remote.MovieRemoteDataSource;
import vunt.com.vn.moviedb_28.databinding.FragmentFavoriteBinding;
import vunt.com.vn.moviedb_28.screen.home.CategoriesAdapter;
import vunt.com.vn.moviedb_28.screen.moviedetail.MovieDetailActivity;

public class FavoriteFragment extends Fragment
        implements CategoriesAdapter.ItemClickListener, FavoritiesNavigator {

    private FavoritiesViewModel mViewModel;
    private FragmentFavoriteBinding mBinding;

    private CategoriesAdapter mFavoriteAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        initViewModel();
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_favorite,
                container, false);
        mBinding.setViewModel(mViewModel);

        setupAdapters();

        return mBinding.getRoot();
    }

    private void setupAdapters() {
        RecyclerView favoriteRecycler = mBinding.recyclerFavorities;
        mFavoriteAdapter = new CategoriesAdapter(new ArrayList<Movie>(0));
        mFavoriteAdapter.setItemClickListener(this);
        mFavoriteAdapter.setFavorities(true);
        favoriteRecycler.setAdapter(mFavoriteAdapter);
    }

    private void initViewModel() {
        FavoriteReaderDbHelper dbHelper = new FavoriteReaderDbHelper(getContext());
        MovieRepository movieRepository = MovieRepository.getInstance(
                MovieRemoteDataSource.getInstance(),
                MovieLocalDataSource.getInstance(dbHelper));
        mViewModel = new FavoritiesViewModel(movieRepository);
    }

    @Override
    public void onMovieItemClick(Movie movie) {
        showMovieDetail(movie);
    }

    @Override
    public void onDeleteFavoritiesClick(Movie movie) {
    }

    @Override
    public void showMovieDetail(Movie movie) {
        startActivity(MovieDetailActivity.getMovieDetailIntent(getActivity(), movie));
    }
}
