/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package interfaz;

import javax.swing.ImageIcon;

/**
 *
 * @author Alonso
 */
public class intefaz_inicial extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(intefaz_inicial.class.getName());

    /**
     * Creates new form intefaz_inicial
     */
    public intefaz_inicial() {
        initComponents();
        cargarPokemon("pikachu");
        cargarPokemonContrincante("charizard");
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        labelNombreJugador = new javax.swing.JLabel();
        labelNombreContrincante = new javax.swing.JLabel();
        labelPokemonJugador = new javax.swing.JLabel();
        labelPokemonContrincante = new javax.swing.JLabel();
        BOTONINTERCAMBIAR = new javax.swing.JButton();
        ATACAR = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        labelNombreJugador.setFont(new java.awt.Font("Minecraft", 2, 24)); // NOI18N
        labelNombreJugador.setText("jLabel2");
        getContentPane().add(labelNombreJugador, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 440, -1, -1));

        labelNombreContrincante.setFont(new java.awt.Font("Minecraft", 2, 24)); // NOI18N
        labelNombreContrincante.setText("jLabel2");
        getContentPane().add(labelNombreContrincante, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 70, -1, -1));
        getContentPane().add(labelPokemonJugador, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 260, 300, 260));
        getContentPane().add(labelPokemonContrincante, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 110, 210, 180));

        BOTONINTERCAMBIAR.setIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaz/imagenes/INTERCAMBIAR.png"))); // NOI18N
        BOTONINTERCAMBIAR.setText("INTERCAMBIAR");
        BOTONINTERCAMBIAR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BOTONINTERCAMBIARActionPerformed(evt);
            }
        });
        getContentPane().add(BOTONINTERCAMBIAR, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 600, 260, -1));

        ATACAR.setIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaz/imagenes/ATCAR.png"))); // NOI18N
        ATACAR.setActionCommand("Atacar");
        ATACAR.setLabel("ATACAR");
        ATACAR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ATACARActionPerformed(evt);
            }
        });
        getContentPane().add(ATACAR, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 600, 280, -1));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaz/imagenes/fondo2.png"))); // NOI18N
        jLabel1.setText("jLabel1");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BOTONINTERCAMBIARActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BOTONINTERCAMBIARActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BOTONINTERCAMBIARActionPerformed
    private ImageIcon escalarImagen(String ruta, javax.swing.JLabel label) {
    java.net.URL url = getClass().getResource(ruta);
    if (url == null) {
        System.err.println("Imagen no encontrada: " + ruta);
        return null;
    }
    ImageIcon icon = new ImageIcon(url);
    java.awt.Image img = icon.getImage().getScaledInstance(
        label.getWidth(), label.getHeight(), java.awt.Image.SCALE_SMOOTH);
    return new ImageIcon(img);
}

    private void ATACARActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ATACARActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ATACARActionPerformed
    public void cargarPokemonContrincante(String nombrePokemon) {
        String nombreFormateado = nombrePokemon.substring(0, 1).toUpperCase() + nombrePokemon.substring(1);
        labelNombreContrincante.setText(nombreFormateado);
        String rutaImagen = "";
    
        String basePath = "/interfaz/pokemones/";

        switch (nombrePokemon.toLowerCase()) {
            case "pikachu" -> rutaImagen = basePath + "pikachu.gif";
            case "charizard" -> rutaImagen = basePath + "charizard.gif";
            case "blastoise" -> rutaImagen = basePath + "blastoise.gif";
            case "venusaur" -> rutaImagen = basePath + "venusaur.gif";
            case "gengar" -> rutaImagen = basePath + "gengar.gif";
            case "golem" -> rutaImagen = basePath + "golem.gif";
            case "machamp" -> rutaImagen = basePath + "machamp.gif";
            case "nidoking" -> rutaImagen = basePath + "nidoking.gif";
            case "dugtrio" -> rutaImagen = basePath + "dugtrio.gif";

            default -> {
                System.err.println("Contrincante no encontrado: " + nombrePokemon);
                labelPokemonContrincante.setIcon(null); // Limpia la imagen del contrincante
                return;
            }
        }

        java.net.URL url = this.getClass().getResource(rutaImagen);

        if (url != null) {
            labelPokemonContrincante.setIcon(new ImageIcon(url));
        } else {
            System.err.println("IMAGEN NO ENCONTRADA en la ruta: " + rutaImagen);
            labelPokemonContrincante.setIcon(escalarImagen(rutaImagen, labelPokemonContrincante));
        }
    }
    public void cargarPokemon(String nombrePokemon) {
        String nombreFormateado = nombrePokemon.substring(0, 1).toUpperCase() + nombrePokemon.substring(1);
    // Ponemos el nombre en el nuevo JLabel
        labelNombreJugador.setText(nombreFormateado);
        String rutaImagen = "";
    
    // ✅ OJO: La ruta correcta según tu imagen es "/interfaz/pokemones/"
    String basePath = "/interfaz/pokemones/";

    switch (nombrePokemon.toLowerCase()) {
            case "pikachu" -> rutaImagen = basePath + "pikachu.gif";
            case "charizard" -> rutaImagen = basePath + "charizard.gif";
            case "blastoise" -> rutaImagen = basePath + "blastoise.gif";
            case "venusaur" -> rutaImagen = basePath + "venusaur.gif";
            case "gengar" -> rutaImagen = basePath + "gengar.gif";
            case "golem" -> rutaImagen = basePath + "golem.gif";
            case "machamp" -> rutaImagen = basePath + "machamp.gif";
            case "nidoking" -> rutaImagen = basePath + "nidoking.gif";
            case "dugtrio" -> rutaImagen = basePath + "dugtrio.gif";
            default -> {
  
            System.err.println("Pokémon no encontrado en el switch: " + nombrePokemon);
            labelPokemonJugador.setIcon(escalarImagen(rutaImagen, labelPokemonJugador)); 
            return;
            }
        }

        java.net.URL url = this.getClass().getResource(rutaImagen);

        if (url != null) {
            labelPokemonJugador.setIcon(new ImageIcon(url));
        } else {
            System.err.println("IMAGEN NO ENCONTRADA en la ruta: " + rutaImagen);
            labelPokemonJugador.setIcon(null);
        }
    }
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new intefaz_inicial().setVisible(true));
    }
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ATACAR;
    private javax.swing.JButton BOTONINTERCAMBIAR;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel labelNombreContrincante;
    private javax.swing.JLabel labelNombreJugador;
    private javax.swing.JLabel labelPokemonContrincante;
    private javax.swing.JLabel labelPokemonJugador;
    // End of variables declaration//GEN-END:variables
}
