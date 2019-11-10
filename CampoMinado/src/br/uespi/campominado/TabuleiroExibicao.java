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
public class TabuleiroExibicao extends Tabuleiro {

    private int x, y;

    public TabuleiroExibicao(int x, int y) {

        this.y = y + 1;
        this.x = x + 1;
        this.tabuleiro = new String[this.y][this.x];

    }

}
