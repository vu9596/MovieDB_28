package vunt.com.vn.moviedb_28.screen.trailers;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import vunt.com.vn.moviedb_28.R;
import vunt.com.vn.moviedb_28.data.model.Video;
import vunt.com.vn.moviedb_28.databinding.FragmentTrailerBinding;
import vunt.com.vn.moviedb_28.screen.BaseFragment;

public class TrailerFragment extends BaseFragment {

    private FragmentTrailerBinding mBinding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_trailer, container, false);
        mBinding = FragmentTrailerBinding.bind(view);
        mBinding.setViewModel(mViewModel);
        setupAdapters(mBinding.recyclerTrailer,
                new TrailerAdapter(new ArrayList<Video>()));
        return view;
    }
}
