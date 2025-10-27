package com.mycompany.juego_de_batalla_pokemon;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Ranking {
    private static final String ARCHIVO_RANKING = "ranking.dat";
    private List<EntradaRanking> listaRanking; // Usando EntradaRanking

    public Ranking() {
        this.listaRanking = cargarRanking();
    }

    // Carga la lista desde el archivo (si existe)
    private List<EntradaRanking> cargarRanking() { // Usando EntradaRanking
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ARCHIVO_RANKING))) {
            // Se realiza un 'casting' para recuperar la lista
            System.out.println("Ranking cargado exitosamente.");
            return (List<EntradaRanking>) ois.readObject(); // Usando EntradaRanking
        } catch (FileNotFoundException e) {
            System.out.println("Archivo de ranking no encontrado. Creando nuevo ranking.");
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error al cargar el ranking: " + e.getMessage());
            // Si hay un error, devolvemos una lista vacía para empezar de nuevo
        }
        return new ArrayList<>();
    }

    // Guarda la lista en el archivo
    public void guardarRanking() {
        // Corrección de sintaxis: se eliminó el 'new' duplicado.
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ARCHIVO_RANKING))) { 
            oos.writeObject(listaRanking);
            System.out.println("Ranking guardado exitosamente.");
        } catch (IOException e) {
            System.err.println("Error al guardar el ranking: " + e.getMessage());
        }
    }

    // Lógica para actualizar o añadir el puntaje de un jugador
    public void actualizarPuntaje(String nombreJugador, int puntosObtenidos) {
        EntradaRanking entrada = listaRanking.stream() // Usando EntradaRanking
            .filter(e -> e.getNombreJugador().equalsIgnoreCase(nombreJugador))
            .findFirst()
            .orElse(null);

        if (entrada != null) {
            entrada.addPuntaje(puntosObtenidos);
        } else {
            // Es un jugador nuevo, se añade a la lista
            listaRanking.add(new EntradaRanking(nombreJugador, puntosObtenidos)); // Usando EntradaRanking
        }
        
        // Reordenar la lista después de cada actualización
        ordenarRanking();
        // Guardar automáticamente
        guardarRanking(); 
    }
    
    // Ordena la lista usando el compareTo de EntradaRanking
    public void ordenarRanking() {
        Collections.sort(listaRanking);
    }
    
    // Getter para la interfaz
    public List<EntradaRanking> getListaRanking() { // Usando EntradaRanking
        return listaRanking;
    }
}