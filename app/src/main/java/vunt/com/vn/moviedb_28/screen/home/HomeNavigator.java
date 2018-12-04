package vunt.com.vn.moviedb_28.screen.home;

import android.os.Bundle;

import vunt.com.vn.moviedb_28.data.model.Genre;
import vunt.com.vn.moviedb_28.data.model.Movie;

public interface HomeNavigator {
    public void showMovies(Genre genre, int getBy);

    public void showMovieDetail(Movie movie);
}
