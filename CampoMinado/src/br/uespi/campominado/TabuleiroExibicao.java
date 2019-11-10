/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uespi.campominado;

import java.text.DecimalFormat;

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
    
    //Métodos
    public void geraTabuleiro(){
        int i,j;
        this.tabuleiro[0][0] = "X/Y"; //Posição 0/0
        
        for(i=1;i<this.y;i++){ //Coordenada Y
            DecimalFormat df = new DecimalFormat("00");            
            this.tabuleiro[i][0] = df.format(i);
        }
        for(j=1;j<this.x;j++){ //Coordenada X
            DecimalFormat df = new DecimalFormat("00");            
            this.tabuleiro[0][j] = df.format(j);
        }
        
        for(i=1;i<this.y;i++){
            for(j=1;j<this.x;j++){
                this.tabuleiro[i][j] = "X";
            }
        }
    }

}
