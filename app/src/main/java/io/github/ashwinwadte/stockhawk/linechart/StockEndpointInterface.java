package io.github.ashwinwadte.stockhawk.linechart;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Ashwin on 23-Aug-16
 */
public interface StockEndpointInterface {
    @GET("v1/public/yql?&format=json&diagnostics=true&env=store://datatables.org/alltableswithkeys&callback=")
    Call<List<Stock>> getData(@Query("q") String symbol);
}