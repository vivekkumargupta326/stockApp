package com.stockApp.detailed.service;

import com.stockApp.detailed.exception.StockNotFoundException;
import com.stockApp.detailed.model.Stock;

import java.util.List;

public interface StockService {
    List<Stock> getStockPrice() throws StockNotFoundException;
    Stock getStockPrice(String symbol) throws StockNotFoundException;

    void addStock(String symbol);

    List<String> getAllStocks();

    void deleteStock(String symbol);

}
