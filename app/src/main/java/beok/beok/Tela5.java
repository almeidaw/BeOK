package beok.beok;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.TimePicker;

import beok.beok.POJO.ContatoEmergencia;
import beok.beok.api.DB;

import static beok.beok.R.id.edtxtcontato3;
import static beok.beok.R.id.txtcontato3;

public class Tela5 extends AppCompatActivity {

    RadioGroup rg;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela5);

        rg = (RadioGroup) findViewById(R.id.rg);

    }

    public void onClick5(View v) {

        SharedPreferences SP = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = SP.edit();

        switch (v.getId()) {
            case R.id.btproximo3:

                switch (rg.getCheckedRadioButtonId()) {
                    case R.id.domingo:
                        editor.putInt("notifications_week_day", 1);
                        break;
                    case R.id.segunda:
                        editor.putInt("notifications_week_day", 2);
                        break;
                    case R.id.terca:
                        editor.putInt("notifications_week_day", 3);
                        break;
                    case R.id.quarta:
                        editor.putInt("notifications_week_day", 4);
                        break;
                    case R.id.quinta:
                        editor.putInt("notifications_week_day", 5);
                        break;
                    case R.id.sexta:
                        editor.putInt("notifications_week_day", 6);
                        break;
                    case R.id.sabado:
                        editor.putInt("notifications_week_day", 7);
                        break;
                }

                Intent nextActivity = new Intent(this, Tela6.class);
                startActivity(nextActivity);
                //slide from right to left
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);

                editor.commit();
        }

    }
    @Override
    public void onBackPressed() {

    }
}
