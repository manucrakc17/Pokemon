package com.mycompany.juego_de_batalla_pokemon;

import java.util.Arrays;
import java.util.List;
import javax.swing.JTextArea;

public class Blastoise extends Pokemon {

    private List<Ataque> ataquesDisponibles = Arrays.asList(
        new Ataque("Hidrobomba", 120, Tipo.AGUA, Cualidad.SPA),
        new Ataque("Terremoto", 100, Tipo.TIERRA, Cualidad.ATK),
        new Ataque("Cascada", 80, Tipo.AGUA, Cualidad.ATK),
        new Ataque("Mordisco", 60, Tipo.NORMAL, Cualidad.ATK) 
    );

    public Blastoise() {
        super(9, "Blastoise", Tipo.AGUA, 186, 148, 167, 150, 172, 143);
    }

    public List<Ataque> getAtaques() {
        return this.ataquesDisponibles;
    }

    @Override
    public void atacar(Pokemon oponente, Ataque ataqueElegido, JTextArea textArea) {

        double danioBase;

        if (ataqueElegido.getCualidad() == Cualidad.ATK) {
            danioBase = ((22.0 * ataqueElegido.getBase() * (this.atk / (double)oponente.getDef())) / 50.0) + 2.0;
        } else {
            danioBase = ((22.0 * ataqueElegido.getBase() * (this.spa / (double)oponente.getSpd())) / 50.0) + 2.0;
        }

        double stab = 1.0;
        if (ataqueElegido.getTipo() == this.tipo){
            stab = 1.5;
        }

        double mult = efectividad(ataqueElegido.getTipo(), oponente.getTipo());
        double damage_roll = 0.85 + Math.random() * 0.15;

        int danioFinal = (int)Math.round(danioBase * stab * mult * damage_roll);

        oponente.recibirDanio(danioFinal);

        String mensaje = nombre + " usa " + ataqueElegido.getNombre() + " → " + danioFinal + " (x" + mult + ")";
        if (textArea != null) {
            textArea.append(mensaje + "\n");
        } else {
            System.out.println(mensaje);
        }
    }
}
