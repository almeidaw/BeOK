package beok.beok;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Diary1 extends AppCompatActivity {

    Button btsim, btnao;
    TextView txtusuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary_1);

        btnao = (Button) findViewById(R.id.btnao);
        btsim = (Button) findViewById(R.id.btsim);
        txtusuario = (TextView) findViewById(R.id.txtusuario);

        String usuario; //Nome do usuario (banco de dados)

        txtusuario.setText("Olá "); //Adicionar o usuario no texto
    }

    public void botaoSim(View v){
        Intent i = new Intent(this, Diary2.class);
        startActivity(i);
    }
    public void botaoNao(View v){
        Intent i = new Intent(this, Diary4.class);
        startActivity(i);
    }


}
