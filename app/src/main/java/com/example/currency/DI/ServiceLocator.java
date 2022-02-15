package com.example.currency.DI;

import android.app.Application;

import com.example.currency.Domain.Model.Currency;
import com.example.currency.Presentation.Repository.CurrencyRepository;
import com.example.currency.Presentation.Repository.Network.TransferLogic.CurrencyTransfer;
import com.example.currency.Presentation.Repository.RepositoryTasks;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class ServiceLocator {
    static ServiceLocator serviceLocator;
    private RepositoryTasks repositoryTasks;
    CurrencyTransfer currencyTransfer;

    List<Currency> currencyList;
    List<String> currencyCodes;
    HashMap<String, Float> currencyMap;

    ServiceLocator(){
        currencyCodes = new ArrayList<>();
        currencyMap = new HashMap<>();
    }

    static public ServiceLocator getInstance(){
        if (serviceLocator == null){
            serviceLocator = new ServiceLocator();
        }
        return serviceLocator;
    }

    public CurrencyTransfer getCurrencyTransfer(){
        if (currencyTransfer == null){
            currencyTransfer = new CurrencyTransfer();
        }
        return currencyTransfer;
    }

    public void initializeRepository(Application application){
        if (repositoryTasks == null){
            repositoryTasks = new CurrencyRepository(application);
        }
    }

    public RepositoryTasks getRepository(){
        return repositoryTasks;
    }

    public List<Currency> getCurrencyList() {
        return currencyList;
    }

    public void updateCodes(List<Currency> currencyValues) {
        this.currencyList = currencyValues;
        for (Currency currencyValue : currencyValues) {
            currencyMap.put(currencyValue.getName(), currencyValue.getConversion_rate());
            currencyCodes.add(currencyValue.getName());
        }
        Collections.sort(currencyCodes);
    }

    public float convertValue(float value, String code1, String code2) {
        float fac1 = currencyMap.get(code1);
        float fac2 = currencyMap.get(code2);
        return value * (fac2/fac1);
    }
}
