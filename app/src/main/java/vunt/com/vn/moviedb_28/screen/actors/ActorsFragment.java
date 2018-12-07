package vunt.com.vn.moviedb_28.screen.actors;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import vunt.com.vn.moviedb_28.R;
import vunt.com.vn.moviedb_28.data.model.Actor;
import vunt.com.vn.moviedb_28.databinding.FragmentActorBinding;
import vunt.com.vn.moviedb_28.screen.BaseFragment;

public class ActorsFragment extends BaseFragment implements ActorsAdapter.ItemClickListener {

    private FragmentActorBinding mBinding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_actor, container, false);
        mBinding = FragmentActorBinding.bind(view);
        mBinding.setViewModel(mViewModel);
        ActorsAdapter adapter = new ActorsAdapter(new ArrayList<Actor>());
        adapter.setItemClickListener(this);
        setupAdapters(mBinding.recyclerActor, adapter);
        return view;
    }

    @Override
    public void onActorItemClick(Actor actor) {
    }
}
