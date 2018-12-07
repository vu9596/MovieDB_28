package vunt.com.vn.moviedb_28.screen.producer;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import vunt.com.vn.moviedb_28.R;
import vunt.com.vn.moviedb_28.data.model.Company;
import vunt.com.vn.moviedb_28.databinding.FragmentProduceBinding;
import vunt.com.vn.moviedb_28.screen.BaseFragment;

public class ProduceFragment extends BaseFragment implements ProduceAdapter.ItemClickListener {
    private FragmentProduceBinding mBinding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_produce, container, false);
        mBinding = FragmentProduceBinding.bind(view);
        mBinding.setViewModel(mViewModel);
        ProduceAdapter adapter = new ProduceAdapter(new ArrayList<Company>());
        adapter.setItemClickListener(this);
        setupAdapters(mBinding.recyclerProducer, adapter);
        return view;
    }

    @Override
    public void onProduceItemClick(Company company) {
    }
}
