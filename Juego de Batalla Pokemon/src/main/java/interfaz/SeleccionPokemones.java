package interfaz;

import com.mycompany.juego_de_batalla_pokemon.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

public class SeleccionPokemones extends JFrame {

    private Jugador jugadorActual;
    private final Jugador jugadorPrincipal;
    private final Jugador jugadorOponente;
    private boolean seleccionJugadorPrincipal = true; // indica si estamos eligiendo al jugador principal
    private final List<String> seleccionados = new ArrayList<>();
    private final List<JButton> botonesPokemon = new ArrayList<>();
    private JButton btnConfirmar;
    private JLabel infoLabel;

    private final String[] nombresPokemones = {
            "Pikachu", "Charizard", "Blastoise", "Venusaur",
            "Golem", "Gengar", "Nidoking", "Dugtrio", "Machamp"
    };

    public SeleccionPokemones(Jugador jugadorPrincipal, Jugador jugadorOponente) {
        this.jugadorPrincipal = jugadorPrincipal;
        this.jugadorOponente = jugadorOponente;
        this.jugadorActual = jugadorPrincipal;
        initComponents();
    }

    private void initComponents() {
        setTitle("Selecciona 3 Pokémon");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 500);
        setLayout(new BorderLayout());

        infoLabel = new JLabel("Selecciona 3 Pokémon para " + jugadorActual.getNombre(), SwingConstants.CENTER);
        infoLabel.setFont(new Font("Arial", Font.BOLD, 16));
        add(infoLabel, BorderLayout.NORTH);

        JPanel panelBotones = new JPanel(new GridLayout(3, 3, 10, 10));
        for (String nombre : nombresPokemones) {
            JButton btn = new JButton(nombre);
            btn.setFont(new Font("Arial", Font.PLAIN, 14));
            btn.addActionListener(this::seleccionarPokemon);
            botonesPokemon.add(btn);
            panelBotones.add(btn);
        }
        add(panelBotones, BorderLayout.CENTER);

        btnConfirmar = new JButton("Confirmar selección");
        btnConfirmar.setFont(new Font("Arial", Font.BOLD, 14));
        btnConfirmar.addActionListener(e -> confirmarSeleccion());
        add(btnConfirmar, BorderLayout.SOUTH);
    }

    private void seleccionarPokemon(ActionEvent e) {
        JButton btn = (JButton) e.getSource();
        String nombre = btn.getText();

        if (seleccionados.contains(nombre)) {
            seleccionados.remove(nombre);
            btn.setBackground(null);
        } else {
            if (seleccionados.size() >= 3) {
                JOptionPane.showMessageDialog(this, "Solo puedes elegir 3 Pokémon.");
            } else {
                seleccionados.add(nombre);
                btn.setBackground(Color.GREEN);
            }
        }
    }

    private void confirmarSeleccion() {
        if (seleccionados.size() != 3) {
            JOptionPane.showMessageDialog(this, "Debes seleccionar exactamente 3 Pokémon.");
            return;
        }

        // Agregar Pokémon al jugador actual
        for (String nombre : seleccionados) {
            switch (nombre) {
                case "Pikachu" -> jugadorActual.agregarPokemon(new Pikachu());
                case "Charizard" -> jugadorActual.agregarPokemon(new Charizard());
                case "Blastoise" -> jugadorActual.agregarPokemon(new Blastoise());
                case "Venusaur" -> jugadorActual.agregarPokemon(new Venusaur());
                case "Golem" -> jugadorActual.agregarPokemon(new Golem());
                case "Gengar" -> jugadorActual.agregarPokemon(new Gengar());
                case "Nidoking" -> jugadorActual.agregarPokemon(new Nidoking());
                case "Dugtrio" -> jugadorActual.agregarPokemon(new Dugtrio());
                case "Machamp" -> jugadorActual.agregarPokemon(new Machamp());
            }
        }

        // Limpiar selección y colores
        seleccionados.clear();
        botonesPokemon.forEach(btn -> btn.setBackground(null));

        if (seleccionJugadorPrincipal) {
            // Cambiar a selección del oponente
            jugadorActual = jugadorOponente;
            seleccionJugadorPrincipal = false;
            infoLabel.setText("Selecciona 3 Pokémon para " + jugadorActual.getNombre());
        } else {
            // Ambos jugadores listos, abrir batalla
            new IntefazFinal(new Batalla(jugadorPrincipal, jugadorOponente)).setVisible(true);
            dispose();
        }
    }

    public static void main(String[] args) {
        Jugador jugadorPrincipal = new Jugador("ASH");
        Jugador jugadorOponente = new Jugador("GARY");
        SwingUtilities.invokeLater(() -> new SeleccionPokemones(jugadorPrincipal, jugadorOponente).setVisible(true));
    }
}
