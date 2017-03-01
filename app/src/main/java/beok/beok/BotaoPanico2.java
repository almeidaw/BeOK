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

import java.util.Random;

import beok.beok.api.Conf;

public class BotaoPanico2 extends AppCompatActivity {

    String telefone;

    String tel_prof;
    String tel_1;
    String tel_2;
    String tel_3;
    boolean tel_2e;
    boolean tel_3e;

    TextView txtmsgmotivacional;

    Button bt2,bt3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_botao_panico2);

        bt2=(Button)findViewById(R.id.btcontato2);
        bt3=(Button)findViewById(R.id.btcontato3);

        txtmsgmotivacional = (TextView) findViewById(R.id.txtmsgmotivacional);
        tel_2e=false;
        tel_3e=false;
        SharedPreferences SP = PreferenceManager.getDefaultSharedPreferences(this);
        tel_1=SP.getString("contact1Number","");
        tel_2=SP.getString("contact2Number","");
        tel_3=SP.getString("contact2Number","");

        if(!tel_2.equals(""))
            tel_2e=true;
        if(!tel_3.equals(""))
            tel_3e=true;

        if(!tel_2e)
            bt2.setVisibility(View.GONE);
        if(!tel_3e)
            bt3.setVisibility(View.GONE);

        Random r = new Random();
        Resources res = getResources();
        String[] msg = res.getStringArray(R.array.msg_continue_tentando);
        String mensagem = String.format(msg[r.nextInt(9)], Conf.getNomeUsuario());
        txtmsgmotivacional.setText(mensagem);

    }

    public void ligarProfissional(View v){
        Uri uri = Uri.parse("tel:"+tel_prof); // "telefone" sera o contato salvo de algum profissional
        Intent i = new Intent(Intent.ACTION_DIAL, uri);
        startActivity(i);
    }
    public void mostrarFotos(View v){
        Intent i = new Intent(this, Fim.class); // Direcionar para tela de fotos cadastradas
        startActivity(i);
    }
    public void mostrarDicasFrases(View v){
        Intent i = new Intent(this,  Fim.class); // Direcionar para tela de dicas efrases motivacionais
        startActivity(i);
    }
    public void mostrarGruposAnonimos(View v){
        Intent i = new Intent(this,  Fim.class); // Direcionar para tela de contatos GRUPOS DE ANONIMOS
        startActivity(i);
    }
    public void ligarContato1(View v){
        Uri uri = Uri.parse("tel:"+tel_1); // "telefone" sera o numero do contato1
        Intent i = new Intent(Intent.ACTION_DIAL, uri);
        startActivity(i);

    }
    public void ligarContato2(View v){
        Uri uri = Uri.parse("tel:"+tel_2); // "telefone" sera o numero do contato2
        Intent i = new Intent(Intent.ACTION_DIAL, uri);
        startActivity(i);
    }
    public void ligarContato3(View v){
        Uri uri = Uri.parse("tel:"+tel_3); // "telefone" sera o numero do contato3
        Intent i = new Intent(Intent.ACTION_DIAL, uri);
        startActivity(i);
    }
}
