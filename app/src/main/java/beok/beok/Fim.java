package beok.beok;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Random;

import beok.beok.api.Conf;
import beok.beok.api.DB;
import beok.beok.api.ServiceSincronizer;

public class Fim extends AppCompatActivity {

    TextView txtmsg;

    int period = 3000, se_usou;
    final Handler handler=new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fim);

        txtmsg = (TextView) findViewById(R.id.txtmsg);

        Bundle bundle = getIntent().getExtras();
        se_usou = bundle.getInt("se_usou");

        Random r = new Random();
        Resources res = getResources();

        if (se_usou==0){
            String[] msg = res.getStringArray(R.array.msg_continue_tentando);
            String mensagem = String.format(msg[r.nextInt(9)], Conf.getNomeUsuario()); //Inserir o nome do usuario segundo parametro
            txtmsg.setText(mensagem);
        }
        else if (se_usou==1){
            String[] msg = res.getStringArray(R.array.msg_nao_usou);
            String mensagem = String.format(msg[r.nextInt(5)], Conf.getNomeUsuario()); //Inserir o nome do usuario segundo parametro
            txtmsg.setText(mensagem);
        }




        handler.postDelayed(runnable, period);
        
    }


    private Runnable runnable = new Runnable() {

        @Override
        public void run() {
            end();
        }
    };

    private void end(){
        Intent i = new Intent(this, Main.class);
        startActivity(i);
    }
}
