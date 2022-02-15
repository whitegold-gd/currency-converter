package com.example.currency.Presentation.Repository;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;

import com.example.currency.Domain.Model.Currency;

import java.util.List;

public interface RepositoryTasks {
    <T extends Currency> void addCurrencyValue(T currency);
    <T extends Currency> LiveData<List<T>> getAllCurrencyValues(LifecycleOwner owner);
}
