package com.mcapp.springapp.common.dto;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum Direction {
	S, E, N, W, SE, NE, SW, NW;
	
	
    private static Map<String, Direction> namesMap = new HashMap<String, Direction>(8);

    static {
        namesMap.put("s", S);
        namesMap.put("e", E);
        namesMap.put("n", N);
        namesMap.put("w", W);
        namesMap.put("se", SE);
        namesMap.put("ne", NE);
        namesMap.put("sw", SW);
        namesMap.put("nw", NW);
    }

    @JsonCreator
    public static Direction forValue(String value) {
        return namesMap.get(value.toLowerCase());
    }

    @JsonValue
    public String toValue() {
        for (Entry<String, Direction> entry : namesMap.entrySet()) {
            if (entry.getValue() == this)
                return entry.getKey();
        }

        return null; // or fail
    }
}
