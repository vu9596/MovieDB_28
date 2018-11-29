package vunt.com.vn.moviedb_28.screen.movies;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import vunt.com.vn.moviedb_28.R;
import vunt.com.vn.moviedb_28.screen.home.HomeViewModel;

public class MoviesActivity extends AppCompatActivity {

    private static final String EXTRAS_ARGS = "vunt.com.vn.moviedb_28.extras.EXTRAS_ARGS";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies);
        setupToolbar();
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

    public static Intent getListMovieIntent(Context context, Bundle bundle) {
        Intent intent = new Intent(context, MoviesActivity.class);
        intent.putExtra(EXTRAS_ARGS, bundle);
        return intent;
    }

    private String getTitleToolbar() {
        String title = "";
        title = getIntent().getBundleExtra(EXTRAS_ARGS).getString(HomeViewModel.BUNDLE_NAME);
        return title;
    }

    private void setupToolbar() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        String title = getTitleToolbar();
        getSupportActionBar().setTitle(title);
    }
}
