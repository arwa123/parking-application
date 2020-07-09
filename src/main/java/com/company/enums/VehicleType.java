package com.company.enums;

public enum VehicleType {
    BIKE,
    CAR;

    public static VehicleType getEnum(String value) {
        if (null == value)
            return null;
        return VehicleType.valueOf(value);
    }

    public static String getStringValue(VehicleType type) {
        if (null == type)
            return null;
        return type.name();
    }

}
