package vunt.com.vn.moviedb_28.screen.producer;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

import vunt.com.vn.moviedb_28.R;
import vunt.com.vn.moviedb_28.data.model.Company;
import vunt.com.vn.moviedb_28.databinding.ItemProducerBinding;

public class ProduceAdapter extends RecyclerView.Adapter<ProduceAdapter.ViewHolder> {
    private List<Company> mCompanies;
    private ItemClickListener mItemClickListener;

    public ProduceAdapter(List<Company> companies) {
        mCompanies = companies;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        ItemProducerBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(viewGroup.getContext()),
                R.layout.item_producer,
                viewGroup,
                false
        );
        return new ViewHolder(binding, mItemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.bindData(mCompanies.get(i));
    }

    @Override
    public int getItemCount() {
        return mCompanies != null ? mCompanies.size() : 0;
    }

    public ProduceAdapter setItemClickListener(ItemClickListener itemClickListener) {
        mItemClickListener = itemClickListener;
        return this;
    }

    public void replaceData(List<Company> companies) {
        mCompanies.clear();
        mCompanies.addAll(companies);
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private ItemProducerBinding mBinding;
        private ItemProduceViewModel mItemProduceViewModel;

        public ViewHolder(ItemProducerBinding binding, ItemClickListener listener) {
            super(binding.getRoot());
            mBinding = binding;
            mItemProduceViewModel = new ItemProduceViewModel(listener);
            mBinding.setViewModel(mItemProduceViewModel);
        }

        public void bindData(Company company) {
            mItemProduceViewModel.setCompany(company);
            mBinding.executePendingBindings();
        }
    }

    public interface ItemClickListener {
        void onProduceItemClick(Company company);
    }
}
