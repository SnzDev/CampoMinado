/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uespi.campominado;

/**
 *
 * @author Snz
 */
public class TabuleiroBomba extends Tabuleiro {

    private int quantidadeBombas; //Default 16%
    private Integer[] posicaoBombaX;
    private Integer[] posicaoBombaY;
    private int x, y;

    public TabuleiroBomba(int x, int y) {

        this.y = y + 1;
        this.x = x + 1;
        this.quantidadeBombas = (int) ((y * x) * (0.16));
        //System.out.println(this.quantidadeBombas);
        this.tabuleiro = new String[this.y][this.x];
        this.posicaoBombaX = new Integer[this.quantidadeBombas];
        this.posicaoBombaY = new Integer[this.quantidadeBombas];

    }
}
