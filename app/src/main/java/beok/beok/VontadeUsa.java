package beok.beok;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.SeekBar;
import android.widget.TextView;

import com.google.gson.Gson;

import beok.beok.POJO.VontadeDroga;
import beok.beok.api.DB;

public class VontadeUsa extends AppCompatActivity {

    CheckBox cbalcool, cbmaconha, cbcocaina, cbcrack,
            cbmanha, cbtarde, cbnoite, cbmadrugada;
    Button btprox;
    SeekBar sbvontade;
    TextView txtvontade;

    VontadeDroga  vd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vontade_usa);

        vd=new VontadeDroga();

        cbalcool = (CheckBox) findViewById(R.id.cbalcool);
        cbmaconha = (CheckBox) findViewById(R.id.cbmaconha);
        cbcocaina = (CheckBox) findViewById(R.id.cbcocaina);
        cbcrack = (CheckBox) findViewById(R.id.cbcrack);
        cbmanha = (CheckBox) findViewById(R.id.cbmanha);
        cbtarde = (CheckBox) findViewById(R.id.cbtarde);
        cbnoite = (CheckBox) findViewById(R.id.cbnoite);
        cbmadrugada = (CheckBox) findViewById(R.id.cbmadrugada);


        btprox = (Button) findViewById(R.id.btprox);

        btprox.setVisibility(View.INVISIBLE);

        sbvontade = (SeekBar) findViewById(R.id.sbvontade);

        txtvontade = (TextView) findViewById(R.id.txtvontade);
        sbvontade.setMax(9);
        sbvontade.setProgress(4);
        txtvontade.setText("5");
        sbvontade.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                txtvontade.setText((Integer.toString(progress + 1)));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }

    public void botaoProximo(View v){
        //Pegar todos os checkboxs selecionados e colocar no banco de dados
        vd.setManha(cbmanha.isChecked());
        vd.setTarde(cbtarde.isChecked());
        vd.setNoite(cbnoite.isChecked());
        vd.setMadrugada(cbmadrugada.isChecked());

        vd.setQuantoVontade(sbvontade.getProgress()+1);

        Gson g=new Gson();
        Bundle bundle=new Bundle();
        bundle.putString("vd",g.toJson(vd));

        Intent i = new Intent(this, AlternativaUso.class);
        i.putExtras(bundle);
        startActivity(i);
    }


    private int ch(CheckBox c){
        if(c.isChecked()){
            return 1;
        }else{
            return 0;
        }
    }


    public void alcool(View v){
        vd.setTipo(0);
        cbmaconha.setChecked(false);
        cbcocaina.setChecked(false);
        cbcrack.setChecked(false);
        btprox.setVisibility(View.VISIBLE);
    }
    public void maconha(View v){
        vd.setTipo(3);
        cbcocaina.setChecked(false);
        cbcrack.setChecked(false);
        cbalcool.setChecked(false);
        btprox.setVisibility(View.VISIBLE);
    }
    public void cocaina(View v){
        vd.setTipo(4);
        cbmaconha.setChecked(false);
        cbcrack.setChecked(false);
        cbalcool.setChecked(false);
        btprox.setVisibility(View.VISIBLE);
    }
    public void crack(View v){
        vd.setTipo(5);
        cbmaconha.setChecked(false);
        cbcocaina.setChecked(false);
        cbalcool.setChecked(false);
        btprox.setVisibility(View.VISIBLE);
    }
}
