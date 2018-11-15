package vunt.com.vn.moviedb_28.screen.main;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import vunt.com.vn.moviedb_28.R;
import vunt.com.vn.moviedb_28.screen.favorities.FavoriteFragment;
import vunt.com.vn.moviedb_28.screen.home.HomeFragment;
import vunt.com.vn.moviedb_28.util.ActivityUtils;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupBottomNavigationView();
        ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),
                new HomeFragment(), R.id.frame_fragments_container);
    }

    private void setupBottomNavigationView() {
        BottomNavigationView bottomNavigationView = this.findViewById(R.id.bottom_navigation);
        if (bottomNavigationView != null) {
            setupBottomNavigationViewContent(bottomNavigationView);
        }
    }

    private void setupBottomNavigationViewContent(BottomNavigationView bottomNavigationView) {
        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                        switch (menuItem.getItemId()) {
                            case R.id.navigation_home:
                                ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),
                                        new HomeFragment(), R.id.frame_fragments_container);
                                break;
                            case R.id.navigation_my_fovorities:
                                ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),
                                        new FavoriteFragment(), R.id.frame_fragments_container);
                            case R.id.navigation_my_search:
                                break;
                            default:
                                break;
                        }
                        return true;
                    }
                });
    }
}
