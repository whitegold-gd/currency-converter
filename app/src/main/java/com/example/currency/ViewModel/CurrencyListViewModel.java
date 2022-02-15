package com.example.currency.ViewModel;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.currency.DI.ServiceLocator;
import com.example.currency.Domain.Model.Currency;
import com.example.currency.Presentation.Repository.Network.TransferLogic.CurrencyTransfer;

import java.util.List;

public class CurrencyListViewModel extends ViewModel {
    public LiveData<List<Currency>> getData(LifecycleOwner owner){
        return ServiceLocator.getInstance().getCurrencyTransfer().getExchangeRate(owner);
    }

    public void updateCurrencyCodes(List<Currency> currencies){
        ServiceLocator.getInstance().updateCodes(currencies);
    }

    public float convertValue(float sum, String firstCode, String secondCode){
        return ServiceLocator.getInstance().convertValue(sum, firstCode, secondCode);
    }
}
