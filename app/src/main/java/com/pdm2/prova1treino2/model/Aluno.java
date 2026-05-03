package com.pdm2.prova1treino2.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Aluno {

    @SerializedName("number")
    @Expose
    private Integer number;
    @SerializedName("person")
    @Expose
    private String person;
    @SerializedName("count")
    @Expose
    private Integer count;
    @SerializedName("responses")
    @Expose
    private List<List<String>> responses;
    @SerializedName("solution")
    @Expose
    private List<String> solution;

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getPerson() {
        return person;
    }

    public void setPerson(String person) {
        this.person = person;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public List<List<String>> getResponses() {
        return responses;
    }

    public void setResponses(List<List<String>> responses) {
        this.responses = responses;
    }

    public List<String> getSolution() {
        return solution;
    }

    public String getIdString(){
        return String.valueOf(number);
    }

    public String getCountString(){
        return String.valueOf(count);
    }

    public String getResponsesString(){
        StringBuilder sb = new StringBuilder();
        int indice =1;
        for (List<String> list : responses) {
            sb.append("Resposta " + indice + ": ");
            indice++;
            sb.append(list.toString());
            sb.append("\n");
        }
        return sb.toString().trim();
    }

    public String getSolutionString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Solução: ");
        if(solution != null){
            sb.append(solution);
        }
        else{
            sb.append("null");
        }
        return sb.toString().trim();
    }

    public void setSolution(List<String> solution) {
        this.solution = solution;
    }

}