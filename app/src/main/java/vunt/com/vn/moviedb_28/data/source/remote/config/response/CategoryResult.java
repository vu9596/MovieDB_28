package vunt.com.vn.moviedb_28.data.source.remote.config.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import vunt.com.vn.moviedb_28.data.model.Movie;

public class CategoryResult {
    @SerializedName("page")
    private int mPage;

    @SerializedName("results")
    private List<Movie> mMovies;

    @SerializedName("total_results")
    private int mTotalResults;

    @SerializedName("total_pages")
    private int mTotalPages;

    public CategoryResult(int page, List<Movie> movies, int totalResults, int totalPages) {
        mPage = page;
        mMovies = movies;
        mTotalResults = totalResults;
        mTotalPages = totalPages;
    }

    public int getPage() {
        return mPage;
    }

    public List<Movie> getMovies() {
        return mMovies;
    }

    public int getTotalResults() {
        return mTotalResults;
    }

    public int getTotalPages() {
        return mTotalPages;
    }
}
