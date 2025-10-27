package com.mycompany.juego_de_batalla_pokemon;

import java.util.List;
import javax.swing.JTextArea;

enum Tipo { 
    FUEGO, AGUA, PLANTA, ELECTRICO, TIERRA, NORMAL,
    FANTASMA, LUCHA, ROCA, VENENO
}

public abstract class Pokemon {
    protected int NroPokedex;
    protected String nombre;
    protected Tipo tipo;
    protected int hp;
    protected int maxHp;
    protected int atk;
    protected int def;
    protected int spa;
    protected int spd;
    protected int spe;

    public Pokemon(int NroPokedex, String nombre, Tipo tipo, int hp, int atk, int def, int spa, int spd, int spe) {
        this.NroPokedex = NroPokedex;
        this.nombre = nombre;
        this.tipo = tipo;
        this.hp = hp;
        this.maxHp = hp;
        this.atk = atk;
        this.def = def;
        this.spa = spa;
        this.spd = spd;
        this.spe = spe;
    }

    protected double efectividad(Tipo ataca, Tipo defiende) {
        // 2.0 Súper Efectivo
        if (ataca == Tipo.AGUA && (defiende == Tipo.TIERRA || defiende == Tipo.FUEGO || defiende == Tipo.ROCA)) return 2.0;
        if (ataca == Tipo.ELECTRICO && defiende == Tipo.AGUA) return 2.0;
        if (ataca == Tipo.FANTASMA && defiende == Tipo.FANTASMA) return 2.0;
        if (ataca == Tipo.FUEGO && defiende == Tipo.PLANTA) return 2.0;
        if (ataca == Tipo.PLANTA && (defiende == Tipo.AGUA || defiende == Tipo.ROCA || defiende == Tipo.TIERRA)) return 2.0;
        if (ataca == Tipo.LUCHA && (defiende == Tipo.NORMAL || defiende == Tipo.ROCA)) return 2.0;
        if (ataca == Tipo.ROCA && defiende == Tipo.FUEGO) return 2.0;
        if (ataca == Tipo.TIERRA && (defiende == Tipo.ELECTRICO || defiende == Tipo.FUEGO)) return 2.0;
        if (ataca == Tipo.VENENO && defiende == Tipo.PLANTA) return 2.0;

        // 0.5 No Muy Efectivo
        if (ataca == Tipo.AGUA && (defiende == Tipo.AGUA || defiende == Tipo.PLANTA)) return 0.5;
        if (ataca == Tipo.ELECTRICO && (defiende == Tipo.ELECTRICO || defiende == Tipo.PLANTA)) return 0.5;
        if (ataca == Tipo.FUEGO && (defiende == Tipo.AGUA || defiende == Tipo.FUEGO || defiende == Tipo.ROCA)) return 0.5;
        if (ataca == Tipo.NORMAL && defiende == Tipo.ROCA) return 0.5;
        if (ataca == Tipo.PLANTA && (defiende == Tipo.FUEGO || defiende == Tipo.PLANTA)) return 0.5;
        if (ataca == Tipo.ROCA && (defiende == Tipo.LUCHA || defiende == Tipo.TIERRA)) return 0.5;
        if (ataca == Tipo.TIERRA && defiende == Tipo.PLANTA) return 0.5;
        if (ataca == Tipo.VENENO && (defiende == Tipo.FANTASMA || defiende == Tipo.ROCA || defiende == Tipo.TIERRA || defiende == Tipo.VENENO)) return 0.5;

        // 0.0 Inmune
        if (ataca == Tipo.ELECTRICO && defiende == Tipo.TIERRA) return 0.0;
        if (ataca == Tipo.FANTASMA && defiende == Tipo.NORMAL) return 0.0;
        if (ataca == Tipo.LUCHA && defiende == Tipo.FANTASMA) return 0.0; 
        if (ataca == Tipo.NORMAL && defiende == Tipo.FANTASMA) return 0.0; 

        // 1.0 Normal
        return 1.0;
    }

    // GETTERS
    public int getNroPokedex() { return NroPokedex; }
    public String getNombre() { return nombre; }
    public Tipo getTipo() { return tipo; }
    public int getHp() { return hp; }
    public int getMaxHp() { return maxHp; }
    public int getAtk() { return atk; }
    public int getDef() { return def; }
    public int getSpa() { return spa; }
    public int getSpd() { return spd; }
    public int getSpe() { return spe; }

    // Recibir daño
    public void recibirDanio(int d) {
        hp = Math.max(0, hp - d);
    }

    // Atacar con JTextArea opcional
    public void atacar(Pokemon oponente, Ataque ataqueElegido, JTextArea textArea) {
        double danioBase;

        if (ataqueElegido.getCualidad() == Cualidad.ATK) {
            danioBase = ((22.0 * ataqueElegido.getBase() * (this.atk / (double)oponente.getDef())) / 50.0) + 2.0;
        } else {
            danioBase = ((22.0 * ataqueElegido.getBase() * (this.spa / (double)oponente.getSpd())) / 50.0) + 2.0;
        }

        double stab = (ataqueElegido.getTipo() == this.tipo) ? 1.5 : 1.0;
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

    // Método abstracto solo para obtener ataques
    public abstract List<Ataque> getAtaques();
}