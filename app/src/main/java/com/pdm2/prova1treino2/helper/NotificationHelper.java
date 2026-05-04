package com.pdm2.prova1treino2.helper;

import android.annotation.SuppressLint;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.pdm2.prova1treino2.MainActivity;
import com.pdm2.prova1treino2.R;
import com.pdm2.prova1treino2.ui.piorAluno.PiorAlunoFragment;

public class NotificationHelper {
    public static final String CANAL_ID = "canal_notificacao";
    public static final int NOTIFICACAO_ID = 1;
    private final Context context;
    public NotificationHelper(Context context) {
        this.context = context;
    }
    @SuppressLint("MissingPermission")
    public void gerarNotificacao(String titulo, String conteudo) {
        criarCanal();
        Intent i = new Intent(context, MainActivity.class);
        i.putExtra("destino", "pior_aluno");
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pi = PendingIntent.getActivity(context, 0, i,
                PendingIntent.FLAG_IMMUTABLE);
        Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.pessoas);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, CANAL_ID)
                .setSmallIcon(android.R.drawable.ic_dialog_info)
                .setContentTitle(titulo)
                .setContentText(conteudo)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentIntent(pi)
                .setAutoCancel(true)
                .setLargeIcon(bitmap)
                .setStyle(new NotificationCompat.BigTextStyle()
                        .bigText("Aluno com a pior taxa de acerto calculado!"));
        NotificationManagerCompat nm = NotificationManagerCompat.from(context);
        nm.notify(NOTIFICACAO_ID, builder.build());
    }
    private void criarCanal() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence nome = "Canal Notificação";
            String descricao = "Canal para notificações padrão";
            int importancia = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel canal = new NotificationChannel(CANAL_ID, nome, importancia);
            canal.setDescription(descricao);
            NotificationManager nm = context.getSystemService(NotificationManager.class);
            nm.createNotificationChannel(canal);
        }
    }
}