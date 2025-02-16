package com.stockApp.detailed.service.Impl;

import com.stockApp.detailed.exception.StockNotFoundException;
import com.stockApp.detailed.model.Stock;
import com.stockApp.detailed.service.CacheService;
import com.stockApp.detailed.service.StockService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@RequiredArgsConstructor
public class StockServiceImpl implements StockService {

    private Set<String> trackedStocks = new HashSet<>();
    private final CacheService redisCacheService;
    private Map<String, String> testMap = new HashMap<>();

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
