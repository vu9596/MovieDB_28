package vunt.com.vn.moviedb_28.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Genre {
    @SerializedName("id")
    @Expose
    private int mId;
    @SerializedName("name")
    @Expose
    private String mName;

    public Genre() {
    }

    public Genre(int id, String name) {
        mId = id;
        mName = name;
    }

    public int getId() {
        return mId;
    }

    public Genre setId(int id) {
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
