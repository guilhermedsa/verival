package com.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class EstacionamentoTest {

    // Caso 1: 15 minutos de cortesia
    // "2024, 10, 5, 8, 15, false, 0.00",
    // Caso 2: Até 1 hora
    // "2024, 10, 5, 8, 0, false, 5.90",
    // Caso 3: 1 hora e 15 minutos
    // "2024, 10, 5, 9, 15, false, 5.90",
    // Caso 4: 2 horas
    // "2024, 10, 5, 10, 0, false, 8.40",
    // Caso 5: 3 horas
    // "2024, 10, 5, 11, 0, false, 10.90",
    // Caso 6: Cliente VIP, 1 hora
    // "2024, 10, 5, 8, 0, true, 2.95",
    // Caso 7: Cliente VIP, 2 horas
    // "2024, 10, 5, 10, 0, true, 4.20",
    // Caso 8: Período noturno (exemplo)
    // "2024, 10, 5, 23, 45, false, 5.90",
    // Caso 9: Período noturno, 1 pernoite
    // "2024, 10, 6, 8, 0, false, 50.00",
    // Caso 10: Cliente VIP, 1 pernoite
    // "2024, 10, 6, 8, 0, true, 25.00",
    // Caso 11: 3 dias de pernoite
    // "2024, 10, 5, 23, 45, false, 150.00",
    // Caso 12: Cliente VIP, 3 dias de pernoite
    // "2024, 10, 5, 23, 45, true, 75.00"

    private Estacionamento estacionamento;

    @BeforeEach
    void setUp() {
    }

    @Test
    void testCalcularValorCaso1() {
        estacionamento = new Estacionamento(2024, 10, 5, 8, 15, false);
        estacionamento.registraEntrada();
        estacionamento.registraSaida(2024, 10, 5, 8, 30);
        assertEquals(0.00, estacionamento.calcularValor());
    }

    @Test
    void testCalcularValorCaso2() {
        estacionamento = new Estacionamento(2024, 10, 5, 8, 0, false);
        estacionamento.registraEntrada();
        estacionamento.registraSaida(2024, 10, 5, 8, 59);
        assertEquals(5.90, estacionamento.calcularValor());
    }

    @Test
    void testCalcularValorCaso3() {
        estacionamento = new Estacionamento(2024, 10, 5, 9, 0, false);
        estacionamento.registraEntrada();
        estacionamento.registraSaida(2024, 10, 5, 10, 15);
        assertEquals(5.90, estacionamento.calcularValor());
    }

    @Test
    void testCalcularValorCaso4() {
        estacionamento = new Estacionamento(2024, 10, 5, 10, 0, false);
        estacionamento.registraEntrada();
        estacionamento.registraSaida(2024, 10, 5, 12, 0);
        assertEquals(8.40, estacionamento.calcularValor());
    }

    @Test
    void testCalcularValorCaso5() {
        estacionamento = new Estacionamento(2024, 10, 5, 11, 0, false);
        estacionamento.registraEntrada();
        estacionamento.registraSaida(2024, 10, 5, 14, 0);
        assertEquals(10.90, estacionamento.calcularValor());
    }

    @Test
    void testCalcularValorCaso6() {
        estacionamento = new Estacionamento(2024, 10, 5, 8, 0, true);
        estacionamento.registraEntrada();
        estacionamento.registraSaida(2024, 10, 5, 9, 0);
        assertEquals(2.95, estacionamento.calcularValor());
    }

    @Test
    void testCalcularValorCaso7() {
        estacionamento = new Estacionamento(2024, 10, 5, 10, 0, true);
        estacionamento.registraEntrada();
        estacionamento.registraSaida(2024, 10, 5, 12, 0);
        assertEquals(4.20, estacionamento.calcularValor());
    }

    @Test
    void testCalcularValorCaso8() {
        estacionamento = new Estacionamento(2024, 10, 5, 23, 45, false);
        estacionamento.registraEntrada();
        estacionamento.registraSaida(2024, 10, 6, 0, 45);
        assertEquals(5.90, estacionamento.calcularValor());
    }

    @Test
    void testCalcularValorCaso9() {
        estacionamento = new Estacionamento(2024, 10, 6, 8, 0, false);
        estacionamento.registraEntrada();
        estacionamento.registraSaida(2024, 10, 7, 8, 0);
        assertEquals(50.00, estacionamento.calcularValor());
    }

    @Test
    void testCalcularValorCaso10() {
        estacionamento = new Estacionamento(2024, 10, 6, 8, 0, true);
        estacionamento.registraEntrada();
        estacionamento.registraSaida(2024, 10, 7, 8, 0);
        assertEquals(25.00, estacionamento.calcularValor());
    }

    @Test
    void testCalcularValorCaso11() {
        estacionamento = new Estacionamento(2024, 10, 5, 23, 45, false);
        estacionamento.registraEntrada();
        estacionamento.registraSaida(2024, 10, 8, 23, 45);
        assertEquals(150.00, estacionamento.calcularValor());
    }

    @Test
    void testCalcularValorCaso12() {
        estacionamento = new Estacionamento(2024, 10, 5, 23, 45, true);
        estacionamento.registraEntrada();
        estacionamento.registraSaida(2024, 10, 8, 23, 45);
        assertEquals(75.00, estacionamento.calcularValor());
    }
}
