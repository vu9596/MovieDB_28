package vunt.com.vn.moviedb_28.screen.home;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

import vunt.com.vn.moviedb_28.R;
import vunt.com.vn.moviedb_28.data.model.Genre;
import vunt.com.vn.moviedb_28.databinding.ItemGenresBinding;

public class GenresAdapter extends RecyclerView.Adapter<GenresAdapter.ViewHolder> {
    private List<Genre> mGenres;

    public GenresAdapter(List<Genre> genres) {
        mGenres = genres;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        ItemGenresBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(viewGroup.getContext()),
                R.layout.item_genres,
                viewGroup,
                false
        );
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.bindData(mGenres.get(i));
    }

    @Override
    public int getItemCount() {
        return mGenres != null ? mGenres.size() : 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private ItemGenresBinding mBinding;
        private ItemGenresListViewModel mItemGenresListViewModel;

        public ViewHolder(ItemGenresBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
            mItemGenresListViewModel = new ItemGenresListViewModel();
            mBinding.setViewModel(mItemGenresListViewModel);
        }

        public void bindData(Genre genre) {
            mItemGenresListViewModel.setGenres(genre);
            mBinding.executePendingBindings();
        }
    }

    public void addData(List<Genre> genres) {
        mGenres.addAll(genres);
        notifyDataSetChanged();
    }

    public void replaceData(List<Genre> genres) {
        mGenres.clear();
        mGenres.addAll(genres);
        notifyDataSetChanged();
    }
}
