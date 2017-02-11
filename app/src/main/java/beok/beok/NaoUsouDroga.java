package beok.beok;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class NaoUsouDroga extends AppCompatActivity {

    Button btsim, btnao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nao_usou_droga);

        btsim = (Button) findViewById(R.id.btsim);
        btnao = (Button) findViewById(R.id.btnao);

    }

    public void botaoSim(View v){
        Intent i = new Intent(this, VontadeUsa.class);
        startActivity(i);
    }

    public void botaoNao(View v){
        Intent i = new Intent(this, Fim.class);
        startActivity(i);
    }
}
