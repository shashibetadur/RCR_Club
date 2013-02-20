package com.rcr.domain;

public enum MaterialType {
    SALEABLE('S'), CONSUMABLE('C'), ASSET('A'), OTHER('O');

    private char code;

    MaterialType(char c) {
        this.code = c;
    }

    public char getCode() {
        return code;
    }

    public static String getNameByCode(char code) {
        for (MaterialType materialType : MaterialType.values()) {
            if (materialType.getCode() == code) {
                return materialType.name();
            }
        }
        throw new RuntimeException("Invalid code");
    }
}
