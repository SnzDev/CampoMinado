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
    private boolean perdeu;
    private boolean ganhou;
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
        this.ganhou = false;
        this.perdeu = false;
        tabuleiroExibicao.geraTabuleiro();
        tabuleiroBomba.geraTabuleiro();
        tabuleiroExibicao.imprimeTabuleiro();
        tabuleiroBomba.imprimeTabuleiro();

        iniciaJogo();

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
            System.out.println("Digite o numero da Coluna:");
            x = sc.nextInt();
            System.out.println("Digite o numero da Linha:");
            y = sc.nextInt();
        }

        if (this.tabuleiroBomba.tabuleiro[y][x].equals("@")) {
            return true;
        } else {
            //Meio
            if (revelaBombaMeio(x, y)) {
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
            }
            return false;
        }

    }

    public boolean revelaBombaMeio(int bx, int by) {

        String m = this.tabuleiroBomba.tabuleiro[by][bx];//E
        if (m != "@") {
            String value = m;

            this.tabuleiroExibicao.tabuleiro[by][bx] = value;

        }
        if (m != " ") {
            return false;
        } else {
            return true;
        }

    }

    public void revelaBombaEsquerda(int bx, int by) {

        if (bx - 1 > 0) {
            String e = this.tabuleiroBomba.tabuleiro[by][bx - 1];//E
            if (e != "@") {
                String value = e;
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
                String value = te;
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
                String value = tm;
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
                String value = td;
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
                String value = be;
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

    public void iniciaJogo() {
        this.tempoInicio = System.currentTimeMillis();
        while (this.perdeu == false && this.ganhou == false) {
            mostraMenu();
            acao(this.sc.nextInt());
        }
        this.tempoTotal = (System.currentTimeMillis() - this.tempoInicio) / 1000;
        if (perdeu) {
            this.tabuleiroBomba.imprimeTabuleiro();
            System.out.println(this.nome + " infelizmente você perdeu!");
            System.out.println("Você durou: " + this.tempoTotal + " segundos");
        }
        if (ganhou) {
            System.out.println("Parabéns " + this.nome + "Você Ganhou!");
            System.out.println("Você durou: " + this.tempoTotal + " segundos");
        }

    }

    public long getTempoTotal() {
        return tempoTotal;
    }

    public String getNome() {
        return nome;
    }

    public void acao(int opcao) {
        switch (opcao) {
            case 1:
                this.perdeu = revelaEspaco();
                this.ganhou = verificaGanho();
                this.tabuleiroExibicao.imprimeTabuleiro();
                break;
            case 2:
                marcarBomba();
                this.tabuleiroExibicao.imprimeTabuleiro();
                this.ganhou = verificaGanho();
                break;
            case 3:
                desmarcarBomba();
                this.tabuleiroExibicao.imprimeTabuleiro();
                this.ganhou = verificaGanho();
                break;
            default:
                System.out.println("Opção Inválida!");
                break;
        }
    }

    public boolean verificaGanho() {
        int i, j;
        int k = 0;
        for (i = 0; i < this.y + 1; i++) {
            System.out.println();
            for (j = 0; j < this.x + 1; j++) {
                if (this.tabuleiroExibicao.tabuleiro[i][j] == "X") {
                    k++;
                }
            }
        }
        if (k == 0) {
            return true;
        } else {
            return false;
        }
    }

}
