package beok.beok;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.orm.SugarContext;

public class Pergunta5 extends AppCompatActivity {


    Button btsentiso, btestavafesta, btamigosusando, btbriguei;

    int motivouso;

    Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pergunta5);

        SugarContext.init(this);

        btsentiso = (Button) findViewById(R.id.btsentiso);
        btestavafesta = (Button) findViewById(R.id.btestavafesta);
        btamigosusando = (Button) findViewById(R.id.btamigosusando);
        btbriguei = (Button) findViewById(R.id.btbriguei);

        bundle = getIntent().getExtras();
    }

    public void motivoSentiSo(View v){
        //adicionar o motivo pelo qual voce usou a droga
        Intent i = new Intent(this, Pergunta6.class);
        bundle.putInt("motivouso",0);
        i.putExtras(bundle);
        startActivity(i);
        //slide from right to left
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }
    public void motivoEstavaFesta(View v){
        //adicionar o motivo pelo qual voce usou a droga
        Intent i = new Intent(this, Pergunta6.class);
        bundle.putInt("motivouso",1);
        i.putExtras(bundle);
        startActivity(i);        //slide from right to left
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }
    public void motivoAmigosUsando(View v){
        //adicionar o motivo pelo qual voce usou a droga
        Intent i = new Intent(this, Pergunta6.class);
        bundle.putInt("motivouso",2);
        i.putExtras(bundle);
        startActivity(i);
        //slide from right to left
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }
    public void motivoBriguei(View v){
        //adicionar o motivo pelo qual voce usou a droga
        Intent i = new Intent(this, Pergunta6.class);
        bundle.putInt("motivouso",3);
        i.putExtras(bundle);
        startActivity(i);
        //slide from right to left
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }
}
