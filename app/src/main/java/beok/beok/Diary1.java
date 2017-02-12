package beok.beok;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.orm.SugarContext;

import beok.beok.api.Conf;
import beok.beok.api.DB;

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

        SugarContext.init(this);

        String usuario; //Nome do usuario (banco de dados)

        txtusuario.setText("Olá "+ Conf.getNomeUsuario()); //Adicionar o usuario no texto
    }

    public void botaoSim(View v){
        Intent i = new Intent(this, UsouDroga.class);
        startActivity(i);
        //slide from right to left
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }
    public void botaoNao(View v){
        Intent i = new Intent(this, NaoUsouDroga.class);
        startActivity(i);
        //slide from right to left
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }

}
