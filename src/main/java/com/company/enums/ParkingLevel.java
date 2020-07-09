package com.company.enums;

import lombok.Getter;

@Getter
public enum ParkingLevel {
    UP("UP"),
    DOWN("DOWN");
    String level;

    ParkingLevel(String level) {
        this.level = level;
    }
}

