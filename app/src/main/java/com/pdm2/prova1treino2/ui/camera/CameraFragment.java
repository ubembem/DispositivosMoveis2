package com.pdm2.prova1treino2.ui.camera;

import static android.app.Activity.RESULT_OK;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import com.pdm2.prova1treino2.databinding.FragmentCameraBinding;
import com.pdm2.prova1treino2.ui.SharedViewModel;

public class CameraFragment extends Fragment {
    private FragmentCameraBinding binding;
    private SharedViewModel viewModel;
    private final ActivityResultLauncher<Intent> cameraLauncher =
            registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                    new ActivityResultCallback<ActivityResult>() {
                        @Override
                        public void onActivityResult(ActivityResult result) {
                            if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                                Intent data = result.getData();
                                Bitmap foto = null;
                                if (Build.VERSION.SDK_INT >= 33) {
                                    foto = data.getParcelableExtra("data", Bitmap.class);
                                } else {
                                    foto = (Bitmap) data.getParcelableExtra("data");
                                }
                                if (foto != null) {
                                    viewModel.setFoto(foto);
                                    binding.imFoto.setImageBitmap(foto);
                                }
                            }
                        }
                    });

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        viewModel =
                new ViewModelProvider(requireActivity()).get(SharedViewModel.class);

        binding = FragmentCameraBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        abrirCamera();

        viewModel.exibirImagem().observe(getViewLifecycleOwner(), imagem -> {
            if (imagem != null) {
                binding.imFoto.setImageBitmap(imagem);
            }
        });

        binding.btVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requireActivity().onBackPressed();
            }
        });

        return root;
    }

    private void abrirCamera() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        cameraLauncher.launch(intent);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}