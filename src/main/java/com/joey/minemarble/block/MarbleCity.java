package com.joey.minemarble.block;

import java.util.HashMap;
import java.util.Map;

public class MarbleCity extends MarbleBlock {
    Map<String, Integer> price = new HashMap<>();

    MarbleCity(String name, int passPrice, int hotelPrice, int buildingPrice, int villaPrice) {
        super(name);

        price.put("pass", passPrice);
        price.put("hotel", hotelPrice);
        price.put("building", buildingPrice);
        price.put("villa", villaPrice);
    }

    public void act() {

    }
}
