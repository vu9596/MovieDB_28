package vunt.com.vn.moviedb_28.screen;

import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import vunt.com.vn.moviedb_28.screen.moviedetail.MovieDetailViewModel;
import vunt.com.vn.moviedb_28.screen.movieinfo.GenresDetailAdapter;

public abstract class BaseFragment extends Fragment {
    protected MovieDetailViewModel mViewModel;

    public void setViewModel(MovieDetailViewModel viewModel) {
        mViewModel = viewModel;
    }

    protected void setupAdapters(RecyclerView recyclerView, RecyclerView.Adapter adapter) {
        recyclerView.setAdapter(adapter);
    }

    protected void setupAdapters(RecyclerView recyclerGenre,
                                 GenresDetailAdapter genresDetailAdapter,
                                 LinearLayoutManager layoutManager) {
        recyclerGenre.setLayoutManager(layoutManager);
        setupAdapters(recyclerGenre, genresDetailAdapter);
    }
}
