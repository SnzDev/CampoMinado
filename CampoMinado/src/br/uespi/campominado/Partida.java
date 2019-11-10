/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uespi.campominado;

import java.util.Scanner;

/**
 *
 * @author Snz
 */
public class Partida {

    private String nome;
    private int x, y;
    private boolean perdeu = false;
    private boolean ganhou = false;
    private Scanner sc = new Scanner(System.in);
    private int bombasMarcadas;
    private TabuleiroExibicao tabuleiroExibicao;
    private Bombas tabuleiroBomba;
    private long tempoInicio;
    private long tempoTotal;

    public Partida() {
        recuperaDados(); //Pega Nome e Tamanho do Tabuleiro
        this.tabuleiroExibicao = new TabuleiroExibicao(this.x, this.y);
        this.tabuleiroBomba = new Bombas(this.x, this.y);

        tabuleiroExibicao.geraTabuleiro();
        tabuleiroBomba.geraTabuleiro();
        tabuleiroExibicao.imprimeTabuleiro();
        tabuleiroBomba.imprimeTabuleiro();
        tempoInicio = System.currentTimeMillis();
    }

    //Métodos
    public void recuperaDados() {

        System.out.println("||BEM VINDO AO JOGO - CAMPO MINADO!||");
        System.out.println("Digite seu Nome:");
        this.nome = this.sc.nextLine();
        x = 0;
        y = 0;

        while (x < 8 || y < 8) {
            System.out.println("Mínimo 8x8");
            System.out.println("Digite o tamanho do tabuleiro(Linhas[Min:8]: ");
            this.y = this.sc.nextInt();
            System.out.println("Digite o tamanho do tabuleiro(Colunas[Min:8]: ");
            this.x = this.sc.nextInt();
        }
    }

    public void mostraMenu() {
        System.out.println("Oque deseja Fazer: 1 - Revelar Espaço | 2 - Marcar Bomba | 3 - Desmarcar Bomba");
    }

    public void marcarBomba() {
        System.out.println("Digite a posição(X/Y)");
        System.out.println("Digite o numero da linha:");
        int y = sc.nextInt();
        System.out.println("Digite o numero da coluna:");
        int x = sc.nextInt();

        if (this.tabuleiroExibicao.tabuleiro[x][y] == "X" && this.bombasMarcadas < this.tabuleiroBomba.getQuantidadeBombas()) {
            this.tabuleiroExibicao.tabuleiro[x][y] = "!";
            this.bombasMarcadas++;
        } else {
            System.out.println("Não foi possível marcar a bomba.");
        }
    }

    public void desmarcarBomba() {
        System.out.println("Digite a posição(X/Y)");
        System.out.println("Digite o numero da linha:");
        int y = sc.nextInt();
        System.out.println("Digite o numero da coluna:");
        int x = sc.nextInt();

        if (this.tabuleiroExibicao.tabuleiro[x][y] == "!" && this.bombasMarcadas < this.tabuleiroBomba.getQuantidadeBombas()) {
            this.tabuleiroExibicao.tabuleiro[x][y] = "X";
            this.bombasMarcadas--;
        } else {
            System.out.println("Não foi possível desmarcar a bomba.");
        }
    }

    public Boolean revelaEspaco() {
        int y = 0;
        int x = 0;
        while (x < 1 || y < 1 || x > this.tabuleiroExibicao.tabuleiro[0].length || y > this.tabuleiroExibicao.tabuleiro.length) {
            System.out.println("Digite a posição(X/Y)");
            System.out.println("Digite o numero da linha:");
            y = sc.nextInt();
            System.out.println("Digite o numero da coluna:");
            x = sc.nextInt();
        }

        if (this.tabuleiroBomba.tabuleiro[x][y].equals("@")) {
            return false;
        } else {
            //Lados
            revelaBombaEsquerda(x, y);
            revelaBombaDireita(x, y);
            //Topo
            revelaBombaTopoEsquerda(x, y);
            revelaBombaTopoMeio(x, y);
            revelaBombaTopoDireita(x, y);
            //Baixo
            revelaBombaBaixoEsquerda(x, y);
            revelaBombaBaixoMeio(x, y);
            revelaBombaBaixoDireita(x, y);
            return true;
        }

    }

