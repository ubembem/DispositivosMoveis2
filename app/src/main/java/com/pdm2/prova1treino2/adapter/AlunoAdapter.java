package com.pdm2.prova1treino2.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.pdm2.prova1treino2.R;
import com.pdm2.prova1treino2.model.Aluno;

import java.util.List;

public class AlunoAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    private List<Aluno> itens;
    public AlunoAdapter(Context context, List<Aluno> itens) {
        this.itens = itens;
        this.inflater = LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        return itens.size();
    }
    @Override
    public Aluno getItem(int position) {
        return itens.get(position);
    }
    @Override
    public long getItemId(int position) {
        return position;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_aluno, parent, false);
        }
        Aluno item = getItem(position);
        TextView id = convertView.findViewById(R.id.textoViewID);
        id.setText(item.getIdString());
        TextView person = convertView.findViewById(R.id.textoViewPerson);
        person.setText(item.getPerson());
        TextView count = convertView.findViewById(R.id.textoViewCount);
        count.setText(item.getCountString());
        TextView response = convertView.findViewById(R.id.textoViewResponses);
        response.setText(item.getResponsesString());
        TextView solution = convertView.findViewById(R.id.textoViewSolution);
        solution.setText(item.getSolutionString());

        return convertView;
    }
}