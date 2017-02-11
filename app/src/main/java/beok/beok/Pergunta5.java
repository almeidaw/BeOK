package beok.beok;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Pergunta5 extends AppCompatActivity {

    boolean[] array;

    Button btsentiso, btestavafesta, btamigosusando, btbriguei;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pergunta5);

        btsentiso = (Button) findViewById(R.id.btsentiso);
        btestavafesta = (Button) findViewById(R.id.btestavafesta);
        btamigosusando = (Button) findViewById(R.id.btamigosusando);
        btbriguei = (Button) findViewById(R.id.btbriguei);

        Bundle bundle = getIntent().getExtras();
        array = bundle.getBooleanArray("checkbox");
    }

    public void motivoSentiSo(View v){
        //adicionar o motivo pelo qual voce usou a droga
        Bundle bundle = new Bundle();
        bundle.putBooleanArray("checkbox", array);
        Intent i = new Intent(this, Pergunta6.class);
        i.putExtras(bundle);
        startActivity(i);
        //slide from right to left
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }
    public void motivoEstavaFesta(View v){
        //adicionar o motivo pelo qual voce usou a droga
        Bundle bundle = new Bundle();
        bundle.putBooleanArray("checkbox", array);
        Intent i = new Intent(this, Pergunta6.class);
        i.putExtras(bundle);
        startActivity(i);        //slide from right to left
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }
    public void motivoAmigosUsando(View v){
        //adicionar o motivo pelo qual voce usou a droga
        Bundle bundle = new Bundle();
        bundle.putBooleanArray("checkbox", array);
        Intent i = new Intent(this, Pergunta6.class);
        i.putExtras(bundle);
        startActivity(i);
        //slide from right to left
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }
    public void motivoBriguei(View v){
        //adicionar o motivo pelo qual voce usou a droga
        Bundle bundle = new Bundle();
        bundle.putBooleanArray("checkbox", array);
        Intent i = new Intent(this, Pergunta6.class);
        i.putExtras(bundle);
        startActivity(i);
        //slide from right to left
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }
}
