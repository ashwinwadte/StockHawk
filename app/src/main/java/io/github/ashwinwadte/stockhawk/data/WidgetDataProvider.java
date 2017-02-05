/**
 * Created by Ashwin on 23-Aug-16
 */
package io.github.ashwinwadte.stockhawk.data;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.graphics.Color;
import android.os.Binder;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import java.util.ArrayList;
import java.util.List;

import io.github.ashwinwadte.stockhawk.R;
import io.github.ashwinwadte.stockhawk.linechart.Stock;

public class WidgetDataProvider implements RemoteViewsService.RemoteViewsFactory {
    QuoteProvider quoteProvider = new QuoteProvider();
    List<Stock> collection = new ArrayList<>();
    Context context;
    Intent intent;
    private Cursor mCursor;

    public WidgetDataProvider(Context context, Intent intent) {
        this.context = context;
        this.intent = intent;
    }

    private void initData() {
        collection.clear();

        final long identityToken = Binder.clearCallingIdentity();
        mCursor = context.getContentResolver().query(QuoteProvider.Quotes.CONTENT_URI, null, null, null, null);

        DatabaseUtils.dumpCursor(mCursor);

        int index = mCursor.getColumnIndex(QuoteColumns.SYMBOL);

        if (mCursor != null) {
            while (mCursor.moveToNext()) {

                Stock stock = new Stock();
                stock.setSymbol(mCursor.getString(mCursor.getColumnIndex(QuoteColumns.SYMBOL)));
                stock.setBid_Price(mCursor.getString(mCursor.getColumnIndex(QuoteColumns.BIDPRICE)));
                stock.setPercent_Change(mCursor.getString(mCursor.getColumnIndex(QuoteColumns.PERCENT_CHANGE)));
                stock.setChange(mCursor.getString(mCursor.getColumnIndex(QuoteColumns.CHANGE)));
                collection.add(stock);
            }
        }
//        mCursor.close();
        Binder.restoreCallingIdentity(identityToken);
    }

    @Override
    public void onCreate() {
//        initData();
    }

    @Override
    public void onDataSetChanged() {
        initData();
    }

    @Override
    public void onDestroy() {
        if (mCursor != null) {
            mCursor.close();
            mCursor = null;
        }
    }

    @Override
    public int getCount() {
        return collection.size();
    }

    @Override
    public RemoteViews getViewAt(int position) {
        RemoteViews remoteView = new RemoteViews(context.getPackageName(),
                R.layout.widget_layout);
        remoteView.setTextViewText(R.id.symbol, collection.get(position).getSymbol());
        remoteView.setTextViewText(R.id.bid_price, collection.get(position).getBid_Price());
        remoteView.setContentDescription(R.id.bid_price, context.getResources().getString(R.string.bid_price) + collection.get(position).getBid_Price());

        remoteView.setTextViewText(R.id.change, collection.get(position).getPercent_Change());
        remoteView.setContentDescription(R.id.change, context.getResources().getString(R.string.change) + collection.get(position).getPercent_Change());

        remoteView.setTextViewText(R.id.change, collection.get(position).getChange());
        remoteView.setContentDescription(R.id.change, context.getResources().getString(R.string.change) + collection.get(position).getChange());

        remoteView.setTextColor(R.layout.widget_layout, Color.BLACK);

        return remoteView;
    }

    @Override
    public RemoteViews getLoadingView() {
        return new RemoteViews(context.getPackageName(), R.layout.widget_layout);
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public long getItemId(int position) {
        if (mCursor.moveToPosition(position))
            return mCursor.getLong(0);
        return position;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }
}
