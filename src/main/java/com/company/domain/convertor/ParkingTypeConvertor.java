package com.company.domain.convertor;

import com.company.enums.ParkingType;
import com.company.enums.VehicleType;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class ParkingTypeConvertor implements AttributeConverter<ParkingType, String> {


    @Override
    public String convertToDatabaseColumn(ParkingType type) {

        return ParkingType.getStringValue(type);
    }

    @Override
    public ParkingType convertToEntityAttribute(String dbData) {

        return ParkingType.getEnum(dbData);
    }
}