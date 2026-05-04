package com.pdm2.prova1treino2.ui;

import android.graphics.Bitmap;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.pdm2.prova1treino2.helper.CalcularAcertos;
import com.pdm2.prova1treino2.model.Aluno;
import com.pdm2.prova1treino2.repository.Repositorio;
import java.util.List;
import java.util.Objects;

public class SharedViewModel extends ViewModel {
    private Repositorio repositorio;
    private MutableLiveData<List<Aluno>> lista;
    private MutableLiveData<String> mensagem;
    private MutableLiveData<Aluno> alunoVar = new MutableLiveData<>();
    private MutableLiveData<String> acertos = new MutableLiveData<>();
    private MutableLiveData<Bitmap> foto = new MutableLiveData<>();
    private String piorAluno;

    public SharedViewModel() {
        this.repositorio = new Repositorio();
        lista = new MutableLiveData<>();
        mensagem = new MutableLiveData<>();
    }
    public LiveData<List<Aluno>> getLista() {
        return lista;
    }
    public LiveData<String> getMensagem() {
        return mensagem;
    }
    public void carregarDados() {
        if (lista.getValue() != null && !lista.getValue().isEmpty()) {
            return;// fiz esse if pq estava baixando a lista toda vez que retornava para o fragmentoLista
        }
        repositorio.obterDados(new Repositorio.Callback() {
            @Override
            public void sucesso(List<Aluno> itens) {
                lista.postValue(itens);
            }
            @Override
            public void erro(String msg) {
                mensagem.postValue(msg);
            }
        });
    }
    public void consultar(int id) {
        if (lista.getValue() != null) {
            for (Aluno aluno : lista.getValue()) {
                if (aluno.getNumber() == id) {
                    alunoVar.postValue(aluno);
                    setAcertos(aluno);
                    return;
                }
            }
        }else{
            mensagem.postValue("Lista vazia");
        }
        if (alunoVar.getValue() == null) {
            mensagem.postValue("Aluno não encontrado");
        }
    }

    public LiveData<String> getAcertos() {
        return acertos;
    }

    public void setAcertos(Aluno aluno) {
        int acertos = CalcularAcertos.calcularAcertos(aluno.getResponses(), aluno.getSolution());
        this.acertos.postValue(String.valueOf(acertos));
    }

    public LiveData<Aluno> getAlunoVar() {
        return alunoVar;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        repositorio.shutdown();
    }

    public LiveData<Bitmap> exibirImagem() {
        return foto;
    }

    public void setFoto(Bitmap bitmap){
        foto.postValue(bitmap);
    }

    public String getPiorAluno(){
        if (lista.getValue() == null || lista.getValue().isEmpty()) {
            return "Dados não carregados";
        }
        piorAluno =  CalcularAcertos.calcularPiorAluno(Objects.requireNonNull(lista.getValue()));
        return piorAluno;
    }
}//