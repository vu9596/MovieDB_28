package vunt.com.vn.moviedb_28.screen.home;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

import vunt.com.vn.moviedb_28.R;
import vunt.com.vn.moviedb_28.data.model.Genres;
import vunt.com.vn.moviedb_28.databinding.ItemGenresBinding;

public class GenresAdapter extends RecyclerView.Adapter<GenresAdapter.ViewHolder> {
    private List<Genres> mGenress;

    public GenresAdapter(List<Genres> Genress) {
        mGenress = Genress;
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
        viewHolder.bindData(mGenress.get(i));
    }

    @Override
    public int getItemCount() {
        return mGenress.size();
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

        public void bindData(Genres genres) {
            Log.e("aaa", "genres");
            mItemGenresListViewModel.setGenres(genres);
            mBinding.executePendingBindings();
        }
    }

    public void addData(List<Genres> genresList) {
        mGenress.addAll(genresList);
        notifyDataSetChanged();
    }

    public void replaceData(List<Genres> genresList) {
        mGenress.clear();
        mGenress.addAll(genresList);
        notifyDataSetChanged();
    }
}
