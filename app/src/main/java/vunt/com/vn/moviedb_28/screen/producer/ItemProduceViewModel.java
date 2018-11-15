package vunt.com.vn.moviedb_28.screen.producer;

import android.databinding.ObservableField;
import android.view.View;

import vunt.com.vn.moviedb_28.data.model.Company;

public class ItemProduceViewModel {
    public final ObservableField<Company> companyObservable = new ObservableField<>();
    private ProduceAdapter.ItemClickListener mItemClickListener;

    public ItemProduceViewModel(ProduceAdapter.ItemClickListener itemClickListener) {
        mItemClickListener = itemClickListener;
    }

    public void setCompany(Company company) {
        companyObservable.set(company);
    }

    public void onItemClicked(View view) {
        if (mItemClickListener == null || companyObservable.get() == null) {
            return;
        }
        mItemClickListener.onProduceItemClick(companyObservable.get());
    }
}
