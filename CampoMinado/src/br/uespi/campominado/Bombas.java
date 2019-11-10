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
public class Bombas extends TabuleiroBomba {

    private Integer[] posicaoBombaX;
    private Integer[] posicaoBombaY;

    public Bombas(int x, int y) {
        super(x, y);
        this.quantidadeBombas = (int) ((y * x) * (0.16));
        this.posicaoBombaX = new Integer[this.quantidadeBombas];
        this.posicaoBombaY = new Integer[this.quantidadeBombas];
    }

    //Métodos
    public int gerarNumeroAleatorio(int ate) {
        Random r = new Random();
        int menor = 1;
        int maior = ate;
        int numeroAleatorio = r.nextInt(maior - menor) + menor;
        return numeroAleatorio;
    }

    public void geraBombas() {
        int i;
        for (i = 0; i < this.quantidadeBombas; i++) {
            this.posicaoBombaX[i] = gerarNumeroAleatorio(this.x);
            this.posicaoBombaY[i] = gerarNumeroAleatorio(this.y);
            //System.out.println("Bomba:" + i + " " + this.posicaoBombaX[i] + "/" + this.posicaoBombaY[i]);
        }
        verificaBombasDuplicadas(5); //Verifica 5 vezes pra saber se tem duplicadas(2 já são impossíveis de duplicarem imagina 5)
    }

    public void verificaBombasDuplicadas(int quantidadeDeVerificacoes) {
        int h, i, j;
        for (h = 0; h < quantidadeDeVerificacoes; h++) {
            for (i = 0; i < this.quantidadeBombas; i++) {
                for (j = 0; j < this.quantidadeBombas; j++) {
                    if (i != j) {
                        while ((this.posicaoBombaX[i] == this.posicaoBombaX[j]) && (this.posicaoBombaY[i] == this.posicaoBombaY[j])) {
                            this.posicaoBombaX[i] = gerarNumeroAleatorio(this.x);
                            this.posicaoBombaY[i] = gerarNumeroAleatorio(this.y);
                            //System.out.println("Duplicado");
                        }
                    }
                }
            }
        }
    }

    public void geraTabuleiro() {
        geraBombas();
        int i, j;
        this.tabuleiro[0][0] = "X/Y"; //Posição 0/0
        for (i = 1; i < this.y; i++) { //Coordenada Y
            DecimalFormat df = new DecimalFormat("00");
            this.tabuleiro[i][0] = df.format(i);
        }
        for (j = 1; j < this.x; j++) { //Coordenada X
            DecimalFormat df = new DecimalFormat("00");
            this.tabuleiro[0][j] = df.format(j);
        }
        for (i = 1; i < this.y; i++) {
            for (j = 1; j < this.x; j++) {
                this.tabuleiro[i][j] = " ";
            }
        }
        for (i = 0; i < this.quantidadeBombas; i++) {
            int bx = this.posicaoBombaX[i];
            int by = this.posicaoBombaY[i];
            this.tabuleiro[by][bx] = "@";

            //Lados
            numeraBombaEsquerda(bx, by);
            numeraBombaDireita(bx, by);
            //Topo
            numeraBombaTopoEsquerda(bx, by);
            numeraBombaTopoMeio(bx, by);
            numeraBombaTopoDireita(bx, by);
            //Baixo
            numeraBombaBaixoEsquerda(bx, by);
            numeraBombaBaixoMeio(bx, by);
            numeraBombaBaixoDireita(bx, by);
        }
    }

    public void numeraBombaEsquerda(int bx, int by) {

        if (bx - 1 > 0) {
            String e = this.tabuleiro[by][bx - 1];//E
            if (e != "@") {
                String value = "0";
                if (e != " ") {
                    value = e;
                }
                this.tabuleiro[by][bx - 1] = Integer.toString(Integer.parseInt(value) + 1);
            }
        }
    }

    public void numeraBombaDireita(int bx, int by) {
        if (bx + 1 > 0 && bx + 1 < tabuleiro[0].length) {
            String d = this.tabuleiro[by][bx + 1];//D
            if (d != "@") {
                String value = "0";
                if (d != " ") {
                    value = d;
                }
                this.tabuleiro[by][bx + 1] = Integer.toString(Integer.parseInt(value) + 1);
            }
        }
    }

    public void numeraBombaTopoEsquerda(int bx, int by) {

        //topo
        if (bx - 1 > 0 && by - 1 > 0) {
            String te = this.tabuleiro[by - 1][bx - 1];//TE
            if (te != "@") {
                String value = "0";
                if (te != " ") {
                    value = te;
                }
                this.tabuleiro[by - 1][bx - 1] = Integer.toString(Integer.parseInt(value) + 1);
            }
        }
    }

    public void numeraBombaTopoMeio(int bx, int by) {

        if (by - 1 > 0) {
            String tm = this.tabuleiro[by - 1][bx];//TM
            if (tm != "@") {
                String value = "0";
                if (tm != " ") {
                    value = tm;
                }
                this.tabuleiro[by - 1][bx] = Integer.toString(Integer.parseInt(value) + 1);
            }
        }
    }

    public void numeraBombaTopoDireita(int bx, int by) {

        if (bx + 1 > 0 && bx + 1 < tabuleiro[0].length && by - 1 > 0) {
            String td = this.tabuleiro[by - 1][bx + 1];//TD  
            if (td != "@") {
                String value = "0";
                if (td != " ") {
                    value = td;
                }
                this.tabuleiro[by - 1][bx + 1] = Integer.toString(Integer.parseInt(value) + 1);
            }
        }
    }

    public void numeraBombaBaixoEsquerda(int bx, int by) {
        //rodapé
        if (bx - 1 > 0 && by + 1 > 0 && by + 1 < tabuleiro.length) {
            String be = this.tabuleiro[by + 1][bx - 1];//BE
            if (be != "@") {
                String value = "0";
                if (be != " ") {
                    value = be;
                }
                this.tabuleiro[by + 1][bx - 1] = Integer.toString(Integer.parseInt(value) + 1);
            }
        }
    }

    public void numeraBombaBaixoMeio(int bx, int by) {
        if (by + 1 >= 0 && by + 1 < tabuleiro.length) {
            String bm = this.tabuleiro[by + 1][bx];//BM
            if (bm != "@") {
                String value = "0";
                if (bm != " ") {
                    value = bm;
                }
                this.tabuleiro[by + 1][bx] = Integer.toString(Integer.parseInt(value) + 1);
            }
        }
    }

    public void numeraBombaBaixoDireita(int bx, int by) {
        if (bx + 1 > 0 && bx + 1 < tabuleiro[0].length && by + 1 > 0 && by + 1 < tabuleiro.length) {
            String bd = this.tabuleiro[by + 1][bx + 1];//BD
            if (bd != "@") {
                String value = "0";
                if (bd != " ") {
                    value = bd;
                }
                this.tabuleiro[by + 1][bx + 1] = Integer.toString(Integer.parseInt(value) + 1);
            }
        }
    }

    public void mostraPosicaoBombas() {
        int i;
        System.out.println("Quantidade de Bombas: " + this.quantidadeBombas);
        for (i = 0; i < this.quantidadeBombas; i++) {
            System.out.println("Bomba:" + (i + 1) + " X:" + this.posicaoBombaX[i] + "/Y:" + this.posicaoBombaY[i]);
        }
    }

}
