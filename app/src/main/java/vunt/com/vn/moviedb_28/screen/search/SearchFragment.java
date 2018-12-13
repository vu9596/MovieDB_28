package vunt.com.vn.moviedb_28.screen.search;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;

import java.util.ArrayList;

import vunt.com.vn.moviedb_28.R;
import vunt.com.vn.moviedb_28.data.model.Movie;
import vunt.com.vn.moviedb_28.data.repository.MovieRepository;
import vunt.com.vn.moviedb_28.data.source.local.FavoriteReaderDbHelper;
import vunt.com.vn.moviedb_28.data.source.local.MovieLocalDataSource;
import vunt.com.vn.moviedb_28.data.source.remote.MovieRemoteDataSource;
import vunt.com.vn.moviedb_28.databinding.FragmentSearchBinding;
import vunt.com.vn.moviedb_28.screen.home.CategoriesAdapter;
import vunt.com.vn.moviedb_28.screen.moviedetail.MovieDetailActivity;
import vunt.com.vn.moviedb_28.screen.searchfilter.SearchFilterActivity;
import vunt.com.vn.moviedb_28.util.Constant;

import static android.app.Activity.RESULT_OK;

public class SearchFragment extends Fragment
        implements SearchNavigator, CategoriesAdapter.ItemClickListener {
    public static final int REQUSET_CODE_FILTER = 9596;
    private static final String EXTRAS_ARGS = "vunt.com.vn.moviedb_28.extras.EXTRAS_ARGS";
    private static final String BUNDLE_SEARCH_BY = "BUNDLE_SEARCH_BY";
    private SearchViewModel mViewModel;
    private FragmentSearchBinding mBinding;

    private CategoriesAdapter mSearchAdapter;

    private static Class mActivityClassName;

    private LinearLayoutManager mLayoutManager = new LinearLayoutManager(getContext());
    private boolean isScrolling;
    private int currentItem, totalItem, scrolOutItem;

    public static SearchFragment newInstance() {
        SearchFragment fragment = new SearchFragment();
        return fragment;
    }

    public static Intent getSearchFragmentIntent(Context context, String serchBy) {
        Intent intent = new Intent(context, mActivityClassName);
        Bundle bundle = new Bundle();
        bundle.putString(BUNDLE_SEARCH_BY, serchBy);
        intent.putExtra(EXTRAS_ARGS, bundle);
        return intent;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivityClassName = context.getClass();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        initViewModel();
        setNavigator();
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_search,
                container, false);
        mBinding.setViewModel(mViewModel);

        setupAdapters();

        setupSearchView();
        return mBinding.getRoot();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mViewModel.clear();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == SearchFragment.REQUSET_CODE_FILTER && resultCode == RESULT_OK) {
        }
    }

    @Override
    public void showSearchFilter() {
        startActivityForResult(
                SearchFilterActivity.getSearchFilterIntent(getContext()),
                REQUSET_CODE_FILTER);
    }

    @Override
    public void showMovieDetail(Movie movie) {
        startActivity(MovieDetailActivity.getMovieDetailIntent(getContext(), movie));
    }

    @Override
    public void onMovieItemClick(Movie movie) {
        showMovieDetail(movie);
    }

    @Override
    public void onDeleteFavoritiesClick(Movie movie) {

    }

    private void initViewModel() {
        FavoriteReaderDbHelper dbHelper = new FavoriteReaderDbHelper(getContext());
        MovieRepository movieRepository = MovieRepository.getInstance(
                MovieRemoteDataSource.getInstance(),
                MovieLocalDataSource.getInstance(dbHelper));
        mViewModel = new SearchViewModel(movieRepository);
    }

    private void setupAdapters() {
        RecyclerView searchRecycler = mBinding.recyclerSearch;
        searchRecycler.setLayoutManager(mLayoutManager);
        mSearchAdapter = new CategoriesAdapter(new ArrayList<Movie>(0));
        mSearchAdapter.setItemClickListener(this);
        searchRecycler.setAdapter(mSearchAdapter);

        setupScrollListener(searchRecycler);
    }

    private void setupSearchView() {
        SearchView searchView = mBinding.searchView;
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                mViewModel.setCurrentPage(Constant.FIRST_PAGE);
                mViewModel.setKey(s);
                mViewModel.searchMovie();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                mViewModel.setCurrentPage(Constant.FIRST_PAGE);
                mViewModel.setKey(s);
                mViewModel.searchMovie();
                return true;
            }
        });
    }

    private void setNavigator() {
        mViewModel.setSearchNavigator(this);
    }

    private void setupScrollListener(RecyclerView genresRecycler) {
        genresRecycler.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
                    isScrolling = true;
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                currentItem = mLayoutManager.getChildCount();
                totalItem = mLayoutManager.getItemCount();
                scrolOutItem = mLayoutManager.findFirstVisibleItemPosition();
                if (isScrolling && (currentItem + scrolOutItem == totalItem)) {
                    isScrolling = false;
                    mViewModel.isLoadMore.set(true);
                    mViewModel.increaseCurrentPage();
                    mViewModel.searchMovie();
                    mViewModel.isLoadMore.set(true);
                }
            }
        });
    }
}
