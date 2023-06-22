package com.example.notapp.Strategy;

public class Contexto {
    private Estrategia estrategia;

    public Contexto(Estrategia estrategia) {
        this.estrategia = estrategia;
    }

    public void ejecutarEstrategia() {
        estrategia.ejecutar();
    }
}
