package beok.beok;

import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.IBinder;
import android.preference.PreferenceManager;
import android.support.v4.app.NotificationCompat;

import java.util.Calendar;

/**
 * Created by erick on 01/03/17.
 */

public class NotificacaoDiario extends Service {

    private int hora_notificacao; // horario que deseja receber notificacao do regitro diario
    private int minuto_notificacao;

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

        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
        hora_notificacao = Integer.valueOf(pref.getString("horaNotificacao", "12"));
        minuto_notificacao = Integer.valueOf(pref.getString("minutoNotificacao", "0"));

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, hora_notificacao);
        calendar.set(Calendar.MINUTE, minuto_notificacao);

        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this);

        if (Calendar.getInstance().get(Calendar.HOUR_OF_DAY) == calendar.get(Calendar.HOUR_OF_DAY) && Calendar.getInstance().get(Calendar.MINUTE) == calendar.get(Calendar.MINUTE)) {
            mBuilder.setSmallIcon(R.mipmap.ic_launcher);
            mBuilder.setContentTitle("BeOk!");
            mBuilder.setContentText("Registro Diário!");
            mBuilder.setVibrate(new long[] { 100, 250});
            mBuilder.setAutoCancel(true);
            PendingIntent p = PendingIntent.getActivity(this, 1, new Intent(this, Diary1.class), 0);
            mBuilder.setContentIntent(p);

            NotificationManager mNotifyMgr = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            mNotifyMgr.notify(R.mipmap.ic_launcher, mBuilder.build());
        }

        Intent i = new Intent(NotificacaoDiario.this, NotificacaoDiario.class);
        PendingIntent pi = PendingIntent.getService(NotificacaoDiario.this, 11, i, PendingIntent.FLAG_UPDATE_CURRENT);

        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + 60000, pi);

        return START_NOT_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
