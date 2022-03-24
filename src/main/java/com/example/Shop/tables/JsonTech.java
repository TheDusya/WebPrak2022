package com.example.Shop.tables;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class JsonTech implements Serializable {
    @JsonProperty("модель")
    public String model;

    @JsonProperty("гарантия, мес")
    public Integer guarantee;
}