    public void revelaBombaEsquerda(int bx, int by) {

        if (bx - 1 > 0) {
            String e = this.tabuleiroBomba.tabuleiro[by][bx - 1];//E
            if (e != "@") {
                String value = "0";
                if (e != " ") {
                    value = e;
                }
                this.tabuleiroExibicao.tabuleiro[by][bx - 1] = value;
            }
        }
    }

    public void revelaBombaDireita(int bx, int by) {
        if (bx + 1 > 0 && bx + 1 < this.tabuleiroBomba.tabuleiro[0].length) {
            String d = this.tabuleiroBomba.tabuleiro[by][bx + 1];//D
            if (d != "@") {
                String value = d;
                if (d != " ") {
                    value = d;
                }
                this.tabuleiroExibicao.tabuleiro[by][bx + 1] = value;
            }
        }
    }

    public void revelaBombaTopoEsquerda(int bx, int by) {

        //topo
        if (bx - 1 > 0 && by - 1 > 0) {
            String te = this.tabuleiroBomba.tabuleiro[by - 1][bx - 1];//TE
            if (te != "@") {
                String value = "0";
                if (te != " ") {
                    value = te;
                }
                this.tabuleiroExibicao.tabuleiro[by - 1][bx - 1] = value;
            }
        }
    }

    public void revelaBombaTopoMeio(int bx, int by) {

        if (by - 1 > 0) {
            String tm = this.tabuleiroBomba.tabuleiro[by - 1][bx];//TM
            if (tm != "@") {
                String value = "0";
                if (tm != " ") {
                    value = tm;
                }
                this.tabuleiroExibicao.tabuleiro[by - 1][bx] = value;
            }
        }
    }

    public void revelaBombaTopoDireita(int bx, int by) {

        if (bx + 1 > 0 && bx + 1 < this.tabuleiroBomba.tabuleiro[0].length && by - 1 > 0) {
            String td = this.tabuleiroBomba.tabuleiro[by - 1][bx + 1];//TD  
            if (td != "@") {
                String value = "0";
                if (td != " ") {
                    value = td;
                }
                this.tabuleiroExibicao.tabuleiro[by - 1][bx + 1] = value;
            }
        }
    }

    public void revelaBombaBaixoEsquerda(int bx, int by) {
        //rodapé
        if (bx - 1 > 0 && by + 1 > 0 && by + 1 < this.tabuleiroBomba.tabuleiro.length) {
            String be = this.tabuleiroBomba.tabuleiro[by + 1][bx - 1];//BE
            if (be != "@") {
                String value = "0";
                if (be != " ") {
                    value = be;
                }
                this.tabuleiroExibicao.tabuleiro[by + 1][bx - 1] = value;
            }
        }
    }

    public void revelaBombaBaixoMeio(int bx, int by) {
        if (by + 1 >= 0 && by + 1 < this.tabuleiroBomba.tabuleiro.length) {
            String bm = this.tabuleiroBomba.tabuleiro[by + 1][bx];//BM
            if (bm != "@") {
                String value = bm;
                if (bm != " ") {
                    value = bm;
                }
                this.tabuleiroExibicao.tabuleiro[by + 1][bx] = value;
            }
        }
    }

    public void revelaBombaBaixoDireita(int bx, int by) {
        if (bx + 1 > 0 && bx + 1 < this.tabuleiroBomba.tabuleiro[0].length && by + 1 > 0 && by + 1 < this.tabuleiroBomba.tabuleiro.length) {
            String bd = this.tabuleiroBomba.tabuleiro[by + 1][bx + 1];//BD
            if (bd != "@") {
                String value = bd;
                if (bd != " ") {
                    value = bd;
                }
                this.tabuleiroExibicao.tabuleiro[by + 1][bx + 1] = value;
            }
        }
    }

    public void acao() {
        while (this.perdeu) {

        }
    }

}
