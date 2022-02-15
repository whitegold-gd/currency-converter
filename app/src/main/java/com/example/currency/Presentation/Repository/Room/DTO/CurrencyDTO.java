package com.example.currency.Presentation.Repository.Room.DTO;

import androidx.room.ColumnInfo;
import androidx.room.Entity;

import com.example.currency.Domain.Model.Currency;

@Entity(tableName = "currency", primaryKeys = {"id"}, ignoredColumns = {"name", "conversion_rate"})
public class CurrencyDTO extends Currency {
    @ColumnInfo
    private String nameDTO;
    @ColumnInfo
    private String conversion_rateDTO;

    public CurrencyDTO() {
    }

    public CurrencyDTO(String name, float conversion_rate) {
        super(name, conversion_rate);
    }

    @Override
    public String getName() {
        if (super.getName() != null){
            super.setName(nameDTO);
        }
        return super.getName();
    }

    @Override
    public void setName(String nameDTO) {
        super.setName(nameDTO);
        this.nameDTO = nameDTO;
    }

    @Override
    public float getConversion_rate() {
        super.setConversion_rate(Float.parseFloat(conversion_rateDTO));
        return super.getConversion_rate();
    }

    @Override
    public void setConversion_rate(float conversion_rateDTO) {
        super.setConversion_rate(conversion_rateDTO);
        this.conversion_rateDTO = String.valueOf(conversion_rateDTO);
    }

    public String getNameDTO() {
        return nameDTO;
    }

    public void setNameDTO(String nameDTO) {
        this.nameDTO = nameDTO;
    }

    public String getConversion_rateDTO() {
        return conversion_rateDTO;
    }

    public void setConversion_rateDTO(String conversion_rateDTO) {
        this.conversion_rateDTO = conversion_rateDTO;
    }
}
