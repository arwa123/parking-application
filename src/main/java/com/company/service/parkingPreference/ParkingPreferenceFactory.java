package com.company.service.parkingPreference;


import com.company.enums.ParkingType;

public class ParkingPreferenceFactory {
    public static IParkingPreference getParkingType(ParkingType parkingType) {
        switch (parkingType) {
            case ELDER:
                return new ElderPreferenceImpl();
            case ROYAL:
                return new RoyalPreferenceImpl();
            case NORMAL:
            default:
                return new NoPreferenceImpl();
        }
    }
}
