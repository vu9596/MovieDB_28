package vunt.com.vn.moviedb_28.screen.movieinfo;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

import vunt.com.vn.moviedb_28.R;
import vunt.com.vn.moviedb_28.data.model.Genre;
import vunt.com.vn.moviedb_28.databinding.ItemGenreDetailBinding;

public class GenresDetailAdapter extends RecyclerView.Adapter<GenresDetailAdapter.ViewHolder> {
    private List<Genre> mGenres;
    private ItemClickListener mItemClickListener;


    public GenresDetailAdapter(List<Genre> genres) {
        mGenres = genres;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        ItemGenreDetailBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(viewGroup.getContext()),
                R.layout.item_genre_detail,
                viewGroup,
                false
        );
        return new ViewHolder(binding, mItemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.bindData(mGenres.get(i));
    }

    @Override
    public int getItemCount() {
        return mGenres != null ? mGenres.size() : 0;
    }

    public GenresDetailAdapter setItemClickListener(ItemClickListener itemClickListener) {
        mItemClickListener = itemClickListener;
        return this;
    }

    public void replaceData(List<Genre> genres) {
        mGenres.clear();
        mGenres.addAll(genres);
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private ItemGenreDetailBinding mBinding;
        private ItemGenreDetailViewModel mItemGenreDetailViewModel;

        public ViewHolder(ItemGenreDetailBinding binding, ItemClickListener listener) {
            super(binding.getRoot());
            mBinding = binding;
            mItemGenreDetailViewModel = new ItemGenreDetailViewModel(listener);
            mBinding.setViewModel(mItemGenreDetailViewModel);
        }

        public void bindData(Genre genre) {
            mItemGenreDetailViewModel.setGenre(genre);
            mBinding.executePendingBindings();
        }
    }

    public interface ItemClickListener {
        void onGenreItemClick(Genre genre);
    }
}
