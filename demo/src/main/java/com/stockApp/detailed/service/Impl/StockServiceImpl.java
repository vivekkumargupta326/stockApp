package com.stockApp.detailed.service.Impl;

import com.stockApp.detailed.exception.StockNotFoundException;
import com.stockApp.detailed.model.Stock;
import com.stockApp.detailed.service.CacheService;
import com.stockApp.detailed.service.StockService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class StockServiceImpl implements StockService {

    private final Set<String> trackedStocks = new HashSet<>();
    private final CacheService redisCacheService;

    @Override
    public List<Stock> getStockPrice() throws StockNotFoundException {
        List<Stock> allStockPrices = new ArrayList<>();
        for (String symbol: trackedStocks) {
            double price = redisCacheService.getStockPrice(symbol);
            allStockPrices.add(
                    Stock.builder()
                            .name(symbol)
                            .price(price)
                            .build()
            );
        }
        return allStockPrices;
    }

    @Override
    public Stock getStockPrice(String symbol) throws StockNotFoundException {
        double price = redisCacheService.getStockPrice(symbol);
        return Stock.builder()
                .name(symbol)
                .price(price)
                .build();
    }

    @Override
    public List<String> getAllStocks() {
        return new ArrayList<>(trackedStocks);
    }

    @Override
    public void addStock(String symbol) {
        trackedStocks.add(symbol);
    }

    @Override
    public void deleteStock(String symbol) {
        trackedStocks.remove(symbol);
        redisCacheService.deleteStock(symbol);
    }
}
