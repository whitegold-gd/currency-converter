package com.example.currency.Presentation.Repository.Room.DAO;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.currency.Presentation.Repository.Room.DTO.CurrencyDTO;

import java.util.List;

@Dao
public interface CurrencyDAO {
    @Update
    void addCurrencyValue(CurrencyDTO currency);

    @Query("SELECT * FROM currency")
    LiveData<List<CurrencyDTO>> getAllCurrencyValues();
}
