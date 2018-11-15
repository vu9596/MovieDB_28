package vunt.com.vn.moviedb_28.screen.trailers;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

import vunt.com.vn.moviedb_28.R;
import vunt.com.vn.moviedb_28.data.model.Video;
import vunt.com.vn.moviedb_28.databinding.ItemTrailerBinding;

public class TrailerAdapter extends RecyclerView.Adapter<TrailerAdapter.ViewHolder> {
    private List<Video> mVideos;

    public TrailerAdapter(List<Video> videos) {
        mVideos = videos;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        ItemTrailerBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(viewGroup.getContext()),
                R.layout.item_trailer,
                viewGroup,
                false
        );
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.bindData(mVideos.get(i));
    }

    @Override
    public int getItemCount() {
        return mVideos != null ? mVideos.size() : 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private ItemTrailerBinding mBinding;
        private ItemTrailerViewModel mItemTrailerViewModel;

        public ViewHolder(ItemTrailerBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
            mItemTrailerViewModel = new ItemTrailerViewModel();
            mBinding.setViewModel(mItemTrailerViewModel);
        }

        public void bindData(Video video) {
            mItemTrailerViewModel.setTrailer(video);
            mBinding.executePendingBindings();
        }
    }
}
