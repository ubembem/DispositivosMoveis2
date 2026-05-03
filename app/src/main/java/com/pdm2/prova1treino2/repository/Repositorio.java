package com.pdm2.prova1treino2.repository;

import android.graphics.Bitmap;
import android.util.Log;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.pdm2.prova1treino2.model.Aluno;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
public class Repositorio {
    private static final String URL = "https://raw.githubusercontent.com/ubembem/DispositivosMoveis2/refs/heads/main/prova/db.json";
    private ExecutorService executor;
    public Repositorio() {
        executor = Executors.newSingleThreadExecutor();
    }

    public interface Callback {
        void sucesso(List<Aluno> items);
        void erro(String mensagem);
    }
    public void obterDados(Callback c) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    Conexao conexao = new Conexao();
                    InputStream inputStream = conexao.obterRespostaHTTP(URL);
                    Conversao conversao = new Conversao();
                    String jsonString = conversao.converter(inputStream);
                    if (jsonString == null || jsonString.isEmpty()) {
                        c.erro("Resposta vazia");
                        return;
                    }
                    Log.i("JSON", "Recebido " + jsonString);
                    Gson gson = new Gson();
                    Type type = new TypeToken<List<Aluno>>(){}.getType();
                    List<Aluno> dados = gson.fromJson(jsonString, type);
                    List<Aluno> itens = new ArrayList<>();
                    if (dados != null) {
                        itens = dados;
                    }
                    c.sucesso(itens);
                } catch (Exception e) {
                    e.printStackTrace();
                    c.erro("Erro ao baixar dados: " + e.getMessage());
                }
            }
        });
    }
    public void shutdown() {
        executor.shutdown();
    }
}
