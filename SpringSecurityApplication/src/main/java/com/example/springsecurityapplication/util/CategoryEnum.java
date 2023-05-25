package com.example.springsecurityapplication.util;

public enum CategoryEnum {
    furniture(1),
    appliances(2),
    clothes(3);

    private final int value;

    CategoryEnum(int v) {
        value = v;
    }

    public int getValue() {
        return value;
    }
}
