package com.stockApp.detailed.externalAPIHandler.Impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stockApp.detailed.externalAPIHandler.ExternalAPIHandler;
import com.stockApp.detailed.model.Stock;
import com.stockApp.detailed.service.CacheService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AlphaVantageAPIHandlerImpl implements ExternalAPIHandler {

    private final RestTemplate restTemplate;
    private final CacheService redisCacheService;

    @Value("${alphaVantage.api.key}")
    private String apiKey;

    private final ObjectMapper objectMapper;

    @Override
    public void updateStockPrice(List<String> stocks) {
        stocks.forEach(symbol -> {
            String baseUrl = "https://www.alphavantage.co/query?function=TIME_SERIES_INTRADAY&symbol=<SYMBOL>&interval=5min&apikey=";
            baseUrl = baseUrl.replace("<SYMBOL>", symbol) + apiKey;
            String fullUrl = UriComponentsBuilder.fromHttpUrl(baseUrl)
                    .toUriString();
            String response = restTemplate.getForObject(fullUrl, String.class);
            try {
                JsonNode jsonNode = objectMapper.readTree(response);
                jsonNode = jsonNode.get("Time Series (5min)");
                Iterator<Map.Entry<String, JsonNode>> fields = jsonNode.fields();
                Map.Entry<String, JsonNode> firstEntry = fields.next();
                String firstKey = firstEntry.getKey();
                JsonNode firstValue = firstEntry.getValue();
                String price = firstValue.get("1. open").asText();
                redisCacheService.setStockPrice(symbol, price);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }

        });

    }
}
