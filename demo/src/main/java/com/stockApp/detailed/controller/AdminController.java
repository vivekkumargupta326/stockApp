package com.stockApp.detailed.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.TextNode;
import com.stockApp.detailed.service.StockService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/stockApp")
public class AdminController {
    private final StockService stockService;
    @Value("${api.key}")
    private String apiKey;

    @Autowired
    private ObjectMapper objectMapper;

    @PostMapping(path = "/delete/stock")
    public ResponseEntity<?> deleteStockBySymbol(@RequestHeader(value = "stock-symbol") String symbol) {
        stockService.deleteStock(symbol);
        return new ResponseEntity<>("Deleted", HttpStatus.OK);
    }

    @GetMapping(path = "/get/test")
    public void getTest() {
        return;
    }
}
