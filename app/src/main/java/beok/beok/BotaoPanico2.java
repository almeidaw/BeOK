package beok.beok;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.List;
import java.util.Random;

import beok.beok.POJO.BotaoAtivo;
import beok.beok.POJO.ContatoEmergencia;
import beok.beok.api.Conf;
import beok.beok.api.DB;

public class BotaoPanico2 extends AppCompatActivity {

    String telefone;

    String tel_prof;
    String tel_1, contato1;
    String tel_2, contato2;
    String tel_3, contato3;
    boolean tel_2e;
    boolean tel_3e;

    TextView txtmsgmotivacional;

    Button  btcontato1, btcontato2, btcontato3;
    Bundle b;
    BotaoAtivo bta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_botao_panico2);

        btcontato1 = (Button) findViewById(R.id.btcontato1);
        btcontato2 = (Button) findViewById(R.id.btcontato2);
        btcontato3 = (Button) findViewById(R.id.btcontato3);

        txtmsgmotivacional = (TextView) findViewById(R.id.txtmsgmotivacional);
        tel_2e=false;
        tel_3e=false;

        SharedPreferences SP = PreferenceManager.getDefaultSharedPreferences(this);

        contato1 = SP.getString("contact1Name","");
        contato2 = SP.getString("contact2Name","");
        contato3 = SP.getString("contact3Name","");

        String ligar1 = ("Ligar para "+contato1);
        String ligar2 = ("Ligar para "+contato2);
        String ligar3 = ("Ligar para "+contato3);

        btcontato1.setText(ligar1);
        btcontato2.setText(ligar2);
        btcontato3.setText(ligar3);


        tel_1=SP.getString("contact1Number","");
        tel_2=SP.getString("contact2Number","");
        tel_3=SP.getString("contact2Number","");

        if(!contato2.equals(""))
            tel_2e=true;
        if(!contato3.equals(""))
            tel_3e=true;

        if(!tel_2e)
            btcontato2.setVisibility(View.GONE);
        if(!tel_3e)
            btcontato3.setVisibility(View.GONE);

        Random r = new Random();
        Resources res = getResources();
        String[] msg = res.getStringArray(R.array.msg_continue_tentando);
        String mensagem = String.format(msg[r.nextInt(9)], Conf.getNomeUsuario());
        txtmsgmotivacional.setText(mensagem);

        b=getIntent().getExtras();
        Gson g=new Gson();
        bta=g.fromJson(b.getString("bta"),BotaoAtivo.class);

    }

    public void ligarProfissional(View v){
        bta.setOQueFez(0);
        DB.save(bta);
        Uri uri = Uri.parse("tel:"+tel_prof); // "telefone" sera o contato salvo de algum profissional
        Intent i = new Intent(Intent.ACTION_DIAL, uri);
        startActivity(i);
    }
    public void mostrarDicasFrases(View v){
        bta.setOQueFez(1);
        DB.save(bta);
        Bundle bundle = new Bundle();
        bundle.putInt("inicia_insp", 1);
        Intent i = new Intent(this,  Fim.class); // Direcionar para tela de dicas efrases motivacionais
        i.putExtras(bundle);
        startActivity(i);
    }

    public void mostrarGruposAnonimos(View v){
        bta.setOQueFez(2);
        DB.save(bta);
        Intent i = new Intent(this,  Fim.class); // Direcionar para tela de contatos GRUPOS DE ANONIMOS
        startActivity(i);
    }
    public void ligarContato1(View v){
        bta.setOQueFez(3);
        DB.save(bta);
        Uri uri = Uri.parse("tel:"+tel_1); // "telefone" sera o numero do contato1
        Intent i = new Intent(Intent.ACTION_DIAL, uri);
        startActivity(i);
    }
    public void ligarContato2(View v){
        bta.setOQueFez(3);
        DB.save(bta);
        Uri uri = Uri.parse("tel:"+tel_2); // "telefone" sera o numero do contato2
        Intent i = new Intent(Intent.ACTION_DIAL, uri);
        startActivity(i);
    }
    public void ligarContato3(View v){
        bta.setOQueFez(3);
        DB.save(bta);
        Uri uri = Uri.parse("tel:"+tel_3); // "telefone" sera o numero do contato3
        Intent i = new Intent(Intent.ACTION_DIAL, uri);
        startActivity(i);
    }
}
