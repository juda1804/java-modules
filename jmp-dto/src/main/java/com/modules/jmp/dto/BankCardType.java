package com.modules.jmp.dto;

public enum BankCardType {
    CREDIT,DEBIT;

    public static BankCardType fromString(String value) {
        for (BankCardType type : BankCardType.values()) {
            if (type.name().equalsIgnoreCase(value)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Unknown enum value: " + value);
    }

    // Convert an enum to a string
    @Override
    public String toString() {
        return this.name();
    }

}
