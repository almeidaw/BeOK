package beok.beok;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Pergunta3 extends AppCompatActivity {

    boolean[] array;

    Button btamigos, btparceiro, btfamiliares, btsozinho, btdesconhecidos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pergunta3);

        btamigos = (Button) findViewById(R.id.btamigos);
        btparceiro = (Button) findViewById(R.id.btparceiro);
        btfamiliares = (Button) findViewById(R.id.btfamiliares);
        btsozinho = (Button) findViewById(R.id.btsozinho);
        btdesconhecidos = (Button) findViewById(R.id.btdesconhecidos);

        Bundle bundle = getIntent().getExtras();
        array = bundle.getBooleanArray("checkbox");

    }

    public void usouComAmigos(View v){
        //adicionar "com quem" ele usou a droga
        Bundle bundle = new Bundle();
        bundle.putBooleanArray("checkbox", array);
        Intent i = new Intent(this, Pergunta4.class);
        i.putExtras(bundle);
        startActivity(i);
        //slide from right to left
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }
    public void usouComDesconhecidos(View v){
        //adicionar "com quem" ele usou a droga
        Bundle bundle = new Bundle();
        bundle.putBooleanArray("checkbox", array);
        Intent i = new Intent(this, Pergunta4.class);
        i.putExtras(bundle);
        startActivity(i);
        //slide from right to left
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }
    public void usouComParceiro(View v){
        //adicionar "com quem" ele usou a droga
        Bundle bundle = new Bundle();
        bundle.putBooleanArray("checkbox", array);
        Intent i = new Intent(this, Pergunta4.class);
        i.putExtras(bundle);
        startActivity(i);
        //slide from right to left
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }
    public void usouSozinho(View v){
        //adicionar "com quem" ele usou a droga
        Bundle bundle = new Bundle();
        bundle.putBooleanArray("checkbox", array);
        Intent i = new Intent(this, Pergunta4.class);
        i.putExtras(bundle);
        startActivity(i);
        //slide from right to left
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }
    public void usouComFamiliares(View v){
        //adicionar "com quem" ele usou a droga
        Bundle bundle = new Bundle();
        bundle.putBooleanArray("checkbox", array);
        Intent i = new Intent(this, Pergunta4.class);
        i.putExtras(bundle);
        startActivity(i);
        //slide from right to left
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }
}
