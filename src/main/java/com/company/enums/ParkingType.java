package com.company.enums;

public enum ParkingType {

    ELDER, ROYAL, NORMAL;

    public static ParkingType getEnum(String value) {
        if (null == value)
            return null;
        return ParkingType.valueOf(value);
    }

    public static String getStringValue(ParkingType type) {
        if (null == type)
            return null;
        return type.name();
    }

}
