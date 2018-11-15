package vunt.com.vn.moviedb_28.data.source.remote.config.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import vunt.com.vn.moviedb_28.data.model.Actor;

public class CastResult {
    @SerializedName("cast")
    @Expose
    private List<Actor> mCasts;

    public List<Actor> getCasts() {
        return mCasts;
    }

    public CastResult setCasts(List<Actor> casts) {
        mCasts = casts;
        return this;
    }
}
