package com.pdm2.prova1treino2.helper;

import java.util.List;

public class CalcularAcertos {
    public static int calcularAcertos(List<List<String>> respostas, List<String> solution) {
        int totalDeAcertos = 0;
        int quantidadeDeRespostas = 0;
        for (List<String> resposta : respostas){
            for(int i = 0; (i < resposta.size()) && (i < solution.size()); i++){
                if (resposta.get(i).equals(solution.get(i))){
                    totalDeAcertos++;
                }
                quantidadeDeRespostas++;
            }
        }
        if(quantidadeDeRespostas > 0){
            return (totalDeAcertos * 100) / quantidadeDeRespostas;
        }else{
            return 0;
        }
    }
}
