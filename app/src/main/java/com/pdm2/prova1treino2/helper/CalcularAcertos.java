package com.pdm2.prova1treino2.helper;

import com.pdm2.prova1treino2.model.Aluno;

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

    public static String calcularAcertosString(Aluno aluno) {
        StringBuilder sb = new StringBuilder();
        int quantidadeDeAcertos = 0;
        for (List<String> resposta : aluno.getResponses()){
            for(int i = 0; (i < resposta.size()) && (i < aluno.getSolution().size()); i++){
                if (resposta.get(i).equals(aluno.getSolution().get(i))){
                    quantidadeDeAcertos++;
                }
            }
            sb.append("Acertos na tentativa ").append(aluno.getResponses().indexOf(resposta) + 1).append(": ").append(quantidadeDeAcertos).append("\n");
            quantidadeDeAcertos = 0;
        }
        return sb.toString();
    }

    public static String calcularErrosString(Aluno aluno) {
        StringBuilder sb = new StringBuilder();
        int totalDeErros = 0;
        for (List<String> resposta : aluno.getResponses()){
            for(int i = 0; (i < resposta.size()) && (i < aluno.getSolution().size()); i++){
                if (resposta.get(i).equals(aluno.getSolution().get(i))){
                    continue;
                }else{
                    totalDeErros++;
                }
            }
        }
        sb.append(totalDeErros);
        return sb.toString();
    }
}
