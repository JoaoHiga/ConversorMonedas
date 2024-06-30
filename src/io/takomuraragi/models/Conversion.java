package io.takomuraragi.models;

import io.takomuraragi.exepciones.ConversionNoValidaException;

public class Conversion {
    private String divisaOrigen;
    private String divisaDestino;
    private double montoConvertido;
    private double ratioConversion;
    private double resultadoConversion;

    public Conversion(ConversionAPI conversionAPI, double montoConvertido){
        if (conversionAPI.baseCode() == null){
            throw new ConversionNoValidaException("Conversión no válida.");
        }
        this.divisaOrigen = conversionAPI.baseCode();
        this.divisaDestino = conversionAPI.targetCode();
        this.montoConvertido = montoConvertido;
        this.ratioConversion = conversionAPI.conversionRate();
        this.resultadoConversion = conversionAPI.conversionResult();
    }

    public String getDivisaOrigen() {
        return divisaOrigen;
    }

    public String getDivisaDestino() {
        return divisaDestino;
    }

    public double getMontoConvertido() {
        return montoConvertido;
    }

    public double getRatioConversion() {
        return ratioConversion;
    }

    public double getResultadoConversion() {
        return resultadoConversion;
    }

    @Override
    public String toString() {
        return "Monto convertido: %.2f %s -> %.2f %s".
                formatted(this.montoConvertido, this.divisaOrigen,
                this.resultadoConversion, this.divisaDestino)
        ;
    }
}
