package com.stockApp.detailed.service.Impl;

import com.stockApp.detailed.externalAPIHandler.ExternalAPIHandler;
import com.stockApp.detailed.service.CacheService;
import com.stockApp.detailed.service.SchedulerService;
import com.stockApp.detailed.service.StockService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SchedulerServiceImpl implements SchedulerService {

    private final StockService stockService;
    private final ExternalAPIHandler externalAPIHandler;

    @Scheduled(fixedDelay = 60000)
    public void updateStockPrices() {
        externalAPIHandler.updateStockPrice(stockService.getAllStocks());
    }
}
