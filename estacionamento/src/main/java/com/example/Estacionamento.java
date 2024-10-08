package com.example;

import java.time.LocalDateTime;

public class Estacionamento {

    private LocalDateTime entrada;
    private LocalDateTime saida;
    private boolean clienteVIP;
    private int ano;
    private int mes;
    private int dia;
    private int hora;
    private int minuto;

    public Estacionamento(int ano, int mes, int dia, int hora, int minuto, boolean clienteVIP) {
        this.ano = ano;
        this.mes = mes;
        this.dia = dia;
        this.hora = hora;
        this.minuto = minuto;
        this.clienteVIP = clienteVIP;
    }

    public void registraEntrada() {
        if (this.hora < 2 || this.hora >= 8) {
            this.entrada = LocalDateTime.of(this.ano, this.mes, this.dia, this.hora, this.minuto);
            System.out.println("Data de registro: " + entrada);

        } else {
            System.out.println("Fechado");
        }
    }

    public void registraSaida(int ano, int mes, int dia, int hora, int minuto) {
        if (this.hora < 2 || this.hora >= 8) {
            this.saida = LocalDateTime.of(ano, mes, dia, hora, minuto);
            System.out.println("Data de registro: " + this.saida);

        } else {
            System.out.println("Fechado");
        }
    }

    public void calcularDiferenca() {

        if (entrada.isAfter(saida)) {
            LocalDateTime temp = entrada;
            entrada = saida;
            saida = temp;
        }

        long dias = saida.toLocalDate().toEpochDay() - entrada.toLocalDate().toEpochDay();
        int horas = saida.getHour() - entrada.getHour();
        int minutos = saida.getMinute() - entrada.getMinute();

        if (minutos < 0) {
            minutos += 60;
            horas--;
        }

        if (horas < 0) {
            horas += 24;
            dias--;
        }

        System.out.println("Diferença: " + dias + " dias, " + horas + " horas e " + minutos + " minutos.");
    }

    public double calcularValor() {
        if (entrada == null || saida == null) {
            System.out.println("É necessário registrar a entrada e a saída.");
            return 0.0;
        }

        long minutosDiferenca = java.time.Duration.between(entrada, saida).toMinutes();
        double valor = 0.0;

        if (minutosDiferenca <= 15) {
            valor = 0.0; // 15 minutos de cortesia
        } else {
            minutosDiferenca -= 15; // Subtrai os 15 minutos de cortesia

            if (minutosDiferenca <= 60) {
                valor = 5.90; // Até 1 hora
            } else {
                // Calcular quantos dias de pernoite e horas restantes
                long dias = 0;
                if (saida.getHour() >= 8 && saida.toLocalDate().isAfter(entrada.toLocalDate())) {
                    // Contar o número de dias inteiros entre a entrada e a saída
                    dias = java.time.Duration
                            .between(entrada.toLocalDate().atStartOfDay(), saida.toLocalDate().atStartOfDay()).toDays();
                    valor = dias * 50.00; // Tarifa de pernoite
                } else {
                    // Se não for pernoite, calcular horas adicionais
                    long horas = minutosDiferenca / 60;
                    valor = 5.90 + (horas * 2.50); // Cálculo por hora adicional
                }
            }
        }

        // Aplicando desconto para cliente VIP
        if (clienteVIP) {
            valor *= 0.5; // 50% de desconto
        }

        System.out.printf("Valor a ser pago: R$%.2f%n", valor);
        return valor;
    }

}
