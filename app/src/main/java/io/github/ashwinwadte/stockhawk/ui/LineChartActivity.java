package io.github.ashwinwadte.stockhawk.ui;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.Toast;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

import io.github.ashwinwadte.stockhawk.R;
import io.github.ashwinwadte.stockhawk.data.QuoteColumns;
import io.github.ashwinwadte.stockhawk.linechart.Stock;
import io.github.ashwinwadte.stockhawk.linechart.StockEndpointInterface;
import io.github.ashwinwadte.stockhawk.linechart.StocksDeserializer;
import io.github.ashwinwadte.stockhawk.utils.Constants;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LineChartActivity extends Activity implements Callback<List<Stock>> {
    LineChart mLineChart;
    String mStockSymbol;
    String mEndDate, mStartDate;
    Type mListType = new TypeToken<List<Stock>>() {
    }.getType();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        mStockSymbol = intent.getExtras().getString(QuoteColumns.SYMBOL);

        setContentView(R.layout.activity_line_graph);
        mLineChart = (LineChart) findViewById(R.id.linechart);

        SimpleDateFormat dateFormat = new SimpleDateFormat(Constants.DATE_FORMAT);

        Calendar calendar = Calendar.getInstance();
        mEndDate = dateFormat.format(calendar.getTime());
        calendar.add(Calendar.MONTH, -1);
        mStartDate = dateFormat.format(calendar.getTime());

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://query.yahooapis.com/")
                .addConverterFactory(GsonConverterFactory
                        .create(new GsonBuilder()
                                .registerTypeAdapter(mListType, new StocksDeserializer()
                                ).create()
                        )
                ).build();

        StockEndpointInterface stockDataEndpoint = retrofit.create(StockEndpointInterface.class);

        String query = "select * from yahoo.finance.historicaldata where symbol= '" + mStockSymbol + "' and startDate = '" + mStartDate + "' and endDate ='" + mEndDate + "'";

        Call<List<Stock>> call = stockDataEndpoint.getData(query);
        call.enqueue(this);
    }

    @Override
    public void onResponse(Call<List<Stock>> call, Response<List<Stock>> response) {
        drawChart(response.body());
    }

    @Override
    public void onFailure(Call<List<Stock>> call, Throwable t) {
        Toast.makeText(LineChartActivity.this, R.string.chart_load_failed, Toast.LENGTH_SHORT).show();
    }

    private void drawChart(List<Stock> stockList) {
        try {
            Collections.reverse(stockList);

            XAxis xAxis = mLineChart.getXAxis();
            xAxis.setTextSize(14f);

            ArrayList<String> xValues = new ArrayList<>();
            ArrayList<Entry> yValues = new ArrayList<>();


            for (int i = 0; i < stockList.size(); i++) {
                xValues.add(i, stockList.get(i).getDate());

                yValues.add(new Entry(Float.valueOf(stockList.get(i).getClose()), i));
            }

            LineDataSet dataSet = new LineDataSet(yValues, mStockSymbol);
            LineData lineData = new LineData(dataSet);
            mLineChart.setData(lineData);
            mLineChart.setDescription(getResources().getString(R.string.stock_graph));
            mLineChart.setDescriptionTextSize(18f);
            mLineChart.getLegend().setTextSize(16f);
            mLineChart.setPinchZoom(false);
            mLineChart.invalidate();
        } catch (NumberFormatException | Resources.NotFoundException | NullPointerException e) {
            e.printStackTrace();
        }
    }
}
