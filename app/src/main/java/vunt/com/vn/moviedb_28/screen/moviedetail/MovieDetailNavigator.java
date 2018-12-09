package vunt.com.vn.moviedb_28.screen.moviedetail;

import vunt.com.vn.moviedb_28.screen.actors.ActorsNavigator;
import vunt.com.vn.moviedb_28.screen.movieinfo.InfoNavigator;
import vunt.com.vn.moviedb_28.screen.producer.ProduceNavigator;
import vunt.com.vn.moviedb_28.screen.trailers.TrailerNavigator;

public interface MovieDetailNavigator extends InfoNavigator, ActorsNavigator,
        ProduceNavigator, TrailerNavigator {
    void back();
}
