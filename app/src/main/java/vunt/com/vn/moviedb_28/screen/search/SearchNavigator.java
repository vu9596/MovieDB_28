package vunt.com.vn.moviedb_28.screen.search;

import vunt.com.vn.moviedb_28.data.model.Movie;

public interface SearchNavigator {
    void showSearchFilter();

    void showMovieDetail(Movie movie);
}
