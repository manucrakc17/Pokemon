package com.mycompany.juego_de_batalla_pokemon;

import java.util.Arrays;
import java.util.List;
import javax.swing.JTextArea;

public class Pikachu extends Pokemon {

    private List<Ataque> ataquesDisponibles = Arrays.asList(
        new Ataque("Impactrueno", 40, Tipo.ELECTRICO, Cualidad.SPA),
        new Ataque("Placaje", 35, Tipo.NORMAL, Cualidad.ATK),
        new Ataque("Puño Trueno", 75, Tipo.ELECTRICO, Cualidad.SPA),
        new Ataque("Golpe roca", 40, Tipo.LUCHA, Cualidad.ATK)
    );

    public Pikachu() { 
        super(25, "Pikachu", Tipo.ELECTRICO, 142, 117, 90, 112, 101, 156); 
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
