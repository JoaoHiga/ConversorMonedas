package io.takomuraragi.main;

import io.takomuraragi.exepciones.ConversionNoValidaException;
import io.takomuraragi.exepciones.OpcionInvalidaException;
import io.takomuraragi.models.*;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        ConsultaParaDivisasAPI consulta = new ConsultaParaDivisasAPI();
        boolean enEjecucion = true;
        boolean convirtiendo = false;
        HistorialDeConversion historialConversiones =  new HistorialDeConversion();
        ListaDeDivisas listaDeDivisas = new ListaDeDivisas();
        GeneradorDeArchivos generadorDeArchivos = new GeneradorDeArchivos();

        System.out.println("""
                ************************************************************
                             Bienvenido al conversor de divisas
                ************************************************************
                """);

        while (enEjecucion){
            System.out.println("""
                    ************************************************************
                    Ingrese una opción:
                    
                        1. Convertir divisa.
                        2. Ver historial de conversiones.
                        3. Salir.
                    ************************************************************1""");
            try {
                int eleccionMenuPrincipal = input.nextInt();

                switch (eleccionMenuPrincipal){
                    case 1:
                        convirtiendo = true;
                        input.nextLine();
                        break;
                    case 2:
                        historialConversiones.imprimirHistorialDeConversiones();
                        break;
                    case 3:
                        enEjecucion = false;
                        break;
                    default:
                        System.out.println("Ingrese una opción válida.");
                        break;
                }
            } catch (InputMismatchException e) {
                input.nextLine();
                System.out.println("Ingrese una opción válida.");
            } catch (RuntimeException e) {
                System.out.println(e.getMessage());
            }

            boolean eligiendoDivisaOrigen = true;
            boolean eligiendoDivisaDestino = false;
            boolean eligiendoMonto = false;
            String divisaOrigen = "";
            String divisaDestino = "";
            double monto = 0;

            while (convirtiendo){

                try {
                    while (eligiendoDivisaOrigen) {
                        System.out.printf("""
                        ************************************************************
                        Seleccione la divisa a CONVERTIR:
                        %s
                        ************************************************************%n"""
                                , listaDeDivisas.imprimirListaDeDivisas());

                        String  eleccionDivisOrigen = input.nextLine();
                        divisaOrigen = listaDeDivisas.devolverDivisa(eleccionDivisOrigen);
                        eligiendoDivisaOrigen = false;
                        eligiendoDivisaDestino = true;
                    }

                    while (eligiendoDivisaDestino) {
                        System.out.printf("""
                        ************************************************************
                        Seleccione la divisa DESEADA:
                        %s
                        ************************************************************%n"""
                                , listaDeDivisas.imprimirListaDeDivisas());

                        String eleccionDivisaDestino = input.nextLine();
                        divisaDestino = listaDeDivisas.devolverDivisa(eleccionDivisaDestino);
                        eligiendoDivisaDestino = false;
                        eligiendoMonto = true;
                    }

                    while (eligiendoMonto) {
                        System.out.println("""
                        ************************************************************
                        Ingrese el monto a convertir:""");

                        monto = input.nextDouble();
                        input.nextLine();
                        eligiendoMonto = false;
                    }

                    ConversionAPI conversionAPI = consulta.consultarConversion(divisaOrigen, divisaDestino, monto);
                    Conversion conversion = new Conversion(conversionAPI, monto);

                    System.out.println("************************************************************");
                    System.out.println(conversion);
                    historialConversiones.agregarConversion(conversion);
                    convirtiendo = false;
                } catch (NumberFormatException e) {
                    System.out.println("Ingrese una opción numérica válida de la lista mostrada.");
                } catch (OpcionInvalidaException e) {
                    System.out.println(e.getMessage());
                } catch (ConversionNoValidaException e) {
                    System.out.println(e.getMessage());
                } catch (InputMismatchException e) {
                    input.nextLine();
                    System.out.println("Ingrese un monto válido.");
                }
            }
        }

        try {
            String nombreDeArchivo = "historial.json";
            System.out.printf("""
                    Historial de conversiones guardado en %s
                    Hasta luego!%n""", nombreDeArchivo);
            generadorDeArchivos.generarJsonDeHistorial(historialConversiones, nombreDeArchivo);
        } catch (IOException e){
            System.out.println(e.getMessage());
        }
    }
}
