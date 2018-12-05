package vunt.com.vn.moviedb_28.screen.producer;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import vunt.com.vn.moviedb_28.R;
import vunt.com.vn.moviedb_28.databinding.FragmentProduceBinding;
import vunt.com.vn.moviedb_28.screen.BaseFragment;

public class ProduceFragment extends BaseFragment {
    private FragmentProduceBinding mBinding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_produce, container, false);
        mBinding = FragmentProduceBinding.bind(view);
        mBinding.setViewModel(mViewModel);
        setupAdapters(mBinding.recyclerProducer,
                new ProduceAdapter(mViewModel.getMovie().getProductionCompanies()));
        return view;
    }
}
