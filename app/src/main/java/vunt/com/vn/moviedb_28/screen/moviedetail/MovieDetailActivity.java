package vunt.com.vn.moviedb_28.screen.moviedetail;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import vunt.com.vn.moviedb_28.R;
import vunt.com.vn.moviedb_28.data.model.Actor;
import vunt.com.vn.moviedb_28.data.model.Company;
import vunt.com.vn.moviedb_28.data.model.Genre;
import vunt.com.vn.moviedb_28.data.model.Movie;
import vunt.com.vn.moviedb_28.data.model.Video;
import vunt.com.vn.moviedb_28.data.repository.MovieRepository;
import vunt.com.vn.moviedb_28.data.source.local.FavoriteReaderDbHelper;
import vunt.com.vn.moviedb_28.data.source.local.MovieLocalDataSource;
import vunt.com.vn.moviedb_28.data.source.remote.MovieRemoteDataSource;
import vunt.com.vn.moviedb_28.databinding.ActivityMovieDetailBinding;
import vunt.com.vn.moviedb_28.screen.actors.ActorsFragment;
import vunt.com.vn.moviedb_28.screen.movieinfo.InfoFragment;
import vunt.com.vn.moviedb_28.screen.movies.MoviesActivity;
import vunt.com.vn.moviedb_28.screen.producer.ProduceFragment;
import vunt.com.vn.moviedb_28.screen.trailers.TrailerFragment;

import static vunt.com.vn.moviedb_28.screen.home.HomeViewModel.ACTOR_SOURCE;
import static vunt.com.vn.moviedb_28.screen.home.HomeViewModel.BUNDLE_KEY;
import static vunt.com.vn.moviedb_28.screen.home.HomeViewModel.PRODUCE_SOURCE;

public class MovieDetailActivity extends AppCompatActivity
        implements OnChangeVideoListener, MovieDetailNavigator,
        ProduceFragment.OnProduceSelectedListener, ActorsFragment.OnActorSelectedListener,
        TrailerFragment.OnTrailerSelectedListener {

    private static final String EXTRAS_ARGS = "vunt.com.vn.moviedb_28.extras.EXTRAS_ARGS";

    private MovieDetailViewModel mViewModel;
    private ActivityMovieDetailBinding mBinding;

    private YouTubeVideoFragment mYouTubeVideoFragment;
    private String mMovieId;

    public static Intent getMovieDetailIntent(Context context, Movie movie) {
        Intent intent = new Intent(context, MovieDetailActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString(BUNDLE_KEY, String.valueOf(movie.getId()));
        intent.putExtra(EXTRAS_ARGS, bundle);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initViewModel();
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_movie_detail);
        mBinding.setViewModel(mViewModel);
        initViews();
        mYouTubeVideoFragment =
                (YouTubeVideoFragment) getFragmentManager().findFragmentById(R.id.player);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mViewModel.clear();
    }

    @Override
    public void setVideoId(String videoId) {
        mYouTubeVideoFragment.setVideoId(videoId);
    }

    private void initViews() {
        ViewPager viewPager = findViewById(R.id.view_pager);
        MainPagerAdapter pagerAdapter = new MainPagerAdapter(getSupportFragmentManager());

        InfoFragment infoFragment = InfoFragment.newInstance();
        infoFragment.setViewModel(mViewModel);
        pagerAdapter.addFragment(infoFragment, getString(R.string.tab_title_information));

        ActorsFragment actorsFragment = ActorsFragment.newInstance();
        actorsFragment.setViewModel(mViewModel);
        pagerAdapter.addFragment(actorsFragment, getString(R.string.tab_title_actor));

        TrailerFragment trailerFragment = TrailerFragment.newInstance();
        trailerFragment.setViewModel(mViewModel);
        pagerAdapter.addFragment(trailerFragment, getString(R.string.tab_title_trailer));

        ProduceFragment produceFragment = ProduceFragment.newInstance();
        produceFragment.setViewModel(mViewModel);
        pagerAdapter.addFragment(produceFragment, getString(R.string.tab_title_producer));
        viewPager.setAdapter(pagerAdapter);
    }

    @Override
    public void back() {
        this.finish();
    }

    @Override
    public void showMovies(Actor actor, int getBy) {
        Genre genre = new Genre(String.valueOf(actor.getId()), actor.getName());
        startActivity(MoviesActivity.getMoviesIntent(this, genre, getBy));
    }

    @Override
    public void showMovies(Genre genre, int getBy) {

    }

    @Override
    public void showMovies(Company company, int getBy) {
        Genre genre = new Genre(String.valueOf(company.getId()), company.getName());
        startActivity(MoviesActivity.getMoviesIntent(this, genre, getBy));
    }

    @Override
    public void onProduceSelected(Company company) {
        showMovies(company, PRODUCE_SOURCE);
    }

    @Override
    public void onActorSelected(Actor actor) {
        showMovies(actor, ACTOR_SOURCE);
    }

    @Override
    public void onTrailerSelected(Video video) {
        setVideoId(video.getKey());
        mYouTubeVideoFragment.playVideo();
    }

    public static class MainPagerAdapter extends FragmentPagerAdapter {
        private List<Fragment> mFragments = new ArrayList<>();
        private List<String> mTitles = new ArrayList<>();

        public MainPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mFragments != null ? mFragments.size() : 0;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTitles.get(position);
        }

        public void addFragment(Fragment fragment, String title) {
            mFragments.add(fragment);
            mTitles.add(title);
        }
    }

    private void initViewModel() {
        FavoriteReaderDbHelper dbHelper = new FavoriteReaderDbHelper(this);
        mMovieId = getIntent().getBundleExtra(EXTRAS_ARGS).getString(BUNDLE_KEY);
        mViewModel = new MovieDetailViewModel(Integer.valueOf(mMovieId),
                MovieRepository.getInstance(MovieRemoteDataSource.getInstance(),
                        MovieLocalDataSource.getInstance(dbHelper)));
        mViewModel.setOnChangeVideoListener(this);
        mViewModel.setNavigator(this);
    }
}
