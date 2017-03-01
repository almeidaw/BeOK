package beok.beok;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.EditText;

import com.google.gson.Gson;
import com.orm.SugarContext;

import java.util.Date;

import beok.beok.POJO.RelatoDiario;
import beok.beok.POJO.VontadeDroga;
import beok.beok.api.DB;

public class AlternativaUso extends AppCompatActivity {


    Button btprox;
    RadioButton cbalegria, cbsolidao, cbexcitacao, cbtedio, cbfrustracao, cbraiva, cbcomemorar, cbprobfamilia,
            cbprobtrabesc, cbrelaxar, cboutrosmotivos,
            cbliguei, cbpessoalmente1, cbpessoalmente2, cbauto_ajuda, cbbanho, cbcomi, cbpessoasqueridas, cboutrasalternativas;
    EditText edtxtoutrosmotivos, edtxtoutrasalternativas;

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


        cbalegria = (RadioButton) findViewById(R.id.cbalegria);
        cbsolidao = (RadioButton) findViewById(R.id.cbsolidao);
        cbexcitacao = (RadioButton) findViewById(R.id.cbexcitacao);
        cbtedio = (RadioButton) findViewById(R.id.cbtedio);
        cbfrustracao = (RadioButton) findViewById(R.id.cbfrustracao);
        cbraiva = (RadioButton) findViewById(R.id.cbraiva);
        cbcomemorar = (RadioButton) findViewById(R.id.cbcomemorar);
        cbprobfamilia = (RadioButton) findViewById(R.id.cbprobfamilia);
        cbprobtrabesc = (RadioButton) findViewById(R.id.cbprobtrabesc);
        cbrelaxar = (RadioButton) findViewById(R.id.cbrelaxar);
        cboutrosmotivos = (RadioButton) findViewById(R.id.cboutrosmotivos);
        cbliguei = (RadioButton) findViewById(R.id.cbliguei);
        cbpessoalmente1 = (RadioButton) findViewById(R.id.cbpessoalmente1);
        cbpessoalmente2 = (RadioButton) findViewById(R.id.cbpessoalmente2);
        cbauto_ajuda = (RadioButton) findViewById(R.id.cbauto_ajuda);
        cbbanho = (RadioButton) findViewById(R.id.cbbanho);
        cbcomi = (RadioButton) findViewById(R.id.cbcomi);
        cbpessoasqueridas = (RadioButton) findViewById(R.id.cbpessoasqueridas);
        cboutrasalternativas = (RadioButton) findViewById(R.id.cboutrasalternativas);

        edtxtoutrosmotivos = (EditText) findViewById(R.id.edtxtoutrosmotivos);
        edtxtoutrasalternativas = (EditText) findViewById(R.id.edtxtoutrasalternativas);

        btprox = (Button) findViewById(R.id.btprox);

    }

    public void botaoProximo(View v){
        int m=0;
        m+=0*rb(cbalegria);
        m+=1*rb(cbexcitacao);
        m+=2*rb(cbsolidao);
        m+=3*rb(cbtedio);
        m+=4*rb(cbfrustracao);
        m+=5*rb(cbraiva);
        m+=6*rb(cbcomemorar);
        m+=7*rb(cbprobfamilia);
        m+=8*rb(cbprobtrabesc);
        m+=9*rb(cbrelaxar);
        m+=10*rb(cboutrosmotivos);
        vd.setMotivo(m);
        if(m==10){
            vd.setMotivoOutros(edtxtoutrosmotivos.getText().toString());
        }

        int a=0;
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

    private int rb(RadioButton r){
        if(r.isChecked()){
            return 1;
        }else{
            return 0;
        }
    }


    public void outrosMotivos(View v){
        if (cboutrosmotivos.isChecked()){
            edtxtoutrosmotivos.setVisibility(View.VISIBLE);
        }else {
            edtxtoutrosmotivos.setVisibility(View.INVISIBLE);
        }
    }

    public void outrasAlternativas(View v){
        if (cboutrasalternativas.isChecked()){
            edtxtoutrasalternativas.setVisibility(View.VISIBLE);
        }else {
            edtxtoutrasalternativas.setVisibility(View.INVISIBLE);
        }
    }

}
