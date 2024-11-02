package com.stockApp.detailed.externalAPIHandler;

import com.stockApp.detailed.model.Stock;

import java.util.List;

public interface ExternalAPIHandler {
    void updateStockPrice(List<String> stocks);
}
