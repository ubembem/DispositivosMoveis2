package com.pdm2.prova1treino2.ui.tentativa;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.pdm2.prova1treino2.databinding.FragmentTentativasBinding;
import com.pdm2.prova1treino2.ui.SharedViewModel;

public class TentativasFragment extends Fragment {
    private FragmentTentativasBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        SharedViewModel viewModel =
                new ViewModelProvider(this).get(SharedViewModel.class);

        binding = FragmentTentativasBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

//        final TextView textView = binding.textSettings;
//        settingsViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}