package vunt.com.vn.moviedb_28.screen.producer;

import android.databinding.ObservableField;

import vunt.com.vn.moviedb_28.data.model.Company;

public class ItemProduceViewModel {
    public final ObservableField<Company> companyObservable = new ObservableField<>();

    public void setCompany(Company company) {
        companyObservable.set(company);
    }
}
