/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.juego_de_batalla_pokemon;

enum Cualidad {
    ATK,
    SPA
}

public class Ataque {
    private String nombre;
    private int base;
    private Tipo tipo;
    private Cualidad cualidad;

    public Ataque(String nombre, int base, Tipo tipo, Cualidad cualidad) {
        this.nombre = nombre;
        this.base = base;
        this.tipo = tipo;
        this.cualidad = cualidad;
    }

    // Getters
    public String getNombre() {
        return nombre;
    }

    public int getBase() {
        return base;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public Cualidad getCualidad() {
        return cualidad;
    }
}

