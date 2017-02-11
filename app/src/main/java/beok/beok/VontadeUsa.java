package beok.beok;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.SeekBar;
import android.widget.TextView;

public class VontadeUsa extends AppCompatActivity {

    CheckBox cbalcool, cbmaconha, cbcocaina, cbcrack,
            cbmanha, cbtarde, cbnoite, cbmadrugada;
    Button btprox;
    SeekBar sbvontade;
    TextView txtvontade;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vontade_usa);

        cbalcool = (CheckBox) findViewById(R.id.cbalcool);
        cbmaconha = (CheckBox) findViewById(R.id.cbmaconha);
        cbcocaina = (CheckBox) findViewById(R.id.cbcocaina);
        cbcrack = (CheckBox) findViewById(R.id.cbcrack);
        cbmanha = (CheckBox) findViewById(R.id.cbmanha);
        cbtarde = (CheckBox) findViewById(R.id.cbtarde);
        cbnoite = (CheckBox) findViewById(R.id.cbnoite);
        cbmadrugada = (CheckBox) findViewById(R.id.cbmadrugada);

        btprox = (Button) findViewById(R.id.btprox);

        sbvontade = (SeekBar) findViewById(R.id.sbvontade);

        txtvontade = (TextView) findViewById(R.id.txtvontade);

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
        Intent i = new Intent(this, AlternativaUso.class);
        startActivity(i);
    }

    public void alcool(View v){
        cbmaconha.setChecked(false);
        cbcocaina.setChecked(false);
        cbcrack.setChecked(false);
    }
    public void maconha(View v){
        cbcocaina.setChecked(false);
        cbcrack.setChecked(false);
        cbalcool.setChecked(false);
    }
    public void cocaina(View v){
        cbmaconha.setChecked(false);
        cbcrack.setChecked(false);
        cbalcool.setChecked(false);
    }
    public void crack(View v){
        cbmaconha.setChecked(false);
        cbcocaina.setChecked(false);
        cbalcool.setChecked(false);
    }
}
