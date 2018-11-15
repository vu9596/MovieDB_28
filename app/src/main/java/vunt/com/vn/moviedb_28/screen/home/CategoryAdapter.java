package vunt.com.vn.moviedb_28.screen.home;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

import vunt.com.vn.moviedb_28.R;
import vunt.com.vn.moviedb_28.data.model.Movie;
import vunt.com.vn.moviedb_28.databinding.ItemMovieBinding;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {
    private List<Movie> mMovies;

    public CategoryAdapter(List<Movie> movies) {
        mMovies = movies;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        ItemMovieBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(viewGroup.getContext()),
                R.layout.item_movie,
                viewGroup,
                false
        );
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.bindData(mMovies.get(i));
    }

    @Override
    public int getItemCount() {
        return mMovies.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private ItemMovieBinding mBinding;
        private ItemMovieListViewModel mItemMovieListViewModel;

        public ViewHolder(ItemMovieBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
            mItemMovieListViewModel = new ItemMovieListViewModel();
            mBinding.setViewModel(mItemMovieListViewModel);
        }

        public void bindData(Movie movie) {
            mItemMovieListViewModel.setMovie(movie);
            mBinding.executePendingBindings();
        }
    }

    public void addData(List<Movie> movies) {
        mMovies.addAll(movies);
        notifyDataSetChanged();
    }

    public void replaceData(List<Movie> movies) {
        mMovies.clear();
        mMovies.addAll(movies);
        notifyDataSetChanged();
    }
}
