package vunt.com.vn.moviedb_28.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Genres {
    @SerializedName("id")
    @Expose
    private int mId;
    @SerializedName("name")
    @Expose
    private String mName;

    public Genres(int id, String name) {
        mId = id;
        mName = name;
    }

    public int getId() {
        return mId;
    }

    public Genres setId(int id) {
        mId = id;
        return this;
    }

    public String getName() {
        return mName;
    }

    public Genres setName(String name) {
        mName = name;
        return this;
    }
}
