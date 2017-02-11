package beok.beok;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Pergunta2 extends AppCompatActivity {

    boolean[] array;

    Button btprox2, btpularpergunta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pergunta2);

        btprox2 = (Button) findViewById(R.id.btprox2);
        btpularpergunta = (Button) findViewById(R.id.btpularpergunta);

        Bundle bundle = getIntent().getExtras();
        array = bundle.getBooleanArray("checkbox");

    }

    public void botaoProximo2(View v){
        //adicionar as coordenadas do local em que usou
        Bundle bundle = new Bundle();
        bundle.putBooleanArray("checkbox", array);
        Intent i = new Intent(this, Pergunta3.class);
        i.putExtras(bundle);
        startActivity(i);
        //slide from right to left
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }


    public void pularPergunta(View v){
        Bundle bundle = new Bundle();
        bundle.putBooleanArray("checkbox", array);
        Intent i = new Intent(this, Pergunta3.class);
        i.putExtras(bundle);
        startActivity(i);
        //slide from right to left
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }

}
