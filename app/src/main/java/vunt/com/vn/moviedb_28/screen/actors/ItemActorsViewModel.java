package vunt.com.vn.moviedb_28.screen.actors;

import android.databinding.BaseObservable;
import android.databinding.ObservableField;
import android.view.View;

import vunt.com.vn.moviedb_28.data.model.Actor;

public class ItemActorsViewModel extends BaseObservable {
    public final ObservableField<Actor> actorObservable = new ObservableField<>();
    private ActorsAdapter.ItemClickListener mItemClickListener;

    public ItemActorsViewModel(ActorsAdapter.ItemClickListener itemClickListener) {
        mItemClickListener = itemClickListener;
    }

    public void setActor(Actor actor) {
        actorObservable.set(actor);
    }

    public void onItemClicked(View view) {
        if (mItemClickListener == null || actorObservable.get() == null) {
            return;
        }
        mItemClickListener.onActorItemClick(actorObservable.get());
    }
}
