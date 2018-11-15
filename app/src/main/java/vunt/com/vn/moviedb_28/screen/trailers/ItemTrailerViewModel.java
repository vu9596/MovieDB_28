package vunt.com.vn.moviedb_28.screen.trailers;

import android.databinding.BaseObservable;
import android.databinding.ObservableField;

import vunt.com.vn.moviedb_28.data.model.Video;

public class ItemTrailerViewModel extends BaseObservable {
    public final ObservableField<Video> videoObservable = new ObservableField<>();

    public void setTrailer(Video video) {
        videoObservable.set(video);
    }
}
