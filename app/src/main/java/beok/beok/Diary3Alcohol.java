package beok.beok;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.ViewSwitcher;

public class Diary3Alcohol extends AppCompatActivity {

    int dia, mes, ano;

    Button btalcoolprox1, btalcoolprox2, btalcoolprox4, btalcoolprox6,
            btpularpergunta,
            btamigos, btparceiro, btfamiliares, btsozinho, btdesconhecidos,
            btsentiso, btestavafesta, btamigosusando, btbriguei;

    ViewSwitcher viewSwitcher, viewSwitcher2, viewSwitcher3, viewSwitcher4, viewSwitcher5;

    Spinner spalcoolqtd;
    EditText edtxt;
    CalendarView calendarView;

    Animation left, right;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary_3_alcool);

        btalcoolprox1 = (Button) findViewById(R.id.btalcoolprox1);
        btalcoolprox2 = (Button) findViewById(R.id.btalcoolprox2);
        btalcoolprox4 = (Button) findViewById(R.id.btalcoolprox4);
        btalcoolprox6 = (Button) findViewById(R.id.btalcoolprox6);
        btpularpergunta = (Button) findViewById(R.id.btpularpergunta);
        btamigos = (Button) findViewById(R.id.btamigos);
        btparceiro = (Button) findViewById(R.id.btparceiro);
        btfamiliares = (Button) findViewById(R.id.btfamiliares);
        btsozinho = (Button) findViewById(R.id.btsozinho);
        btdesconhecidos = (Button) findViewById(R.id.btdesconhecidos);
        btsentiso = (Button) findViewById(R.id.btsentiso);
        btestavafesta = (Button) findViewById(R.id.btestavafesta);
        btamigosusando = (Button) findViewById(R.id.btamigosusando);
        btbriguei = (Button) findViewById(R.id.btbriguei);

        viewSwitcher = (ViewSwitcher) findViewById(R.id.viewSwitcher);
        viewSwitcher2 = (ViewSwitcher) findViewById(R.id.viewSwitcher2);
        viewSwitcher3 = (ViewSwitcher) findViewById(R.id.viewSwitcher3);
        viewSwitcher4 = (ViewSwitcher) findViewById(R.id.viewSwitcher4);
        viewSwitcher5 = (ViewSwitcher) findViewById(R.id.viewSwitcher5);

        spalcoolqtd = (Spinner) findViewById(R.id.spalcoolqtd);

        edtxt = (EditText) findViewById(R.id.edtxt);

        calendarView = (CalendarView) findViewById(R.id.calendarView);

        left = AnimationUtils.loadAnimation(this, android.R.anim.slide_in_left);
        right = AnimationUtils.loadAnimation(this, android.R.anim.slide_out_right);

        viewSwitcher.setInAnimation(left); viewSwitcher.setOutAnimation(right);
        viewSwitcher2.setInAnimation(left); viewSwitcher2.setOutAnimation(right);
        viewSwitcher3.setInAnimation(left); viewSwitcher3.setOutAnimation(right);
        viewSwitcher4.setInAnimation(left); viewSwitcher4.setOutAnimation(right);
        viewSwitcher5.setInAnimation(left); viewSwitcher5.setOutAnimation(right);



        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                btalcoolprox4.setVisibility(View.VISIBLE);
                dia = dayOfMonth;
                mes = month;
                ano = year;
            }
        });
    }

    public void botaoProximo1(View v){
        //adicionar a quantidade de droga usada
        viewSwitcher.showNext();
    }
    public void botaoProximo2(View v){
        //adicionar as coordenadas do local em que usou
        viewSwitcher2.showNext();
    }
    public void botaoProximo4(View v){
        //Adicionar a data selecionada no calendario do dia em que usou
        viewSwitcher4.showNext();
    }
    public void botaoProximo6(View v){
        //adicionar o que ele escreveu na caixa de texto
        Intent i = new Intent(this, Diary5.class);
        startActivity(i);
    }

    public void pularPergunta(View v){
        viewSwitcher2.showNext();
    }

    public void usouComAmigos(View v){
        //adicionar "com quem" ele usou a droga
        viewSwitcher3.showNext();
    }
    public void usouComDesconhecidos(View v){
        //adicionar "com quem" ele usou a droga
        viewSwitcher3.showNext();
    }
    public void usouComParceiro(View v){
        //adicionar "com quem" ele usou a droga
        viewSwitcher3.showNext();
    }
    public void usouSozinho(View v){
        //adicionar "com quem" ele usou a droga
        viewSwitcher3.showNext();
    }
    public void usouComFamiliares(View v){
        //adicionar "com quem" ele usou a droga
        viewSwitcher3.showNext();
    }

    public void motivoSentiSo(View v){
        //adicionar o motivo pelo qual voce usou a droga
        viewSwitcher5.showNext();
    }
    public void motivoEstavaFesta(View v){
        //adicionar o motivo pelo qual voce usou a droga
        viewSwitcher5.showNext();
    }
    public void motivoAmigosUsando(View v){
        //adicionar o motivo pelo qual voce usou a droga
        viewSwitcher5.showNext();
    }
    public void motivoBriguei(View v){
        //adicionar o motivo pelo qual voce usou a droga
        viewSwitcher5.showNext();
    }

}
