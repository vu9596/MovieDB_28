package vunt.com.vn.moviedb_28.screen.trailers;

import android.content.Context;
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

public class TrailerFragment extends BaseFragment implements TrailerAdapter.ItemClickListener {

    private FragmentTrailerBinding mBinding;
    private OnTrailerSelectedListener mListener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mListener = (OnTrailerSelectedListener) context;
        } catch (ClassCastException e) {
            handleError(e.getMessage());
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_trailer, container, false);
        mBinding = FragmentTrailerBinding.bind(view);
        mBinding.setViewModel(mViewModel);
        TrailerAdapter adapter = new TrailerAdapter(new ArrayList<Video>());
        adapter.setItemClickListener(this);
        setupAdapters(mBinding.recyclerTrailer, adapter);
        return view;
    }

    @Override
    public void onTrailerItemClick(Video video) {
        mListener.onTrailerSelected(video);
    }

    private void handleError(String message) {
        //TODO handle error
    }

    public interface OnTrailerSelectedListener {
        public void onTrailerSelected(Video video);
    }
}
