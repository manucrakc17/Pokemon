package com.mycompany.juego_de_batalla_pokemon;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase que representa a un jugador y su equipo de Pokémon.
 */
public class Jugador {
    private String nombre;
    private int puntaje;
    private List<Pokemon> equipo;
    private Pokemon pokemonActivo;

    // Constructor
    public Jugador(String nombre) {
        this.nombre = nombre;
        this.puntaje = 0;
        this.equipo = new ArrayList<>();
        this.pokemonActivo = null; 
    }

    // Método para añadir Pokémon al equipo (se llama al inicio del juego)
    public void agregarPokemon(Pokemon p) {
        if (equipo.size() < 3) {
            equipo.add(p);
            // El primer Pokémon añadido es el activo por defecto
            if (equipo.size() == 1) {
                pokemonActivo = p;
            }
        } else {
            System.out.println(nombre + " ya tiene 3 Pokémon en su equipo.");
        }
    }

    // Método para cambiar el Pokémon activo (Usado por BOTONINTERCAMBIAR)
    public boolean intercambiarPokemon(Pokemon nuevoActivo) {
        if (equipo.contains(nuevoActivo) && nuevoActivo.getHp() > 0) {
            this.pokemonActivo = nuevoActivo;
            System.out.println(nombre + " ha sacado a " + nuevoActivo.getNombre());
            // Lógica adicional del intercambio, si es necesaria (interfaz)
            return true;
        }
        return false;
    }
    
    // Verifica si todos los Pokémon del equipo han sido derrotados
    public boolean haPerdido() {
        return equipo.stream().allMatch(p -> p.getHp() <= 0);
    }
    
    // Getters y Setters
    public String getNombre() {
        return nombre;
    }

    public int getPuntaje() {
        return puntaje;
    }

    public void setPuntaje(int puntaje) {
        this.puntaje = puntaje;
    }

    public Pokemon getPokemonActivo() {
        return pokemonActivo;
    }

    public List<Pokemon> getEquipo() {
        return equipo;
    }
}