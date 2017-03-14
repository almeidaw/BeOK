package beok.beok;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.EditText;
import android.widget.TableLayout;

import com.google.gson.Gson;
import com.orm.SugarContext;

import java.util.Date;

import beok.beok.POJO.RelatoDiario;
import beok.beok.POJO.VontadeDroga;
import beok.beok.api.DB;

public class AlternativaUso extends AppCompatActivity {


    Button btprox;

    CheckBox cbemocoes, cbsituacoes, cbestadofisico, cbproblemas, cbtestarcontrole, cboutros,
            cb1_1, cb1_2, cb1_3, cb1_4, cb1_5, cb1_6, cb1_7, cb1_8, cb1_9, cb1_10, cb1_11, cb1_12,
            cb1_13, cb1_14, cb2_1, cb2_2, cb2_3, cb2_4, cb3_1, cb3_2, cb3_3, cb3_4,
            cb3_5, cb3_6;
    TableLayout tlopcao1 , tlopcao2, tlopcao3;
    EditText edtxtoutros;

    RadioButton cbliguei, cbpessoalmente1, cbpessoalmente2, cbauto_ajuda, cbbanho, cbcomi, cbpessoasqueridas, cboutrasalternativas;
    EditText edtxtoutrosmotivos, edtxtoutrasalternativas;

    int motivouso;
    CheckBox[] checkBoxes = {cb1_1, cb1_2, cb1_3, cb1_4, cb1_5, cb1_6, cb1_7, cb1_8, cb1_9, cb1_10, cb1_11, cb1_12,
            cb1_13, cb1_14, cb2_1, cb2_2, cb2_3, cb2_4, cb3_1, cb3_2, cb3_3, cb3_4,
            cb3_5, cb3_6, cbproblemas, cbtestarcontrole, cboutros};

