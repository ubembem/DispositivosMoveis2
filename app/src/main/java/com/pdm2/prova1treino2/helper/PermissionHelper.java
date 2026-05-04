package com.pdm2.prova1treino2.helper;

import android.Manifest;
import android.content.pm.PackageManager;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
public class PermissionHelper {
    private AppCompatActivity activity;
    public PermissionHelper(AppCompatActivity activity) {
        this.activity = activity;
    }
    public boolean temPermissaoNotificacao() {
        return ContextCompat.checkSelfPermission(activity,
                Manifest.permission.POST_NOTIFICATIONS) == PackageManager.PERMISSION_GRANTED;
    }
    public void solicitarPermissao(int requestCode) {
        if (ActivityCompat.shouldShowRequestPermissionRationale(activity,
                Manifest.permission.POST_NOTIFICATIONS)) {
            Toast.makeText(activity,
                    "A permissão é necessária." ,
                    Toast.LENGTH_LONG).show();
        }
        ActivityCompat.requestPermissions(
                activity,
                new String[]{Manifest.permission.POST_NOTIFICATIONS},
                requestCode
        );
    }
}