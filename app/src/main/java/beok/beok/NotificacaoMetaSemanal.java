package beok.beok;

import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.IBinder;
import android.preference.PreferenceManager;
import android.support.v4.app.NotificationCompat;

import java.util.Calendar;

/**
 * Created by erick on 04/03/17.
 */

public class NotificacaoMetaSemanal extends Service {
    private int hora_notificacao ; // horario que deseja receber notificacao do regitro diario
    private int minuto_notificacao = 20;
    private int inicio_meta_semanal; //Dia da semana em que deseja receber a notificacao da meta semanal (5 = quinta)

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
        inicio_meta_semanal = Integer.valueOf(pref.getString("notifications_week_day", "1"));

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, hora_notificacao);
        calendar.set(Calendar.DAY_OF_WEEK, inicio_meta_semanal);
        calendar.set(Calendar.MINUTE, minuto_notificacao);

        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this);

        if (Calendar.getInstance().get(Calendar.DAY_OF_WEEK) == calendar.get(Calendar.DAY_OF_WEEK) && Calendar.getInstance().get(Calendar.HOUR_OF_DAY) == calendar.get(Calendar.HOUR_OF_DAY) && Calendar.getInstance().get(Calendar.MINUTE) == calendar.get(Calendar.MINUTE)){
            mBuilder.setSmallIcon(R.mipmap.ic_launcher);
            mBuilder.setContentTitle("BeOk!");
            mBuilder.setContentText("Meta semanal!");
            mBuilder.setVibrate(new long[] { 100, 250});
            mBuilder.setAutoCancel(true);
            PendingIntent p = PendingIntent.getActivity(this, 2, new Intent(this, MetaSemanal.class), 0);
            mBuilder.setContentIntent(p);

            NotificationManager mNotifyMgr = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            mNotifyMgr.notify(R.mipmap.ic_launcher, mBuilder.build());
        }

        Intent i = new Intent(NotificacaoMetaSemanal.this, NotificacaoMetaSemanal.class);
        PendingIntent pi = PendingIntent.getService(NotificacaoMetaSemanal.this, 12, i, PendingIntent.FLAG_UPDATE_CURRENT);

        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + 60000, pi);

        return START_NOT_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
