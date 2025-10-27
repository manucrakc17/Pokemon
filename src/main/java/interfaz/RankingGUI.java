package interfaz;

import com.mycompany.juego_de_batalla_pokemon.Jugador;
import com.mycompany.juego_de_batalla_pokemon.EntradaRanking;
import com.mycompany.juego_de_batalla_pokemon.Ranking;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.util.List;
import javax.swing.*;
import java.awt.FlowLayout; 

public class RankingGUI extends JFrame {
    
    private final Ranking rankingManager;
    private JTextField campoNombreJugador; // <-- Nuevo: Campo de texto para el nombre

    public RankingGUI(Ranking rankingManager) {
        this.rankingManager = rankingManager;
        initComponents();
        cargarRankingATabla();
    }

    private void initComponents() {
        setTitle("Ranking de Batallas Pokémon");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 450);
        setLocationRelativeTo(null); // Centrar ventana
        setLayout(new BorderLayout(10, 10));

        // Título
        JLabel titleLabel = new JLabel("TOP 10 CAMPEONES", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(new Color(50, 50, 150));
        add(titleLabel, BorderLayout.NORTH);

        // Tabla (JTable)
        String[] columnNames = {"Posición", "Jugador", "Puntaje"};
        JTable tablaRanking = new JTable(new Object[0][0], columnNames);
        tablaRanking.setEnabled(false);
        JScrollPane scrollPane = new JScrollPane(tablaRanking);
        add(scrollPane, BorderLayout.CENTER);

        // ***************************************************************
        // NUEVA SECCIÓN SUR: Entrada de Nombre y Botón de Inicio
        // ***************************************************************
        JPanel southPanel = new JPanel();
        southPanel.setLayout(new BoxLayout(southPanel, BoxLayout.Y_AXIS)); // Layout vertical
        
        // 1. Panel de entrada de nombre
        JPanel panelNombre = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel labelNombre = new JLabel("Tu Nombre:");
        labelNombre.setFont(new Font("Arial", Font.PLAIN, 14));
        
        campoNombreJugador = new JTextField(15); // Inicializa el campo de texto
        
        panelNombre.add(labelNombre);
        panelNombre.add(campoNombreJugador);
        
        // 2. Botón Jugar
        JButton btnJugar = new JButton("¡Jugar Batalla!");
        btnJugar.setFont(new Font("Arial", Font.BOLD, 18));
        
        // Asignamos el Listener al método iniciarJuego, que ahora manejará el nombre
        btnJugar.addActionListener((ActionEvent e) -> iniciarJuego());
        
        // Agregar componentes al panel sur
        southPanel.add(panelNombre);
        southPanel.add(btnJugar);
        
        add(southPanel, BorderLayout.SOUTH);
    }
    
    // Método para llenar la tabla con los datos del Ranking... (se mantiene igual)
    private void cargarRankingATabla() {
        List<EntradaRanking> ranking = rankingManager.getListaRanking();
        
        // Limitar a los 10 primeros
        int size = Math.min(10, ranking.size());
        Object[][] data = new Object[size][3];
        
        for (int i = 0; i < size; i++) {
            EntradaRanking entry = ranking.get(i);
            data[i][0] = i + 1; // Posición
            data[i][1] = entry.getNombreJugador();
            data[i][2] = entry.getPuntaje();
        }
        
        // Se actualiza el modelo de la tabla
        JTable tabla = (JTable) ((JScrollPane)getContentPane().getComponent(1)).getViewport().getView();
        tabla.setModel(new javax.swing.table.DefaultTableModel(data, new String [] {"Posición", "Jugador", "Puntaje"}));
    }

    // ***************************************************************
    // MÉTODO DE INICIO MODIFICADO PARA USAR LOS NOMBRE CAPTURADO
    // ***************************************************************

    private void iniciarJuego() {
        String nombreJugador1 = campoNombreJugador.getText().trim();

        if (nombreJugador1.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, introduce tu nombre.", "Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        this.dispose(); 

        // Crear el objeto Jugador principal con el nombre
        Jugador j1 = new Jugador(nombreJugador1);



        String nombreJugador2 = JOptionPane.showInputDialog(this, "Introduce el nombre del Jugador 2", JOptionPane.QUESTION_MESSAGE);

        if (nombreJugador2 == null || nombreJugador2.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, introduce tu nombre.", "Error", JOptionPane.WARNING_MESSAGE);
            return;
        }
    
    Jugador j2 = new Jugador(nombreJugador2.trim());
    
    // Abrir la ventana de Selección de Pokémon con ambos jugadores
    new SeleccionPokemones(j1, j2).setVisible(true);
}

    // El método main se mantiene igual
    public static void main(String[] args) {
        Ranking r = new Ranking();
        SwingUtilities.invokeLater(() -> new RankingGUI(r).setVisible(true));
    }
}