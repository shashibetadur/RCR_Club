package com.rcr.domain;

public enum OrderStatus {
    IN_PROCESS('P'), REJECTED('R'), FOR_APPROVAL('F'), APPROVED('A');

    private char code;

    OrderStatus(char c) {
        this.code = c;
    }

    public char getCode() {
        return code;
    }

    public static String getNameByCode(char code) {
        for (OrderStatus orderStatus : OrderStatus.values()) {
            if (orderStatus.getCode() == code) {
                return orderStatus.name();
            }
        }
        throw new RuntimeException("Invalid code");
    }
}
