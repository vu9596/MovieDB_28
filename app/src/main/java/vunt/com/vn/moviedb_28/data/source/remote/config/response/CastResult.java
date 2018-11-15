package vunt.com.vn.moviedb_28.data.source.remote.config.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import vunt.com.vn.moviedb_28.data.model.Cast;
import vunt.com.vn.moviedb_28.data.model.Video;

public class CastResult {
    @SerializedName("cast")
    @Expose
    private List<Cast> mCasts;

    public List<Cast> getCasts() {
        return mCasts;
    }

    public CastResult setCasts(List<Cast> casts) {
        mCasts = casts;
        return this;
    }
}
