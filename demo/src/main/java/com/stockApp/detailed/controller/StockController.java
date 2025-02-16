package com.stockApp.detailed.controller;

import com.stockApp.detailed.exception.StockNotFoundException;
import com.stockApp.detailed.model.Stock;
import com.stockApp.detailed.service.StockService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/stockApp")
public class StockController {

    private final StockService stockService;
    @GetMapping(path = "/get/stock")
    public ResponseEntity<?> getAllStocks() throws StockNotFoundException {
        List<Stock> stocks = stockService.getStockPrice();
        return new ResponseEntity<>(stocks, HttpStatus.OK);
    }

    @GetMapping(path = "/get/stock/{symbol}")
    public ResponseEntity<?> getStockBySymbol(@PathVariable(value = "symbol") String symbol) throws StockNotFoundException {
        Stock stock = stockService.getStockPrice(symbol);
        return new ResponseEntity<>(stock, HttpStatus.OK);
    }

    @PostMapping(path = "/add/stock")
    public ResponseEntity<?> addStock(@RequestHeader(value = "stock-symbol") String symbol) {
        stockService.addStock(symbol);
        return new ResponseEntity<>("Added successfully", HttpStatus.OK);
    }

}
