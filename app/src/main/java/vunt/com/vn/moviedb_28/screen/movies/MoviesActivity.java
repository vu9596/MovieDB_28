package vunt.com.vn.moviedb_28.screen.movies;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;

import java.util.ArrayList;

import vunt.com.vn.moviedb_28.R;
import vunt.com.vn.moviedb_28.data.model.Movie;
import vunt.com.vn.moviedb_28.databinding.ActivityMoviesBinding;
import vunt.com.vn.moviedb_28.screen.home.CategoriesAdapter;
import vunt.com.vn.moviedb_28.screen.home.HomeViewModel;

public class MoviesActivity extends AppCompatActivity {

    private static final String EXTRAS_ARGS = "vunt.com.vn.moviedb_28.extras.EXTRAS_ARGS";

    private ActivityMoviesBinding mBinding;
    private CategoriesAdapter mAdapter;
    private LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);

    private MoviesViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = getIntent().getBundleExtra(EXTRAS_ARGS);
        mViewModel = new MoviesViewModel(
                bundle.getInt(HomeViewModel.BUNDLE_SOURCE),
                bundle.getString(HomeViewModel.BUNDLE_KEY));

        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_movies);
        mBinding.setViewModel(mViewModel);

        setupToolbar();

        setupAdapters();
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
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {

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
