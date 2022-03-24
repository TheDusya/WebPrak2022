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
public class JsonAddress implements Serializable {
    @JsonProperty("страна")
    public String country;

    @JsonProperty("город")
    public String city;

    @JsonProperty ("улица")
    public String street;

    @JsonProperty ("переулок")
    public String per;

    @JsonProperty ("набережная")
    public String nab;

    @JsonProperty ("дом")
    public String house;
}
