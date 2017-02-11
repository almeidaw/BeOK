package beok.beok;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

public class AlternativaUso extends AppCompatActivity {

    Button btprox;
    CheckBox cbalegria, cbsolidao, cbexcitacao, cbtedio, cbfrustracao, cbraiva, cbcomemorar, cbprobfamilia,
            cbprobtrabesc, cbrelaxar, cboutrosmotivos,
            cbliguei, cbpessoalmente1, cbpessoalmente2, cbauto_ajuda, cbbanho, cbcomi, cbpessoasqueridas, cboutrasalternativas;
    EditText edtxtoutrosmotivos, edtxtoutrasalternativas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alternativa_uso);

        cbalegria = (CheckBox) findViewById(R.id.cbalegria);
        cbsolidao = (CheckBox) findViewById(R.id.cbsolidao);
        cbexcitacao = (CheckBox) findViewById(R.id.cbexcitacao);
        cbtedio = (CheckBox) findViewById(R.id.cbtedio);
        cbfrustracao = (CheckBox) findViewById(R.id.cbfrustracao);
        cbraiva = (CheckBox) findViewById(R.id.cbraiva);
        cbcomemorar = (CheckBox) findViewById(R.id.cbcomemorar);
        cbprobfamilia = (CheckBox) findViewById(R.id.cbprobfamilia);
        cbprobtrabesc = (CheckBox) findViewById(R.id.cbprobtrabesc);
        cbrelaxar = (CheckBox) findViewById(R.id.cbrelaxar);
        cboutrosmotivos = (CheckBox) findViewById(R.id.cboutrosmotivos);
        cbliguei = (CheckBox) findViewById(R.id.cbliguei);
        cbpessoalmente1 = (CheckBox) findViewById(R.id.cbpessoalmente1);
        cbpessoalmente2 = (CheckBox) findViewById(R.id.cbpessoalmente2);
        //cbauto_ajuda = (CheckBox) findViewById(R.id.cbauto_ajuda);
        cbbanho = (CheckBox) findViewById(R.id.cbbanho);
        cbcomi = (CheckBox) findViewById(R.id.cbcomi);
        cbpessoasqueridas = (CheckBox) findViewById(R.id.cbpessoasqueridas);
        cboutrasalternativas = (CheckBox) findViewById(R.id.cboutrasalternativas);

        edtxtoutrosmotivos = (EditText) findViewById(R.id.edtxtoutrosmotivos);
        edtxtoutrasalternativas = (EditText) findViewById(R.id.edtxtoutrasalternativas);

        btprox = (Button) findViewById(R.id.btprox);

    }

    public void botaoProximo(View v){
        Intent i = new Intent(this, Fim.class);
        startActivity(i);
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
