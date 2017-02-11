package beok.beok;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;

public class Pergunta4 extends AppCompatActivity {

    boolean[] array;

    int dia, mes, ano;

    Button btprox4;
    CalendarView calendarView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pergunta4);

        btprox4 = (Button) findViewById(R.id.btprox4);
        calendarView = (CalendarView) findViewById(R.id.calendarView);

        Bundle bundle = getIntent().getExtras();
        array = bundle.getBooleanArray("checkbox");

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                btprox4.setVisibility(View.VISIBLE);
                dia = dayOfMonth;
                mes = month;
                ano = year;
            }
        });
    }

    public void botaoProximo4(View v){
        //Adicionar a data selecionada no calendario do dia em que usou
        Bundle bundle = new Bundle();
        bundle.putBooleanArray("checkbox", array);
        Intent i = new Intent(this, Pergunta5.class);
        i.putExtras(bundle);
        startActivity(i);
        //slide from right to left
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }
}
