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
    private ItemClickListener mItemClickListener;

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
        return new ViewHolder(binding, mItemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.bindData(mVideos.get(i));
    }

    @Override
    public int getItemCount() {
        return mVideos != null ? mVideos.size() : 0;
    }

    public TrailerAdapter setItemClickListener(ItemClickListener itemClickListener) {
        mItemClickListener = itemClickListener;
        return this;
    }

    public void replaceData(List<Video> videos) {
        mVideos.clear();
        mVideos.addAll(videos);
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private ItemTrailerBinding mBinding;
        private ItemTrailerViewModel mItemTrailerViewModel;

        public ViewHolder(ItemTrailerBinding binding, ItemClickListener listener) {
            super(binding.getRoot());
            mBinding = binding;
            mItemTrailerViewModel = new ItemTrailerViewModel(listener);
            mBinding.setViewModel(mItemTrailerViewModel);
        }

        public void bindData(Video video) {
            mItemTrailerViewModel.setTrailer(video);
            mBinding.executePendingBindings();
        }
    }

    public interface ItemClickListener {
        void onTrailerItemClick(Video video);
    }
}
