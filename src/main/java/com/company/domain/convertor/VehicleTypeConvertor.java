package com.company.domain.convertor;

import com.company.enums.VehicleType;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class VehicleTypeConvertor implements AttributeConverter<VehicleType, String> {


    @Override
    public String convertToDatabaseColumn(VehicleType status) {

        return VehicleType.getStringValue(status);
    }

    @Override
    public VehicleType convertToEntityAttribute(String dbData) {

        return VehicleType.getEnum(dbData);
    }
}