package com.pdm2.prova1treino2.ui.consulta;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.pdm2.prova1treino2.R;
import com.pdm2.prova1treino2.ui.SharedViewModel;
import com.pdm2.prova1treino2.databinding.FragmentConsultaBinding;

/**
 * Fragment that demonstrates a responsive layout pattern where the format of the content
 * transforms depending on the size of the screen. Specifically this Fragment shows items in
 * the [RecyclerView] using LinearLayoutManager in a small screen
 * and shows items using GridLayoutManager in a large screen.
 */
public class ConsultaFragment extends Fragment {
    private FragmentConsultaBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        SharedViewModel consultantViewModel =
                new ViewModelProvider(requireActivity()).get(SharedViewModel.class);

        binding = FragmentConsultaBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        binding.btConsulta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String texto = binding.etConsulta.getText().toString();
                if(texto == null || texto.isEmpty()){
                    binding.tvMensagem.setVisibility(VISIBLE);
                    binding.tvMensagem.setText(R.string.erro_consulta);
                    Toast.makeText(getContext(), "Digite um valor válido!", Toast.LENGTH_SHORT).show();
                    return;
                }else{
                    binding.tvMensagem.setVisibility(GONE);
                    int id = Integer.parseInt(binding.etConsulta.getText().toString());
                    consultantViewModel.consultar(id);
                    consultantViewModel.getAcertos();
                }
            }
        });


        consultantViewModel.getAlunoVar().observe(getViewLifecycleOwner(), aluno -> {
            if (aluno != null) {
                binding.tvMensagem.setVisibility(GONE);
                binding.textoID.setVisibility(VISIBLE);
                binding.textoNome.setVisibility(VISIBLE);
                binding.textoTaxaDeAcerto.setVisibility(VISIBLE);
                binding.textoPorcentagem.setVisibility(VISIBLE);
                binding.textoViewID.setText(aluno.getIdString());
                binding.textoViewPerson.setText(aluno.getPerson());
                binding.textoViewResponses.setText(aluno.getResponsesString());
                consultantViewModel.getAcertos().observe(getViewLifecycleOwner(), acertos -> {
                    binding.textoViewSolution.setText(acertos);
                });
            }else {
                binding.tvMensagem.setVisibility(VISIBLE);
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