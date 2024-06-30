package io.takomuraragi.models;

public record ConversionAPI(String baseCode,
                         String targetCode,
                         double conversionRate,
                         double conversionResult) {
}
