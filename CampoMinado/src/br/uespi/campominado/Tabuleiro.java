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
public class Tabuleiro {

    public String[][] tabuleiro;
    private int x,y;
   

    public Tabuleiro(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
       public void imprimeTabuleiro(){
        int i,j;
        for(i=0;i<this.y;i++){
            System.out.println();
            for(j=0;j<this.x;j++){
                if(i==0){
                    System.out.print("" + this.tabuleiro[i][j] + "| ");
                }else{
                    System.out.print("" + this.tabuleiro[i][j] + " | ");
                }
            }
        }
        System.out.println("");
    }
    
}
