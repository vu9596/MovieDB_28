package vunt.com.vn.moviedb_28.screen;

import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;

import vunt.com.vn.moviedb_28.screen.moviedetail.MovieDetailViewModel;

public abstract class BaseFragment extends Fragment {
    protected MovieDetailViewModel mViewModel;

    protected void setViewModel(MovieDetailViewModel viewModel) {
        mViewModel = viewModel;
    }

    protected void setupAdapters(RecyclerView recyclerView, RecyclerView.Adapter adapter) {
        recyclerView.setAdapter(adapter);
    }
}
