package beok.beok;

import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;

import java.util.Calendar;

/**
 * Created by erick on 04/03/17.
 */

public class NotificacaoTerapia extends Service{
    private int hora_notificacao = 18; // horario que deseja receber notificacao do regitro diario
    private int inicio_meta_semanal = 5; //Dia da semana em que deseja receber a notificacao da terapia (5 = quinta)

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, hora_notificacao);
        calendar.set(Calendar.DAY_OF_WEEK, inicio_meta_semanal);

        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this);

        if (Calendar.getInstance().get(Calendar.DAY_OF_WEEK) == calendar.get(Calendar.DAY_OF_WEEK) && Calendar.getInstance().get(Calendar.HOUR_OF_DAY) == calendar.get(Calendar.HOUR_OF_DAY)){
            mBuilder.setSmallIcon(R.mipmap.ic_launcher);
            mBuilder.setContentTitle("BeOk!");
            mBuilder.setContentText("Terapia!");
            mBuilder.setVibrate(new long[] { 100, 250});
            mBuilder.setAutoCancel(true);
            PendingIntent p = PendingIntent.getActivity(this, 3, new Intent(this, Atividade1.class), 0); //Direcionar para alguma tela
            mBuilder.setContentIntent(p);

            NotificationManager mNotifyMgr = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            mNotifyMgr.notify(R.mipmap.ic_launcher, mBuilder.build());
        }

        Intent i = new Intent(NotificacaoTerapia.this, NotificacaoTerapia.class);
        PendingIntent pi = PendingIntent.getService(NotificacaoTerapia.this, 13, i, PendingIntent.FLAG_UPDATE_CURRENT);

        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + 3600000, pi);

        return START_NOT_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
