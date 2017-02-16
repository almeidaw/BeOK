package beok.beok;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.orm.SugarContext;

import beok.beok.POJO.MetaGeral;
import beok.beok.api.DB;

public class MetaTratamento extends AppCompatActivity {

    boolean[] array;

    Button btproximo;
    CheckBox cbmanha, cbtarde, cbnoite, cbmadrugada;
    SeekBar sbqtd;
    TextView txtlegenda, txtqtd;
    Spinner sp1, sp2, spbebidas;
    ImageView ivbebidas;

    MetaGeral meta;

    Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meta_tratamento);

        meta=new MetaGeral();

        SugarContext.init(this);

        btproximo = (Button) findViewById(R.id.btproximo);
        cbmanha = (CheckBox) findViewById(R.id.cbmanha);
        cbtarde = (CheckBox) findViewById(R.id.cbtarde);
        cbnoite = (CheckBox) findViewById(R.id.cbnoite);
        cbmadrugada = (CheckBox) findViewById(R.id.cbmadrugada);
        sbqtd = (SeekBar) findViewById(R.id.sbqtd);
        txtlegenda = (TextView) findViewById(R.id.txtlegenda);
        txtqtd = (TextView) findViewById(R.id.txtqtd);
        sp1 = (Spinner) findViewById(R.id.sp1);
        sp2 = (Spinner) findViewById(R.id.sp2);
        spbebidas = (Spinner) findViewById(R.id.spbebidas);
        ivbebidas = (ImageView) findViewById(R.id.ivbebidas);

        bundle=getIntent().getExtras();

        if (bundle.getInt("Droga escolhida") == 1){
            spbebidas.setVisibility(View.VISIBLE);
            ivbebidas.setVisibility(View.VISIBLE);
            txtlegenda.setVisibility(View.GONE);
        }else if (bundle.getInt("Droga escolhida") == 2){
            txtlegenda.setText("1 baseado: fino=0,5 grama, normal = 1,2 gramas, bomba= 2 gramas");

        }else if (bundle.getInt("Droga escolhida") == 3){
            txtlegenda.setText("1 papelote/pino= 1grama");

        }else if (bundle.getInt("Droga escolhida") == 4){
            txtlegenda.setVisibility(View.GONE);
        }


        sbqtd.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (bundle.getInt("Droga escolhida") == 1){
                    sbqtd.setMax(14);
                    txtqtd.setText(Integer.toString(progress + 1) + " doses de " + spbebidas.getSelectedItem().toString());
                    meta.setTipo(spbebidas.getSelectedItemPosition());
                    meta.setQuantidade(progress+1);
                }else if (bundle.getInt("Droga escolhida") == 2){
                    sbqtd.setMax(29);
                    txtqtd.setText(Float.toString(((float)progress + 1)/2) + " baseados de maconha");
                    meta.setTipo(3);
                    meta.setQuantidade(progress+1);
                }else if (bundle.getInt("Droga escolhida") == 3){
                    sbqtd.setMax(19);
                    txtqtd.setText(Float.toString(((float)progress + 1)/2) + " gramas cocaina");
                    meta.setTipo(4);
                    meta.setQuantidade(progress+1);
                }else if (bundle.getInt("Droga escolhida") == 4){
                    sbqtd.setMax(14);
                    txtqtd.setText(Integer.toString(progress + 1) + " pedras de crack");
                    meta.setTipo(5);
                    meta.setQuantidade(progress+1);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        array = bundle.getBooleanArray("checkbox");

        sp1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (sp1.getItemAtPosition(position).equals("Reduzir o uso")){
                    sp2.setVisibility(View.VISIBLE);
                } else{
                    sp2.setVisibility(View.GONE);
                    meta.setFreqSemanal(0);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        sp2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                meta.setFreqSemanal(position+1);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }


    public void botaoProximo(View v){
        Bundle bundle = new Bundle();
        bundle.putBooleanArray("checkbox", array);

        meta.setManha(cbmanha.isChecked());
        meta.setTarde(cbtarde.isChecked());
        meta.setNoite(cbnoite.isChecked());
        meta.setMadrugada(cbmadrugada.isChecked());

        DB.save(meta);

        Intent nextActivity = new Intent(this, Tela2.class);
        nextActivity.putExtras(bundle);
        startActivity(nextActivity);
        //slide from left to right
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);

    }
}
