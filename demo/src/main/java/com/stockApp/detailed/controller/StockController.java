package com.stockApp.detailed.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/stockApp")
public class StockController {
    @GetMapping(path = "/get/stock")
    public ResponseEntity<?> getAllStocks() {
        return new ResponseEntity<>("SUCCESS", HttpStatus.OK);
    }

    @GetMapping(path = "/get/stock/{symbol}")
    public ResponseEntity<?> getStockBySymbol(@PathVariable(value = "symbol") String symbol) {
        return new ResponseEntity<>(symbol, HttpStatus.OK);
    }

    @PostMapping(path = "/add/stock")
    public ResponseEntity<?> addStock(@RequestHeader(value = "stock-symbol") String symbol) {
        return new ResponseEntity<>(symbol, HttpStatus.OK);
    }
}
