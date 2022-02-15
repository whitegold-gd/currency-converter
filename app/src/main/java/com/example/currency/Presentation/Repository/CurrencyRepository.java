package com.example.currency.Presentation.Repository;

import android.app.Application;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.room.RoomDatabase;

import com.example.currency.Domain.Model.Currency;
import com.example.currency.Presentation.Repository.Room.DAO.CurrencyDAO;
import com.example.currency.Presentation.Repository.Room.DTO.CurrencyDTO;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;
import java.util.UUID;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CurrencyRepository implements RepositoryTasks{
    private CurrencyDAO currencyDAO;
    private LiveData<List<CurrencyDTO>> currencies;

    public CurrencyRepository(Application application){
        CurrencyRoomDatabase database = CurrencyRoomDatabase.getDatabase(application);
        currencyDAO = database.currencyDAO();
    }

    @Override
    public <T extends Currency> void addCurrencyValue(T currency) {
        currencyDAO.addCurrencyValue((CurrencyDTO) currency);
    }

    @Override
    public MutableLiveData<List<CurrencyDTO>> getAllCurrencyValues(LifecycleOwner owner) {
        MutableLiveData<List<CurrencyDTO>> result = new MutableLiveData<>();
        currencyDAO.getAllCurrencyValues().observe(owner, result::setValue);
        return result;
    }

}
