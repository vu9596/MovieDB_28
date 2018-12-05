package vunt.com.vn.moviedb_28.screen.movieinfo;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import vunt.com.vn.moviedb_28.R;
import vunt.com.vn.moviedb_28.databinding.FragmentInfoBinding;
import vunt.com.vn.moviedb_28.screen.BaseFragment;

public class InfoFragment extends BaseFragment {
    private FragmentInfoBinding mBinding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_info, container, false);
        mBinding = FragmentInfoBinding.bind(view);
        mBinding.setViewModel(mViewModel);
        setupAdapters(mBinding.recyclerGenre,
                new GenresDetailAdapter(mViewModel.getMovie().getGenres()));
        return view;
    }
}
