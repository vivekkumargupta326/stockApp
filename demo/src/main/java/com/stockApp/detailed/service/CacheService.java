package com.stockApp.detailed.service;

import com.stockApp.detailed.exception.StockNotFoundException;

public interface CacheService {
    void setStockPrice(String symbol, String price);
    double getStockPrice(String symbol) throws StockNotFoundException;
    void deleteStock(String symbol);

    String getToken(String userId);

    String generateToken(String userId);
}
