package beok.beok;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.google.gson.Gson;

import java.util.Date;

import beok.beok.POJO.BotaoAtivo;

public class BotaoPanico1 extends AppCompatActivity {

    Button btfissura, btusei;
    BotaoAtivo bta;
    Bundle b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_botao_panico1);

        btfissura = (Button) findViewById(R.id.btfissura);
        btusei = (Button) findViewById(R.id.btusei);

        bta=new BotaoAtivo();

        Date now=new Date();
        now.setTime(System.currentTimeMillis());
        bta.setDataAtivo(now);



    }

    public void botaoFissura(View v){
        bta.setMotivo(false);
        b=new Bundle();
        Gson g=new Gson();
        b.putString("bta",g.toJson(bta));
        Bundle bundle = new Bundle();
        bundle.putInt("usou_ou_fissura", 1);
        Intent i = new Intent(this, BotaoPanico2.class);
        i.putExtras(b);
        i.putExtras(bundle);
        startActivity(i);
    }
    public void botaoUsei(View v){
        bta.setMotivo(true);
        b=new Bundle();
        Gson g=new Gson();
        b.putString("bta",g.toJson(bta));
        Bundle bundle = new Bundle();
        bundle.putInt("usou_ou_fissura", 2);
        Intent i = new Intent(this, BotaoPanico2.class);
        i.putExtras(b);
        i.putExtras(bundle);
        startActivity(i);
    }

}
