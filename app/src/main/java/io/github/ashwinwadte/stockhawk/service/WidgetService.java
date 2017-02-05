package io.github.ashwinwadte.stockhawk.service;

import android.content.Intent;
import android.widget.RemoteViewsService;

import io.github.ashwinwadte.stockhawk.data.WidgetDataProvider;

public class WidgetService extends RemoteViewsService {
    @Override
    public RemoteViewsService.RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new WidgetDataProvider(this, intent);

    }
}
