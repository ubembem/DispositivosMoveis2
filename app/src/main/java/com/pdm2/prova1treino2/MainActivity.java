package com.pdm2.prova1treino2;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.navigation.NavController;
import androidx.navigation.NavOptions;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.appcompat.app.AppCompatActivity;

import com.pdm2.prova1treino2.databinding.ActivityMainBinding;
import com.pdm2.prova1treino2.helper.PermissaoDialogFragment;

public class MainActivity extends AppCompatActivity {
    private static final int CODIGO_SOLICITACAO = 1;
    private static final int CODIGO_SOLICITACAO_MAPA = 2;
    private static final String PERMISSAO = Manifest.permission.CAMERA;
    private static final String PERMISSAO_MAPA = Manifest.permission.ACCESS_FINE_LOCATION;
    private AppBarConfiguration mAppBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarMain.toolbar);
        binding.appBarMain.fab.setOnClickListener(new View.OnClickListener() {  //adicionar um listener para capturar evento de clique
            @Override
            public void onClick(View view) {
                solicitarPermissao();
            }
        });
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment_content_main);
        assert navHostFragment != null;
        NavController navController = navHostFragment.getNavController();

        NavigationView navigationView = binding.navView;
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_consulta, R.id.nav_lista, R.id.nav_mapa)
                .setOpenableLayout(binding.drawerLayout)
                .build();
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        boolean result = super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.overflow, menu);
        return result;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.nav_mapa) {
            solicitarPermissaoMapa(new String[]{
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION
            });
        }
        if (item.getItemId() == R.id.nav_tentativas) {
            NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
            navController.navigate(R.id.nav_tentativas, null, new NavOptions.Builder().setPopUpTo(R.id.nav_lista, true).build());
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == CODIGO_SOLICITACAO) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                chamarFragmento();
            } else {
                PermissaoDialogFragment dialog = new PermissaoDialogFragment();
                dialog.show(getSupportFragmentManager(), "PermissaoDialog");
            }
        }
        if (requestCode == CODIGO_SOLICITACAO_MAPA) {
            boolean granted = true;
            for (int result : grantResults) {
                if (result != PackageManager.PERMISSION_GRANTED) {
                    granted = false;
                    break;
                }
            }
            if (granted) {
                inicializarMapa();
            } else {
                Toast.makeText(this, "Permissão negada. O mapa não será exibido.", Toast.LENGTH_LONG).show();
            }
        }
    }

    private void solicitarPermissao() {
        int temPermissao = ContextCompat.checkSelfPermission(this, PERMISSAO);
        if (temPermissao != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{PERMISSAO}, CODIGO_SOLICITACAO);
        } else {
            chamarFragmento();
        }
    }

    private void solicitarPermissaoMapa(String[] permissions) {
        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(this, permission)
                    != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, permissions, CODIGO_SOLICITACAO_MAPA);
                return;
            }
        }
        inicializarMapa();
    }

    private void inicializarMapa() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        navController.navigate(R.id.nav_mapa, null, new NavOptions.Builder().setPopUpTo(R.id.nav_lista, true).build());
    }

    private void chamarFragmento() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        navController.navigate(R.id.nav_camera);
    }
}