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
import vunt.com.vn.moviedb_28.data.model.Genre;
import vunt.com.vn.moviedb_28.data.model.Movie;
import vunt.com.vn.moviedb_28.data.repository.MovieRepository;
import vunt.com.vn.moviedb_28.data.source.local.FavoriteReaderDbHelper;
import vunt.com.vn.moviedb_28.data.source.local.MovieLocalDataSource;
import vunt.com.vn.moviedb_28.data.source.remote.MovieRemoteDataSource;
import vunt.com.vn.moviedb_28.databinding.ActivityMoviesBinding;
import vunt.com.vn.moviedb_28.screen.home.CategoriesAdapter;
import vunt.com.vn.moviedb_28.screen.home.HomeViewModel;
import vunt.com.vn.moviedb_28.screen.moviedetail.MovieDetailActivity;

import static vunt.com.vn.moviedb_28.screen.home.HomeViewModel.ACTOR_SOURCE;
import static vunt.com.vn.moviedb_28.screen.home.HomeViewModel.BUNDLE_KEY;
import static vunt.com.vn.moviedb_28.screen.home.HomeViewModel.BUNDLE_NAME;
import static vunt.com.vn.moviedb_28.screen.home.HomeViewModel.BUNDLE_SOURCE;
import static vunt.com.vn.moviedb_28.screen.home.HomeViewModel.CATEGORY_SOURCE;
import static vunt.com.vn.moviedb_28.screen.home.HomeViewModel.GENRE_SOURCE;
import static vunt.com.vn.moviedb_28.screen.home.HomeViewModel.PRODUCE_SOURCE;

public class MoviesActivity extends AppCompatActivity implements MoviesNavigator,
        CategoriesAdapter.ItemClickListener {

    private static final String EXTRAS_ARGS = "vunt.com.vn.moviedb_28.extras.EXTRAS_ARGS";

    private ActivityMoviesBinding mBinding;
    private CategoriesAdapter mAdapter;
    private LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);

    private MoviesViewModel mViewModel;

    private boolean isScrolling;
    private int currentItem, totalItem, scrolOutItem;

    public static Intent getMoviesIntent(Context context, Genre genre, int getBy) {
        Intent intent = new Intent(context, MoviesActivity.class);
        Bundle bundle = new Bundle();
        if (getBy == GENRE_SOURCE) {
            bundle.putInt(BUNDLE_SOURCE, GENRE_SOURCE);
        } else if (getBy == PRODUCE_SOURCE) {
            bundle.putInt(BUNDLE_SOURCE, PRODUCE_SOURCE);
        } else if (getBy == ACTOR_SOURCE) {
            bundle.putInt(BUNDLE_SOURCE, ACTOR_SOURCE);
        } else {
            bundle.putInt(BUNDLE_SOURCE, CATEGORY_SOURCE);
        }
        bundle.putString(BUNDLE_KEY, genre.getId());
        bundle.putString(BUNDLE_NAME, genre.getName());
        intent.putExtra(EXTRAS_ARGS, bundle);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initViewModel();

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

    @Override
    public void showMovieDetail(Movie movie) {
        startActivity(MovieDetailActivity.getMovieDetailIntent(this, movie));
    }

    @Override
    public void onMovieItemClick(Movie movie) {
        showMovieDetail(movie);
    }

    @Override
    public void onDeleteFavoritiesClick(Movie movie) {

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

    private void setupAdapters() {
        RecyclerView genresRecycler = mBinding.recycleGenre;
        genresRecycler.setLayoutManager(mLayoutManager);
        mAdapter = new CategoriesAdapter(new ArrayList<Movie>(0));
        mAdapter.setItemClickListener(this);
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
                    mViewModel.isLoadMore.set(true);
                }
            }
        });
    }

    private void initViewModel() {
        FavoriteReaderDbHelper dbHelper = new FavoriteReaderDbHelper(this);
        Bundle bundle = getIntent().getBundleExtra(EXTRAS_ARGS);
        mViewModel = new MoviesViewModel(
                MovieRepository.getInstance(MovieRemoteDataSource.getInstance(),
                        MovieLocalDataSource.getInstance(dbHelper)),
                bundle.getInt(HomeViewModel.BUNDLE_SOURCE),
                bundle.getString(HomeViewModel.BUNDLE_KEY));
    }
}
