package com.stockApp.detailed.service.Impl;

import com.stockApp.detailed.exception.StockNotFoundException;
import com.stockApp.detailed.service.CacheService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class RedisCacheService implements CacheService {

    private final RedisTemplate<String, String> redisTemplate;
    @Override
    public void setStockPrice(String symbol, String price) {
        redisTemplate.opsForValue().set(symbol, price);
    }

    @Override
    public double getStockPrice(String symbol) throws StockNotFoundException {
        try {
            return Double.parseDouble(Objects.requireNonNull(redisTemplate.opsForValue().get(symbol)));
        }
        catch(NullPointerException ex) {
            throw new StockNotFoundException("Stock with symbol " + symbol + " not found in Redis");
        }
    }

    @Override
    public void deleteStock(String symbol) {
        redisTemplate.delete(symbol);
    }

    @Override
    public String getToken(String userId) {
        return redisTemplate.opsForValue().get(userId);
    }

    @Override
    public String generateToken(String userId) {
        Object token = redisTemplate.opsForValue().get(userId);
        if (token == null) {
            String newToken = UUID.randomUUID().toString();
            redisTemplate.opsForValue().set(userId, newToken);
            return newToken;
        }
        return (String)token;
    }
}
