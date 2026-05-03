package com.pdm2.prova1treino2.ui.lista;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import com.pdm2.prova1treino2.R;
import com.pdm2.prova1treino2.adapter.AlunoAdapter;
import com.pdm2.prova1treino2.databinding.FragmentListaBinding;
import com.pdm2.prova1treino2.ui.SharedViewModel;

public class ListaFragment extends Fragment {
    private ListView listView;
    private AlunoAdapter meuAdapter;
    private FragmentListaBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        SharedViewModel listaViewModel =
                new ViewModelProvider(requireActivity()).get(SharedViewModel.class);

        binding = FragmentListaBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        listaViewModel.carregarDados();
        listView = binding.listViewDados;
        listaViewModel.getLista().observe(getViewLifecycleOwner(), lista -> {
                    if (lista != null) {
                        meuAdapter = new AlunoAdapter(getContext(), lista);
                        binding.listViewDados.setAdapter(meuAdapter);
                    }
        });
        listaViewModel.getMensagem().observe(getViewLifecycleOwner(), mensagem -> {
            if (mensagem != null) {
                binding.tvMensagem.setText(mensagem);
            }
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}