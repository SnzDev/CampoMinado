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

    String nome;
    int x, y;

    public Partida() {
        recuperaDados(); //Pega Nome e Tamanho do 
        TabuleiroExibicao tabuleiroExibicao = new TabuleiroExibicao(this.x, this.y);
        TabuleiroBomba tabuleiroBomba = new TabuleiroBomba(this.x, this.y);

    }

    //Métodos
    public void recuperaDados() {
        Scanner sc = new Scanner(System.in);
        System.out.println("||BEM VINDO AO JOGO - CAMPO MINADO!||");
        System.out.println("Digite seu Nome:");
        this.nome = sc.nextLine();
        x = 0;
        y = 0;

        while (x < 8 || y < 8) {
            System.out.println("Mínimo 8x8");
            System.out.println("Digite o tamanho do tabuleiro(Linhas[Min:8]: ");
            this.y = sc.nextInt();
            System.out.println("Digite o tamanho do tabuleiro(Colunas[Min:8]: ");
            this.x = sc.nextInt();
        }
    }

    public void mostraMenu() {
        System.out.println("Oque deseja Fazer: 1 - Revelar Espaço | 2 - Marcar Bomba | 3 - Desmarcar Bomba");
    }
}