    Bundle bundle;
    VontadeDroga vd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alternativa_uso);

        bundle=getIntent().getExtras();
        Gson g=new Gson();
        vd = g.fromJson(bundle.getString("vd"),VontadeDroga.class);

        SugarContext.init(this);

        cbemocoes = (CheckBox) findViewById(R.id.cbemocoes);
        cbsituacoes = (CheckBox) findViewById(R.id.cbsituacoes);
        cbestadofisico = (CheckBox) findViewById(R.id.cbestadofisico);
        cbproblemas = (CheckBox) findViewById(R.id.cbproblemas);
        cbtestarcontrole = (CheckBox) findViewById(R.id.cbtestarcontrole);
        cboutros = (CheckBox) findViewById(R.id.cboutros);

        cb1_1 = (CheckBox) findViewById(R.id.cb1_1);
        cb1_2 = (CheckBox) findViewById(R.id.cb1_2);
        cb1_3 = (CheckBox) findViewById(R.id.cb1_3);
        cb1_4 = (CheckBox) findViewById(R.id.cb1_4);
        cb1_5 = (CheckBox) findViewById(R.id.cb1_5);
        cb1_6 = (CheckBox) findViewById(R.id.cb1_6);
        cb1_7 = (CheckBox) findViewById(R.id.cb1_7);
        cb1_8 = (CheckBox) findViewById(R.id.cb1_8);
        cb1_9 = (CheckBox) findViewById(R.id.cb1_9);
        cb1_10 = (CheckBox) findViewById(R.id.cb1_10);
        cb1_11 = (CheckBox) findViewById(R.id.cb1_11);
        cb1_12 = (CheckBox) findViewById(R.id.cb1_12);
        cb1_13 = (CheckBox) findViewById(R.id.cb1_13);
        cb1_14 = (CheckBox) findViewById(R.id.cb1_14);
        cb2_1 = (CheckBox) findViewById(R.id.cb2_1);
        cb2_2 = (CheckBox) findViewById(R.id.cb2_2);
        cb2_3 = (CheckBox) findViewById(R.id.cb2_3);
        cb2_4 = (CheckBox) findViewById(R.id.cb2_4);
        cb3_1 = (CheckBox) findViewById(R.id.cb3_1);
        cb3_2 = (CheckBox) findViewById(R.id.cb3_2);
        cb3_3 = (CheckBox) findViewById(R.id.cb3_3);
        cb3_4 = (CheckBox) findViewById(R.id.cb3_4);
        cb3_5 = (CheckBox) findViewById(R.id.cb3_5);
        cb3_6 = (CheckBox) findViewById(R.id.cb3_6);

        tlopcao1 = (TableLayout) findViewById(R.id.tlopcao1);
        tlopcao2 = (TableLayout) findViewById(R.id.tlopcao2);
        tlopcao3 = (TableLayout) findViewById(R.id.tlopcao3);

        edtxtoutros = (EditText) findViewById(R.id.edtxtoutros);

        cbliguei = (RadioButton) findViewById(R.id.cbliguei);
        cbpessoalmente1 = (RadioButton) findViewById(R.id.cbpessoalmente1);
        cbpessoalmente2 = (RadioButton) findViewById(R.id.cbpessoalmente2);
        cbauto_ajuda = (RadioButton) findViewById(R.id.cbauto_ajuda);
        cbbanho = (RadioButton) findViewById(R.id.cbbanho);
        cbcomi = (RadioButton) findViewById(R.id.cbcomi);
        cbpessoasqueridas = (RadioButton) findViewById(R.id.cbpessoasqueridas);
        cboutrasalternativas = (RadioButton) findViewById(R.id.cboutrasalternativas);

        edtxtoutrasalternativas = (EditText) findViewById(R.id.edtxtoutrasalternativas);

        btprox = (Button) findViewById(R.id.btprox);

    }

    public void botaoProximo(View v){
        int contador = 1;
        motivouso = 0;

        for (CheckBox cb : checkBoxes){
            if (cb == null){
                contador = contador*2;
            } else {
                motivouso = motivouso + ch(cb) * contador;
                contador = contador * 2;
            }
        }

        vd.setMotivo(contador);

        if (checkBoxes[26] != null) {
            vd.setMotivoOutros(edtxtoutros.getText().toString());
        }

        int a = 0;
        a+=0*rb(cbliguei);
        a+=1*rb(cbpessoalmente1);
        a+=2*rb(cbpessoalmente2);
        a+=3*rb(cbauto_ajuda);
        a+=4*rb(cbbanho);
        a+=5*rb(cbcomi);
        a+=6*rb(cbpessoasqueridas);
        a+=7*rb(cboutrasalternativas);

        vd.setFezLugar(a);

        if(a==7){
            vd.setFezLugarOutros(edtxtoutrasalternativas.getText().toString());
        }


        RelatoDiario rd=new RelatoDiario();

        Date dtnow=new Date();
        dtnow.setTime(System.currentTimeMillis());

        rd.setDataDiario(dtnow);
        rd.setUso(false);
        rd.setVontade(true);

        vd.setidRelatoDiario((int)DB.save(rd));

        DB.save(vd);

        Bundle bundle = new Bundle();
        bundle.putInt("se_usou",1);

        Intent i = new Intent(this, Fim.class);
        i.putExtras(bundle);
        startActivity(i);
    }

    public int ch(CheckBox checkBox){
        if (checkBox.isChecked()){
            return 1;
        } else {
            return 0;
        }
    }

    private int rb(RadioButton r){
        if(r.isChecked()){
            return 1;
        }else{
            return 0;
        }
    }

    public void outrasAlternativas(View v){
        if (cboutrasalternativas.isChecked()){
            edtxtoutrasalternativas.setVisibility(View.VISIBLE);
        }else {
            edtxtoutrasalternativas.setVisibility(View.INVISIBLE);
        }
    }

    public void checkEmocoes (View v){
        if (tlopcao1.getVisibility() == View.GONE){
            tlopcao1.setVisibility(View.VISIBLE);
        } else{
            tlopcao1.setVisibility(View.GONE);
        }
    }

    public void checkSituacoes (View v){
        if (tlopcao2.getVisibility() == View.GONE){
            tlopcao2.setVisibility(View.VISIBLE);
        } else{
            tlopcao2.setVisibility(View.GONE);
        }
    }

    public void checkEstadoFisico (View v){
        if (tlopcao3.getVisibility() == View.GONE){
            tlopcao3.setVisibility(View.VISIBLE);
        } else{
            tlopcao3.setVisibility(View.GONE);
        }
    }

    public void checkOutros (View v){
        if (edtxtoutros.getVisibility() == View.GONE){
            edtxtoutros.setVisibility(View.VISIBLE);
        } else {
            edtxtoutros.setVisibility(View.GONE);
        }
    }

}
