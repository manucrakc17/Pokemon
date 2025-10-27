package com.mycompany.juego_de_batalla_pokemon;

import javax.swing.JTextArea;

/**
 * Clase controladora que gestiona la lógica de la batalla por turnos.
 */
public class Batalla {

    private Jugador jugador1;
    private Jugador jugador2;
    private Jugador turnoActual;

    public Batalla(Jugador j1, Jugador j2) {
        this.jugador1 = j1;
        this.jugador2 = j2;
        this.turnoActual = j1; // Comienza jugador1 por defecto
        System.out.println("¡Comienza la batalla entre " + j1.getNombre() + " y " + j2.getNombre() + "!");
        System.out.println("Ahora es el turno de " + turnoActual.getNombre());
    }

    /**
     * Ejecuta un ataque del Pokémon activo del jugador del turno actual.
     * @param ataqueElegido Ataque seleccionado
     * @param textArea JTextArea opcional para mostrar logs
     * @return Log del resultado del ataque
     */
    public String ejecutarAtaque(Ataque ataqueElegido, JTextArea textArea) {
        Pokemon atacante = turnoActual.getPokemonActivo();
        Jugador oponente = (turnoActual == jugador1) ? jugador2 : jugador1;
        Pokemon defensor = oponente.getPokemonActivo();

        // Verifica si el Pokémon atacante está debilitado
        if (atacante.getHp() <= 0) {
            return turnoActual.getNombre() + " debe cambiar de Pokémon antes de atacar.";
        }

        // Ejecuta el ataque
        atacante.atacar(defensor, ataqueElegido, textArea);

        String log = atacante.getNombre() + " ataca a " + defensor.getNombre() + ".";

        // Verifica si el defensor fue derrotado
        if (defensor.getHp() <= 0) {
            log += "\n¡" + defensor.getNombre() + " fue derrotado!";
            if (oponente.haPerdido()) {
                log += "\n¡El ganador es: " + turnoActual.getNombre() + "!";
                Ranking rankingManager = new Ranking(); 
                // Asignar puntos al jugador ganador 
                rankingManager.actualizarPuntaje(turnoActual.getNombre(), 10);
                // Descontar puntos al perdedor 
                rankingManager.actualizarPuntaje(oponente.getNombre(), -2);
                
                return log;
            } else {
                log += "\n" + oponente.getNombre() + " debe elegir un nuevo Pokémon antes de continuar.";
                // No cambiar turno hasta que el oponente elija
                return log;
            }
        }

        // Cambia el turno
        cambiarTurno(textArea);

        return log;
    }

    /**
     * Ejecuta el intercambio de Pokémon del jugador del turno actual.
     * @param nuevoPokemon Pokémon seleccionado para intercambiar
     * @param textArea JTextArea opcional
     * @return Log del resultado del intercambio
     */
    public String ejecutarIntercambio(Pokemon nuevoPokemon, JTextArea textArea) {
        boolean exito = turnoActual.intercambiarPokemon(nuevoPokemon);

        if (exito) {
            String log = turnoActual.getNombre() + " intercambia a " + nuevoPokemon.getNombre() + ".";
            cambiarTurno(textArea);
            return log;
        } else {
            return "Intercambio fallido: el Pokémon no está en el equipo o ya está debilitado.";
        }
    }

    /**
     * Cambia el turno al otro jugador y muestra mensaje.
     */
    private void cambiarTurno(JTextArea textArea) {
        turnoActual = (turnoActual == jugador1) ? jugador2 : jugador1;
        String mensaje = "Ahora es el turno de " + turnoActual.getNombre();
        if (textArea != null) {
            textArea.append(mensaje + "\n");
        } else {
            System.out.println(mensaje);
        }
    }

    // GETTERS
    public Jugador getTurnoActual() { return turnoActual; }
    public Jugador getJugadorOponente() { return (turnoActual == jugador1) ? jugador2 : jugador1; }
}
