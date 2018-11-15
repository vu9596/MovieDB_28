package vunt.com.vn.moviedb_28.screen.actors;

import android.databinding.BaseObservable;
import android.databinding.ObservableField;

import vunt.com.vn.moviedb_28.data.model.Actor;

public class ItemActorsViewModel extends BaseObservable {
    public final ObservableField<Actor> actorObservable = new ObservableField<>();

    public void setActor(Actor actor) {
        actorObservable.set(actor);
    }
}
