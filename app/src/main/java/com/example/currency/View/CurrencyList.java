package com.example.currency.View;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.currency.DI.ServiceLocator;
import com.example.currency.Domain.Model.Currency;
import com.example.currency.ViewModel.CurrencyListViewModel;
import com.example.currency.databinding.CurrencyListFragmentBinding;

import java.util.List;

public class CurrencyList extends Fragment {
    CurrencyListFragmentBinding binding;
    CurrencyListViewModel currencyListViewModel;

    ArrayAdapter<CharSequence> adapter;

    Spinner upperSpinner;
    Spinner lowerSpinner;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        currencyListViewModel = new ViewModelProvider(this).get(CurrencyListViewModel.class);
        binding = CurrencyListFragmentBinding.inflate(getLayoutInflater(), container, false);

        upperSpinner = binding.upperSpinner;
        lowerSpinner = binding.lowerSpinner;

        currencyListViewModel.getData(getViewLifecycleOwner()).observe(getViewLifecycleOwner(),
                currencies -> {
                    System.out.println("Hello");
                    for (Currency currency: currencies){
                        System.out.println(currency.getConversion_rate() + " ");
                    }
                    currencyListViewModel.updateCurrencyCodes(currencies);
                    adapter = new ArrayAdapter(this.getContext(),
                            android.R.layout.simple_spinner_item, ServiceLocator.getInstance().getCurrencyList());
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    upperSpinner.setAdapter(adapter);
                    lowerSpinner.setAdapter(adapter);
                });

        binding.imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currencyListViewModel.getData(getViewLifecycleOwner()).observe(getViewLifecycleOwner(), new Observer<List<Currency>>() {
                    @Override
                    public void onChanged(List<Currency> currencyValues) {
                        currencyListViewModel.updateCurrencyCodes(currencyValues);
                        convertAndShow();
                    }
                });
            }
        });
        return binding.getRoot();
    }

    private void convertAndShow() {
        if (!binding.upperText.getText().toString().isEmpty()
                && !binding.upperSpinner.getSelectedItem().toString().isEmpty()
                && !binding.lowerSpinner.getSelectedItem().toString().isEmpty())
            binding.lowerText.setText(String.valueOf(
                    currencyListViewModel.convertValue(Float.parseFloat(binding.upperText.getText().toString()),
                            binding.upperSpinner.getSelectedItem().toString(),
                            binding.lowerSpinner.getSelectedItem().toString())));
    }
}
