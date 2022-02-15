package com.example.currency.Presentation.Repository.Network.TransferLogic;

import com.example.currency.Domain.Model.DataFromServerTemp;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;

public interface ExchangeRateAPI {
    @GET("latest/{currency}")
    @Headers({
            "Accept: application/json"
    })
    Call<CurrencyTransfer.DataFromServer> listAddresses(
            @Path("currency") String currency);
}
