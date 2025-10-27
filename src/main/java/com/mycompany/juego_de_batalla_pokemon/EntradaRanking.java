package com.mycompany.juego_de_batalla_pokemon;

import java.io.Serializable;

// Se necesita implementar Serializable para guardar el objeto en un archivo
public class EntradaRanking implements Serializable, Comparable<EntradaRanking> {
    private String nombreJugador;
    private int puntaje;

    // Constructor
    public EntradaRanking(String nombreJugador, int puntaje) {
        this.nombreJugador = nombreJugador;
        this.puntaje = puntaje;
    }

    // Getters
    public String getNombreJugador() {
        return nombreJugador;
    }

    public int getPuntaje() {
        return puntaje;
    }
    
    // Método para sumar puntos
    public void addPuntaje(int puntos) {
        this.puntaje += puntos;
    }

    // Método para ordenar: Ordena por puntaje de forma descendente (mayor a menor)
    @Override
    public int compareTo(EntradaRanking otro) {
        return Integer.compare(otro.puntaje, this.puntaje);
    }
}