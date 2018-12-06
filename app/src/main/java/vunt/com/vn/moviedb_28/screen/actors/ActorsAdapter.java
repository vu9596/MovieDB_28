package vunt.com.vn.moviedb_28.screen.actors;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

import vunt.com.vn.moviedb_28.R;
import vunt.com.vn.moviedb_28.data.model.Actor;
import vunt.com.vn.moviedb_28.databinding.ItemActorBinding;

public class ActorsAdapter extends RecyclerView.Adapter<ActorsAdapter.ViewHolder> {
    private List<Actor> mActors;

    public ActorsAdapter(List<Actor> actors) {
        mActors = actors;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        ItemActorBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(viewGroup.getContext()),
                R.layout.item_actor,
                viewGroup,
                false
        );
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.bindData(mActors.get(i));
    }

    @Override
    public int getItemCount() {
        return mActors != null ? mActors.size() : 0;
    }

    public void replaceData(List<Actor> actors) {
        mActors.clear();
        mActors.addAll(actors);
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private ItemActorBinding mBinding;
        private ItemActorsViewModel mItemActorsViewModel;

        public ViewHolder(ItemActorBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
            mItemActorsViewModel = new ItemActorsViewModel();
            mBinding.setViewModel(mItemActorsViewModel);
        }

        public void bindData(Actor actor) {
            mItemActorsViewModel.setActor(actor);
            mBinding.executePendingBindings();
        }
    }
}
