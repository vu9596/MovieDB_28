package vunt.com.vn.moviedb_28.data.source.remote.config.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import vunt.com.vn.moviedb_28.data.model.Video;

public class VideoResult {
    @SerializedName("results")
    @Expose
    private List<Video> mVideos;

    public List<Video> getVideos() {
        return mVideos;
    }

    public VideoResult setVideos(List<Video> videos) {
        mVideos = videos;
        return this;
    }
}
