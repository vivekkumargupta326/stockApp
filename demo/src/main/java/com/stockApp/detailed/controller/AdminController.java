package com.stockApp.detailed.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/stockApp")
public class AdminController {
    @PostMapping(path = "/delete/stock")
    public ResponseEntity<?> deleteStockBySymbol(@RequestHeader(value = "stock-symbol") String symbol) {
        return new ResponseEntity<>("Deleted", HttpStatus.OK);
    }
}
