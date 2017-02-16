package beok.beok;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.orm.SugarContext;

import java.util.Date;

import beok.beok.POJO.MetaSemGeral;
import beok.beok.api.DB;

public class MetaSemanalGeral extends AppCompatActivity {

    MetaSemGeral msg;
    TextView txtmensagem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meta_semanal_geral);
        txtmensagem = (TextView)findViewById(R.id.editTextMetaGeral) ;
        msg=new MetaSemGeral();
        SugarContext.init(this);
    }
    public void clicaFinaliza(View v){
        Date dtnow=new Date();
        dtnow.setTime(System.currentTimeMillis());
        msg.setDataMeta(dtnow);
        msg.setTexto(txtmensagem.getText().toString());
        DB.save(msg);
        Intent i = new Intent(this,Main.class);
        startActivity(i);
    }
}
