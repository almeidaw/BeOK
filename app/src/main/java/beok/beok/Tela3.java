package beok.beok;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.orm.SugarContext;
import com.orm.SugarRecord;

import java.util.List;

import beok.beok.POJO.Usuario;
import beok.beok.api.DB;

public class Tela3 extends AppCompatActivity implements View.OnClickListener{

    Button btproximo3;
    CheckBox cbfamilia, cbdinheiro, cbtrabalho, cbestudos, cbsfisica, cbsemocional, cboutros;
    EditText edtxtoutramot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela3);

        SugarContext.init(this);

        btproximo3 = (Button) findViewById(R.id.btproximo3);

        cbfamilia = (CheckBox) findViewById(R.id.cbfamilia);
        cbdinheiro = (CheckBox) findViewById(R.id.cbdinheiro);
        cbsemocional = (CheckBox) findViewById(R.id.cbsemocional);
        cbsfisica = (CheckBox) findViewById(R.id.cbsfisica);
        cbtrabalho = (CheckBox) findViewById(R.id.cbtrabalho);
        cbestudos = (CheckBox) findViewById(R.id.cbestudos);
        cboutros = (CheckBox) findViewById(R.id.cboutros);

        edtxtoutramot = (EditText) findViewById(R.id.edtxtoutramot);

        btproximo3.setOnClickListener(this);
    }

    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btproximo3:
                List<Usuario> us = SugarRecord.listAll(Usuario.class);
                Usuario u=us.get(0);
                int motiv=0;
                motiv+=ch(cbfamilia);
                motiv+=ch(cbdinheiro)*2;
                motiv+=ch(cbsfisica)*4;
                motiv+=ch(cbsemocional)*8;
                motiv+=ch(cbtrabalho)*16;
                motiv+=ch(cbestudos)*32;
                motiv+=ch(cboutros)*64;

                u.setMotivacao(motiv);
                if(cboutros.isChecked()) {
                    u.setMotivOutros(edtxtoutramot.getText().toString());
                }
                DB.save(u);
                Intent nextActivity = new Intent(this, Tela4.class);
                startActivity(nextActivity);
                //slide from right to left
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                break;
        }
    }

    private int ch(CheckBox c){
        if(c.isChecked()){
            return 1;
        }else{
            return 0;
        }
    }

    public void outrasMotivacoes(View v){
        if (cboutros.isChecked()){
            edtxtoutramot.setVisibility(View.VISIBLE);
        }else {
            edtxtoutramot.setVisibility(View.GONE);
        }
    }
    @Override
    public void onBackPressed() {

    }
}
