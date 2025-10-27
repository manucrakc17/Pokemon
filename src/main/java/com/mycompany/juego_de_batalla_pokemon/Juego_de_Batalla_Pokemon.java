package com.mycompany.juego_de_batalla_pokemon;

// Importamos la clase de la Interfaz Gráfica inicial
import interfaz.RankingGUI; 
import javax.swing.SwingUtilities;

/**
 * Clase principal que inicializa el juego.
 */
public class Juego_de_Batalla_Pokemon {

    public static void main(String[] args) {
        
        // --- 1. CONFIGURACIÓN INICIAL Y CARGA DE RANKING ---
        
        // El objeto Ranking se encarga de cargar los puntajes guardados
        Ranking rankingGlobal = new Ranking();
        
        // --- 2. LANZAR EL FLUJO DE LA INTERFAZ GRÁFICA ---
        
        // SwingUtilities.invokeLater asegura que la interfaz se ejecute de forma segura
        SwingUtilities.invokeLater(() -> {
            
            // La primera ventana que se muestra es el Ranking, que también pide el nombre
            // y tiene el botón para iniciar la Selección de Pokémon.
            new RankingGUI(rankingGlobal).setVisible(true);
        });
        
        /* * El flujo continúa internamente:
         * * 1. RankingGUI se muestra.
         * 2. El usuario introduce su nombre y pulsa "Jugar Batalla".
         * 3. RankingGUI crea los objetos Jugador y lanza la ventana SeleccionPokemones.
         * 4. SeleccionPokemones arma los equipos y lanza la ventana de Batalla.
         * 5. La Batalla, al terminar, llama al objeto rankingGlobal para registrar el puntaje.
         */
    }
}