package vunt.com.vn.moviedb_28.screen.movieinfo;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import vunt.com.vn.moviedb_28.R;
import vunt.com.vn.moviedb_28.data.model.Genre;
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
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(),
                LinearLayoutManager.HORIZONTAL, false);
        setupAdapters(mBinding.recyclerGenre,
                new GenresDetailAdapter(new ArrayList<Genre>()),
                layoutManager);
        return view;
    }
}
