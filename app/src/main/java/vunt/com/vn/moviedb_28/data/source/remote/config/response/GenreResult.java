package vunt.com.vn.moviedb_28.data.source.remote.config.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import vunt.com.vn.moviedb_28.data.model.Genre;

public class GenreResult {
    @SerializedName("genres")
    private List<Genre> mGenres;

    public GenreResult(List<Genre> genres) {
        mGenres = genres;
    }

    public List<Genre> getGenres() {
        return mGenres;
    }

    public GenreResult setGenres(List<Genre> genres) {
        mGenres = genres;
        return this;
    }
}
