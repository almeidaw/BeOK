package beok.beok;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.orm.SugarContext;

public class Pergunta3 extends AppCompatActivity {


    Button btamigos, btparceiro, btfamiliares, btsozinho, btdesconhecidos;

    Bundle bundle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pergunta3);

        SugarContext.init(this);

        btamigos = (Button) findViewById(R.id.btamigos);
        btparceiro = (Button) findViewById(R.id.btparceiro);
        btfamiliares = (Button) findViewById(R.id.btfamiliares);
        btsozinho = (Button) findViewById(R.id.btsozinho);
        btdesconhecidos = (Button) findViewById(R.id.btdesconhecidos);

        bundle = getIntent().getExtras();

    }

    public void usouComAmigos(View v){
        //adicionar "com quem" ele usou a droga
        Intent i = new Intent(this, Pergunta5.class);
        bundle.putInt("usoucom",0);
        i.putExtras(bundle);
        startActivity(i);
        //slide from right to left
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }
    public void usouComDesconhecidos(View v){
        //adicionar "com quem" ele usou a droga
        Intent i = new Intent(this, Pergunta5.class);
        bundle.putInt("usoucom",1);
        i.putExtras(bundle);
        startActivity(i);
        //slide from right to left
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }
    public void usouComParceiro(View v){
        //adicionar "com quem" ele usou a droga
        Intent i = new Intent(this, Pergunta5.class);
        bundle.putInt("usoucom",2);
        i.putExtras(bundle);
        startActivity(i);
        //slide from right to left
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }
    public void usouSozinho(View v){
        //adicionar "com quem" ele usou a droga
        Intent i = new Intent(this, Pergunta5.class);
        bundle.putInt("usoucom",3);
        i.putExtras(bundle);
        startActivity(i);
        //slide from right to left
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }
    public void usouComFamiliares(View v){
        //adicionar "com quem" ele usou a droga
        Intent i = new Intent(this, Pergunta5.class);
        bundle.putInt("usoucom",4);
        i.putExtras(bundle);
        startActivity(i);
        //slide from right to left
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }
}
