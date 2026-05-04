package com.pdm2.prova1treino2.ui.mapa;


import static android.view.View.GONE;
import static androidx.core.content.ContextCompat.checkSelfPermission;
import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.pdm2.prova1treino2.R;
import com.pdm2.prova1treino2.ui.SharedViewModel;
import com.pdm2.prova1treino2.databinding.FragmentMapaBinding;
import org.osmdroid.config.Configuration;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.overlay.Marker;
import java.util.Objects;

public class MapaFragment extends Fragment {
    private FrameLayout mapContainer;
    private FragmentMapaBinding binding;
    private static final String PERMISSAO_MAPA = Manifest.permission.ACCESS_FINE_LOCATION;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        SharedViewModel viewModel =
                new ViewModelProvider(requireActivity()).get(SharedViewModel.class);

        binding = FragmentMapaBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        Configuration.getInstance().setUserAgentValue(Objects.requireNonNull(getActivity()).getPackageName());
        mapContainer = binding.mapContainer;
        binding.mapView.setMultiTouchControls(true);
        GeoPoint palmasIFTO = new GeoPoint(-10.19794, -48.31207);
        binding.mapView.getController().setZoom(20.0);
        binding.mapView.getController().setCenter(palmasIFTO);
        Marker marker = new Marker(binding.mapView);
        marker.setPosition(palmasIFTO);
        marker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
        marker.setTitle("IFTO");
        marker.setIcon(ContextCompat.getDrawable(Objects.requireNonNull(getContext()), R.drawable.livro));
        binding.mapView.getOverlays().add(marker);
        if(checkSelfPermission(getContext(), PERMISSAO_MAPA) == PackageManager.PERMISSION_GRANTED){
            mapContainer.setVisibility(View.VISIBLE);
        }else{
            binding.tvSemPermissao.setVisibility(View.VISIBLE);
            binding.mapView.setVisibility(GONE);
        }
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}