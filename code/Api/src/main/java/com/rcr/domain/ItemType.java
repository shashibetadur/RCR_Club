package com.rcr.domain;

public enum ItemType {
    WHOLE('W'), COCKTAIL('C'), USABLE('U'), OTHER('O');

    private char code;

    ItemType(char c) {
        this.code = c;
    }

    public char getCode() {
        return code;
    }

    public static String getNameByCode(char code) {
        for (ItemType itemType : ItemType.values()) {
            if (itemType.getCode() == code) {
                return itemType.name();
            }
        }
        throw new RuntimeException("Invalid code");
    }
}
