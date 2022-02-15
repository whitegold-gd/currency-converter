package com.example.currency.Presentation.Repository.Room.DTO;

import androidx.room.ColumnInfo;
import androidx.room.Entity;

import com.example.currency.Domain.Model.Currency;

@Entity(tableName = "currency", primaryKeys = {"id"})
public class CurrencyDTO extends Currency {

    public CurrencyDTO() {
    }

    public CurrencyDTO(String name, float conversion_rate) {
        super(name, conversion_rate);
    }

}
