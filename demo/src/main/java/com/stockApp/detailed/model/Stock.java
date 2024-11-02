package com.stockApp.detailed.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@JsonSerialize
public class Stock {
    @JsonProperty("Name")
    private String name;

    @JsonProperty("Price")
    private double price;

    @JsonProperty("Timestamp")
    private String timestamp;
}
