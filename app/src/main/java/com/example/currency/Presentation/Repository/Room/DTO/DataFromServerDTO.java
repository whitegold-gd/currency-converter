package com.example.currency.Presentation.Repository.Room.DTO;

import androidx.room.ColumnInfo;
import androidx.room.Entity;

import com.example.currency.Domain.Model.DataFromServerTemp;
import com.google.gson.Gson;

import java.util.HashMap;

@Entity(tableName = "dataFromServer", primaryKeys = {"base_codeDTO"}, ignoredColumns = {"base_code", "conversion_rates"})
public class DataFromServerDTO extends DataFromServerTemp {
    @ColumnInfo
    public String base_codeDTO;
    @ColumnInfo
    public String conversion_ratesDTO;

    public DataFromServerDTO() {
    }

    public String getBase_codeDTO() {
        return base_codeDTO;
    }

    public void setBase_codeDTO(String base_codeDTO) {
        this.base_codeDTO = base_codeDTO;
    }

    public String getConversion_ratesDTO() {
        return conversion_ratesDTO;
    }

    public void setConversion_ratesDTO(String conversion_ratesDTO) {
        this.conversion_ratesDTO = conversion_ratesDTO;
    }

    @Override
    public void setBase_code(String base_code) {
        super.setBase_code(base_code);
        this.base_codeDTO = base_code;
    }

    @Override
    public void setConversion_rates(HashMap<String, Float> conversion_rates) {
        super.setConversion_rates(conversion_rates);
        this.conversion_ratesDTO = new Gson().toJson(conversion_rates);
    }

    @Override
    public String getBase_code() {
        if (super.getBase_code() != null){
            super.setBase_code(base_codeDTO);
        }
        return super.getBase_code();
    }

    @Override
    public HashMap<String, Float> getConversion_rates() {
        if (super.getConversion_rates() != null){
            super.setConversion_rates(new Gson().fromJson(this.conversion_ratesDTO, HashMap.class));
        }
        return super.getConversion_rates();
    }
}
