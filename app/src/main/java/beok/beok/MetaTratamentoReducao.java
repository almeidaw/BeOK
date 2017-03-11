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

import com.google.gson.Gson;
import com.orm.SugarContext;

import beok.beok.POJO.MetaGeral;
import beok.beok.api.DB;

public class MetaTratamentoReducao extends AppCompatActivity {

    boolean[] array;

    Button btproximo;
    CheckBox cbmanha, cbtarde, cbnoite, cbmadrugada;
    SeekBar sbqtd;
    TextView txtlegenda, txtqtd;
    Spinner sp1, sp2, spbebidas;
    ImageView ivbebidas;

    MetaGeral meta;

    Bundle bundle;
    Bundle newBundle;
    int freqSemanal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meta_tratamento_reducao);

        meta = new MetaGeral();

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

        bundle = getIntent().getExtras();
        newBundle = new Bundle();

        freqSemanal = bundle.getInt("freqSemanal");

        if (bundle.getInt("Droga escolhida") == 1) {
            spbebidas.setVisibility(View.VISIBLE);
            ivbebidas.setVisibility(View.VISIBLE);
            txtlegenda.setVisibility(View.GONE);
            sbqtd.setMax(14);
            sbqtd.setProgress(6);
            txtqtd.setText("7 doses");
            meta.setTipo(0);
            txtlegenda.setText("Uma dose é igual a");

        } else if (bundle.getInt("Droga escolhida") == 2) {
            txtlegenda.setText(getResources().getString(R.string.peso_baseado));
            sbqtd.setProgress(9);
            txtqtd.setText("10 baseados");
            meta.setTipo(3);

        } else if (bundle.getInt("Droga escolhida") == 3) {
            txtlegenda.setText(getResources().getString(R.string.peso_pedra));
            sbqtd.setProgress(9);
            txtqtd.setText("10 gramas");
            meta.setTipo(4);

        } else if (bundle.getInt("Droga escolhida") == 4) {
            txtlegenda.setVisibility(View.GONE);
            sbqtd.setProgress(6);
            txtqtd.setText("7 pedras");
            meta.setTipo(5);
        }

        for(int i=0;i<6;i++){
            String str=bundle.getString("tipoDroga"+i);
            if(str!=null){
                newBundle.putString("tipoDroga"+i,str);
            }
            str=bundle.getString("metaTipo"+i);
            if(str!=null){
                newBundle.putString("metaTipo"+i,str);
            }
        }

        sbqtd.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (bundle.getInt("Droga escolhida") == 1) {
                    sbqtd.setMax(14);
                    txtqtd.setText(Integer.toString(progress + 1) + " doses");
                    meta.setTipo(spbebidas.getSelectedItemPosition());
                    meta.setQuantidade(progress + 1);
                } else if (bundle.getInt("Droga escolhida") == 2) {
                    sbqtd.setMax(29);
                    txtqtd.setText(Float.toString(((float) progress + 1) / 2) + " baseados");
                    meta.setTipo(3);
                    meta.setQuantidade(progress + 1);
                } else if (bundle.getInt("Droga escolhida") == 3) {
                    sbqtd.setMax(19);
                    txtqtd.setText(Float.toString(((float) progress + 1) / 2) + " gramas");
                    meta.setTipo(4);
                    meta.setQuantidade(progress + 1);
                } else if (bundle.getInt("Droga escolhida") == 4) {
                    sbqtd.setMax(14);
                    txtqtd.setText(Integer.toString(progress + 1) + " pedras");
                    meta.setTipo(5);
                    meta.setQuantidade(progress + 1);
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
    }



    public void botaoProximo(View v){
        newBundle.putBooleanArray("checkbox", array);

        meta.setManha(cbmanha.isChecked());
        meta.setTarde(cbtarde.isChecked());
        meta.setNoite(cbnoite.isChecked());
        meta.setMadrugada(cbmadrugada.isChecked());
        meta.setFreqSemanal(freqSemanal);

        Gson g = new Gson();
        newBundle.putString("metaTipo"+meta.getTipo(),g.toJson(meta));

        Intent nextActivity = new Intent(this, Tela2.class);
        nextActivity.putExtras(newBundle);
        startActivity(nextActivity);
        //slide from left to right
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);

    }
    @Override
    public void onBackPressed() {
        Intent i=new Intent(this,Tela2.class);
        array[bundle.getInt("Droga escolhida")-1]=false;
        newBundle.putBooleanArray("checkbox", array);
        i.putExtras(newBundle);
        startActivity(i);
    }
}
