package com.stockApp.detailed.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.stockApp.detailed.config.RedisConfiguration;
import com.stockApp.detailed.service.Impl.RedisCacheService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @Autowired
    private RedisCacheService redisCacheService;

    @Autowired
    private ObjectMapper objectMapper;

    @PostMapping("/auth/get")
    public ResponseEntity<?> getAuthToken(@RequestHeader String userId) {
        JSONObject result = new JSONObject();
        result.put("Token", redisCacheService.generateToken(userId));
        return new ResponseEntity<>(result.toString(), HttpStatus.OK);
    }
}
