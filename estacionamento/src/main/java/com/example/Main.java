package com.example;

class Main {
    public static void main(String[] args) {
        Estacionamento estacionamento = new Estacionamento(2024, 10, 5, 23, 45, false);
        estacionamento.registraEntrada();
        estacionamento.registraSaida(2024, 10, 6, 0, 45);
        estacionamento.calcularDiferenca();
        estacionamento.calcularValor();

    }
}