/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uespi.campominado;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Snz
 */
public class PlayGame {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Partida> partidas = new ArrayList<Partida>();
        String opcao = "s";

        while (opcao.equals("s")) {
            Partida campoMinado = new Partida();
            partidas.add(campoMinado);
            System.out.println("Você Deseja Jogar Novamente?(S/N)");
            opcao = sc.nextLine().toLowerCase();
            while (opcao != "s" || opcao != "n") {
                System.out.println("Opção Inválida!");
                System.out.println("Você Deseja Jogar Novamente?(S/N)");
                opcao = sc.nextLine().toLowerCase();
            }
        }

        System.out.println("Resultados Anteriores: ");

        for (int i = 0; i < partidas.size(); i++) {  //enquanto i for menor, não maior
            System.out.println("Nome: " + partidas.get(i).getNome() + " Tempo: " + partidas.get(i).getTempoTotal() + " segundos");
        }
    }

}
