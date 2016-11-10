package com.mcapp.springapp.common.dto;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum Direction {
	Vertical, Horizontal, VerticalB, HorizontalB, DiagonalDown, DiagonalUp, DiagonalDownLeft, DiagonalUpLeft;
	
	
    private static Map<String, Direction> namesMap = new HashMap<String, Direction>(8);

    static {
        namesMap.put("vertical", Vertical);
        namesMap.put("horizontal", Horizontal);
        namesMap.put("verticalb", VerticalB);
        namesMap.put("horizontalb", HorizontalB);
        namesMap.put("diagonaldown", DiagonalDown);
        namesMap.put("diagonalup", DiagonalUp);
        namesMap.put("diagonaldownleft", DiagonalDownLeft);
        namesMap.put("diagonalupleft", DiagonalUpLeft);
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
