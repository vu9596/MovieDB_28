package vunt.com.vn.moviedb_28.screen.actors;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import vunt.com.vn.moviedb_28.R;
import vunt.com.vn.moviedb_28.databinding.FragmentActorBinding;
import vunt.com.vn.moviedb_28.screen.BaseFragment;

public class ActorsFragment extends BaseFragment {

    private FragmentActorBinding mBinding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_actor, container, false);
        mBinding = FragmentActorBinding.bind(view);
        mBinding.setViewModel(mViewModel);
        setupAdapters(mBinding.recyclerActor,
                new ActorsAdapter(mViewModel.getMovie().getCastResult().getCasts()));
        return view;
    }
}
