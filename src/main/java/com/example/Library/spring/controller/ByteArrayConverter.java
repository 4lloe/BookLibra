package com.example.Library.spring.controller;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Converter
public class ByteArrayConverter implements AttributeConverter<byte[], String> {

    @Override
    public String convertToDatabaseColumn(byte[] attribute) {
        return attribute == null ? null : new String(Base64.getEncoder().encode(attribute), StandardCharsets.UTF_8);
    }

    @Override
    public byte[] convertToEntityAttribute(String dbData) {
        return dbData == null ? null : Base64.getDecoder().decode(dbData);
    }
}

