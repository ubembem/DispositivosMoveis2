package com.pdm2.prova1treino2.ui.mapa;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import com.pdm2.prova1treino2.ui.SharedViewModel;
import com.pdm2.prova1treino2.databinding.FragmentMapaBinding;

import org.osmdroid.config.Configuration;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.overlay.Marker;

import java.util.Objects;

public class MapaFragment extends Fragment {
    private FrameLayout mapContainer;
    private FragmentMapaBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        SharedViewModel viewModel =
                new ViewModelProvider(this).get(SharedViewModel.class);

        binding = FragmentMapaBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        Configuration.getInstance().setUserAgentValue(Objects.requireNonNull(getActivity()).getPackageName());
        mapContainer = binding.mapContainer;
        binding.mapView.setMultiTouchControls(true);
        GeoPoint palmas = new GeoPoint(-10.184, -48.333);
        binding.mapView.getController().setZoom(13.0);
        binding.mapView.getController().setCenter(palmas);
        Marker marker = new Marker(binding.mapView);
        marker.setPosition(palmas);
        marker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
        marker.setTitle("Palmas - TO");
        marker.setIcon(ContextCompat.getDrawable(Objects.requireNonNull(getContext()), org.osmdroid.library.R.drawable.person));
        binding.mapView.getOverlays().add(marker);
        mapContainer.setVisibility(View.VISIBLE);
        //mapContainer.setVisibility(GONE);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}