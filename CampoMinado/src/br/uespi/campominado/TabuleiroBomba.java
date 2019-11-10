/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uespi.campominado;

import java.text.DecimalFormat;
import java.util.Random;

/**
 *
 * @author Snz
 */
public class TabuleiroBomba extends Tabuleiro {

    protected int x, y;
    protected int quantidadeBombas; //Default 16%

    public TabuleiroBomba(int x, int y) {
        super(x, y);
        this.y = y + 1;
        this.x = x + 1;
        //System.out.println(this.quantidadeBombas);
        this.tabuleiro = new String[this.y][this.x];

    }

    //Métodos
    public int getQuantidadeBombas() {
        return quantidadeBombas;
    }

    public void setQuantidadeBombas(int quantidadeBombas) {
        this.quantidadeBombas = quantidadeBombas;
    }
}
