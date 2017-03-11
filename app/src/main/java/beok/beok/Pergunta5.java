package beok.beok;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TableLayout;

import com.orm.SugarContext;

import java.util.List;

public class Pergunta5 extends AppCompatActivity {


    Button btprox5;
    CheckBox cbemocoes, cbsituacoes, cbestadofisico, cbproblemas, cbtestarcontrole, cboutros,
            cb1_1, cb1_2, cb1_3, cb1_4, cb1_5, cb1_6, cb1_7, cb1_8, cb1_9, cb1_10, cb1_11, cb1_12,
            cb1_13, cb1_14, cb2_1, cb2_2, cb2_3, cb2_4, cb3_1, cb3_2, cb3_3, cb3_4,
            cb3_5, cb3_6;
    TableLayout tlopcao1 , tlopcao2, tlopcao3;
    EditText edtxtoutros;

    int motivouso;
    CheckBox[] checkBoxes = {cb1_1, cb1_2, cb1_3, cb1_4, cb1_5, cb1_6, cb1_7, cb1_8, cb1_9, cb1_10, cb1_11, cb1_12,
            cb1_13, cb1_14, cb2_1, cb2_2, cb2_3, cb2_4, cb3_1, cb3_2, cb3_3, cb3_4,
            cb3_5, cb3_6, cbproblemas, cbtestarcontrole, cboutros};

    Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pergunta5);

        motivouso = 0;

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

        btprox5 = (Button) findViewById(R.id.btprox5);

        bundle = getIntent().getExtras();
    }


    public void botaoProximo5 (View v){
        int contador = 1;

        for (CheckBox cb : checkBoxes){
            motivouso = motivouso + ch(cb)*contador;
            contador = contador*2;
        }

        Intent i = new Intent(this, Pergunta6.class);
        bundle.putInt("motivo", motivouso);
        bundle.putString("outroMotivo", edtxtoutros.getText().toString());
        i.putExtras(bundle);
        startActivity(i);
        //slide from right to left
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
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

    public int ch(CheckBox checkBox){
        if (checkBox.isChecked()){
            return 1;
        } else {
            return 0;
        }
    }

}
