package com.example.currency.Presentation.Repository.Network.TransferLogic;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.currency.DI.ServiceLocator;
import com.example.currency.Domain.Model.Currency;
import com.example.currency.Presentation.Repository.Room.DTO.CurrencyDTO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CurrencyTransfer {
    private ExchangeRateAPI api;
    private final String API_KEY = "291805a57b36515965e9dadc";
    private final String API_HOST = "v6.exchangerate-api.com/v6/" + API_KEY;

    public CurrencyTransfer() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://" + API_HOST + "/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        this.api = retrofit.create(ExchangeRateAPI.class);
    }

    static class DataFromServer {
        String base_code;
        HashMap<String, Float> conversion_rates;

        public String getBase_code() {
            return base_code;
        }

        public void setBase_code(String base_code) {
            this.base_code = base_code;
        }

        public HashMap<String, Float> getConversion_rates() {
            return conversion_rates;
        }

        public void setConversion_rates(HashMap<String, Float> conversion_rates) {
            this.conversion_rates = conversion_rates;
        }
    }

    public MutableLiveData<List<Currency>> getExchangeRate(LifecycleOwner owner){
        MutableLiveData<List<Currency>> data = new MutableLiveData<>();

        api.listAddresses("USD").enqueue(new Callback<DataFromServer>() {
            @Override
            public void onResponse(Call<DataFromServer> call, Response<DataFromServer> response) {
                System.out.println("Hello123 " + response.code());
                System.out.println("Hello123 " + response.isSuccessful());
                System.out.println("Hello123 " + response.body());
                if (response.isSuccessful() && response.body()!= null){
                    ArrayList<Currency> currencyTransferValues = new ArrayList();
                    for (String code : response.body().conversion_rates.keySet()) {
                        currencyTransferValues.add(new CurrencyDTO(code,
                                response.body().conversion_rates.get(code)));

                        ServiceLocator.getInstance().getRepository().addCurrencyValue(new CurrencyDTO(code,
                                response.body().conversion_rates.get(code)));
                    }
                    data.setValue(currencyTransferValues);
                }
            }

            @Override
            public void onFailure(Call<DataFromServer> call, Throwable t) {
                System.out.println("Подгружены локальные данные.");
                ServiceLocator.getInstance().getRepository().getAllCurrencyValues(owner)
                        .observe(owner, data::setValue);
            }
        });
        return data;
    }
}
