package vunt.com.vn.moviedb_28.screen.movies;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.widget.AbsListView;

import java.util.ArrayList;

import vunt.com.vn.moviedb_28.R;
import vunt.com.vn.moviedb_28.data.model.Movie;
import vunt.com.vn.moviedb_28.data.repository.MovieRepository;
import vunt.com.vn.moviedb_28.data.source.remote.MovieRemoteDataSource;
import vunt.com.vn.moviedb_28.databinding.ActivityMoviesBinding;
import vunt.com.vn.moviedb_28.screen.home.CategoriesAdapter;
import vunt.com.vn.moviedb_28.screen.home.HomeViewModel;
import vunt.com.vn.moviedb_28.util.Constant;

public class MoviesActivity extends AppCompatActivity {

    private static final String EXTRAS_ARGS = "vunt.com.vn.moviedb_28.extras.EXTRAS_ARGS";

    private ActivityMoviesBinding mBinding;
    private CategoriesAdapter mAdapter;
    private LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);

    private MoviesViewModel mViewModel;

    private boolean isScrolling;
    private int currentItem, totalItem, scrolOutItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = getIntent().getBundleExtra(EXTRAS_ARGS);
        mViewModel = new MoviesViewModel(
                MovieRepository.getInstance(MovieRemoteDataSource.getInstance()),
                bundle.getInt(HomeViewModel.BUNDLE_SOURCE),
                bundle.getString(HomeViewModel.BUNDLE_KEY));

        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_movies);
        mBinding.setViewModel(mViewModel);

        setupToolbar();

        setupAdapters();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mViewModel.clear();
    }

    private void setupAdapters() {
        RecyclerView genresRecycler = mBinding.recycleGenre;
        genresRecycler.setLayoutManager(mLayoutManager);
        mAdapter = new CategoriesAdapter(new ArrayList<Movie>(0));
        genresRecycler.setAdapter(mAdapter);

        setupScrollListener(genresRecycler);
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
                    mViewModel.loadMovies(mViewModel.getLoadBy());
                }
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public static Intent getMoviesIntent(Context context, Bundle bundle) {
        Intent intent = new Intent(context, MoviesActivity.class);
        intent.putExtra(EXTRAS_ARGS, bundle);
        return intent;
    }

    private void setupToolbar() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        String title = getTitleToolbar();
        getSupportActionBar().setTitle(title);
    }

    private String getTitleToolbar() {
        String title = "";
        title = getIntent().getBundleExtra(EXTRAS_ARGS).getString(HomeViewModel.BUNDLE_NAME);
        return title;
    }
}
