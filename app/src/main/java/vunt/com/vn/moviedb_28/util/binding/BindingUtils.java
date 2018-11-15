package vunt.com.vn.moviedb_28.util.binding;

import android.databinding.BindingAdapter;
import android.databinding.ObservableField;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.List;

import vunt.com.vn.moviedb_28.data.model.Genre;
import vunt.com.vn.moviedb_28.data.model.Movie;
import vunt.com.vn.moviedb_28.screen.home.CategoriesAdapter;
import vunt.com.vn.moviedb_28.screen.home.GenresAdapter;
import vunt.com.vn.moviedb_28.util.StringUtils;

public class BindingUtils {
    private static final int IMAGE_SIZE_200 = 200;

    @BindingAdapter({"bindMovies"})
    public static void setMoviesForRecyclerView(RecyclerView recyclerView,
                                                ObservableField<List<Movie>> observableField) {
        if (recyclerView.getAdapter() == null || recyclerView.getAdapter().getItemCount() == 0) {
            CategoriesAdapter adapter = new CategoriesAdapter(observableField.get());
            recyclerView.setAdapter(adapter);
            return;
        }
        CategoriesAdapter adapter = (CategoriesAdapter) recyclerView.getAdapter();
        adapter.addData(observableField.get());
    }

    @BindingAdapter({"bindGenres"})
    public static void setGenresForRecyclerView(RecyclerView recyclerView,
                                                ObservableField<List<Genre>> observableField) {
        GenresAdapter adapter = new GenresAdapter(observableField.get());
        recyclerView.setAdapter(adapter);
    }

    @BindingAdapter({"layoutManager"})
    public static void setLayoutManager(RecyclerView recyclerView,
                                        LinearLayoutManager layoutManager) {
        recyclerView.setLayoutManager(layoutManager);
    }

    @BindingAdapter({"setVisibility"})
    public static void setVisibleForView(View view, boolean isVisible) {
        if (isVisible) {
            view.setVisibility(View.VISIBLE);
            return;
        }
        view.setVisibility(View.GONE);
    }

    @BindingAdapter("imageUrl")
    public static void setImage(ImageView imageView, String url) {
        if (url == null || url.isEmpty()) {
            return;
        }
        String imageLink = StringUtils.getImageLink(IMAGE_SIZE_200, url);
        Glide.with(imageView.getContext())
                .load(imageLink)
                .into(imageView);
    }
}
