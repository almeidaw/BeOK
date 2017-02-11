package beok.beok;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Pergunta7 extends AppCompatActivity {

    boolean[] array;

    Button btsim, btnao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pergunta7);

        btnao = (Button) findViewById(R.id.btnao);
        btsim = (Button) findViewById(R.id.btsim);

        Bundle bundle = getIntent().getExtras();
        array = bundle.getBooleanArray("checkbox");

    }

    public void botaoSim(View v){
        Bundle bundle = new Bundle();
        bundle.putBooleanArray("checkbox", array);
        Intent i = new Intent(this, UsouDroga.class);
        i.putExtras(bundle);
        startActivity(i);
        //slide from left to right
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }
    public void botaoNao(View v){
        //Registra no diario
        Intent i = new Intent(this, Fim.class);
        startActivity(i);
        //slide from right to left
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }
}
