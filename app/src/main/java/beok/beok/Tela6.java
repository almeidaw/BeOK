package beok.beok;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;

public class Tela6 extends AppCompatActivity {

    TimePicker timePicker1;
    int hora, minuto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela6);

        timePicker1 = (TimePicker) findViewById(R.id.timePicker1);
        timePicker1.setIs24HourView(true);

    }

    public void onClick6(View v) {

        SharedPreferences SP = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = SP.edit();

        switch (v.getId()) {
            case R.id.btproximo4:

                hora = timePicker1.getCurrentHour();
                minuto = timePicker1.getCurrentHour();

                editor.putString("notification_time", hora + ":" + minuto);

                editor.putInt("horaNotificacao", hora);
                editor.putInt("minutoNotificacao", minuto);

                Intent nextActivity = new Intent(this, Atividades.class);
                Bundle bundle = new Bundle();
                bundle.putInt("atividade", 1);
                nextActivity.putExtras(bundle);
                startActivity(nextActivity);

                //slide from right to left
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);

                editor.commit();
                break;

        }
    }
    @Override
    public void onBackPressed() {

    }
}
