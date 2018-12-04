package vunt.com.vn.moviedb_28.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Genre {
    @SerializedName("id")
    @Expose
    private String mId;
    @SerializedName("name")
    @Expose
    private String mName;

    public Genre() {
    }

    public Genre(String id, String name) {
        mId = id;
        mName = name;
    }

    public String getId() {
        return mId;
    }

    public Genre setId(String id) {
        mId = id;
        return this;
    }

    public String getName() {
        return mName;
    }

    public Genre setName(String name) {
        mName = name;
        return this;
    }
}
