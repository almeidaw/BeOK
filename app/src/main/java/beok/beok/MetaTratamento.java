package beok.beok;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;

import com.orm.SugarContext;

import beok.beok.POJO.MetaGeral;
import beok.beok.api.DB;

/**
 * Created by arthur on 01/03/2017.
 */

public class MetaTratamento extends AppCompatActivity {
    Button buttonNext;
    Spinner metaQual, metaFreq;

    MetaGeral meta;
    boolean querAbstinencia;
    boolean[] array;
    Bundle bundle;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meta_tratamento);

        meta = new MetaGeral();

        SugarContext.init(this);

        buttonNext = (Button) findViewById(R.id.btproximo);
        metaQual = (Spinner) findViewById(R.id.sp1);
        metaFreq = (Spinner) findViewById(R.id.sp2);

        bundle = getIntent().getExtras();

        array = bundle.getBooleanArray("checkbox");

        if (bundle.getInt("Droga escolhida") == 1) {
            meta.setTipo(0);
        } else if (bundle.getInt("Droga escolhida") == 2) {
            meta.setTipo(3);

        } else if (bundle.getInt("Droga escolhida") == 3) {
            meta.setTipo(4);

        } else if (bundle.getInt("Droga escolhida") == 4) {
            meta.setTipo(5);
        }


        metaQual.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (metaQual.getItemAtPosition(position).equals("Reduzir o uso")) {
                    querAbstinencia = false;
                    metaFreq.setVisibility(View.VISIBLE);
                } else {
                    metaFreq.setVisibility(View.GONE);
                    querAbstinencia = true;
                    meta.setFreqSemanal(0);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        metaFreq.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                meta.setFreqSemanal(position + 1);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void botaoProximo(View v){
        Intent intent;
        Bundle tempBundle = new Bundle();
        tempBundle.putBooleanArray("checkbox", array);
        if(querAbstinencia){
            intent  = new Intent (this, Tela2.class);
            meta.setFreqSemanal(0);
            meta.setMadrugada(false);
            meta.setManha(false);
            meta.setTarde(false);
            meta.setTarde(false);
            meta.setQuantidade(0);

            DB.save(meta);
        }
        else{
            intent =  new Intent (this, MetaTratamentoReducao.class);

            tempBundle.putInt("Droga escolhida", this.bundle.getInt("DrogaEscolhida"));
            int freqSemanal = this.metaFreq.getSelectedItemPosition();
            tempBundle.putInt("freqSemanal", freqSemanal);

        }
        intent.putExtras(tempBundle);

        startActivity(intent);
    }
}
