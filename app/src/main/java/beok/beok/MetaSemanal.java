package beok.beok;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.orm.SugarContext;
import com.orm.SugarRecord;

import java.util.List;

import beok.beok.POJO.ConsumoAtual;
import beok.beok.POJO.MetaGeral;

public class MetaSemanal extends AppCompatActivity {

    Button btproximo;
    Spinner spmeta1, spmeta2, spmeta3, spmeta4, spfreq1, spfreq2, spfreq3, spfreq4, spbebidas;
    CheckBox cbmanha1, cbtarde1, cbnoite1, cbmadrugada1, cbmanha2, cbtarde2, cbnoite2, cbmadrugada2,
            cbmanha3, cbtarde3, cbnoite3, cbmadrugada3, cbmanha4, cbtarde4, cbnoite4, cbmadrugada4;
    ImageView ivbebidas;
    TextView txtqtd1, txtqtd2, txtqtd3, txtqtd4;
    LinearLayout llalcool, llmaconha, llcocaina, llcrack;
    SeekBar sbqtd1, sbqtd2, sbqtd3, sbqtd4;

    boolean usoalcool;
    boolean usomaconha;
    boolean usococaina;
    boolean usocrack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meta_semanal);

        btproximo = (Button) findViewById(R.id.btproximo);
        spmeta1 = (Spinner) findViewById(R.id.spmeta1);
        spmeta2 = (Spinner) findViewById(R.id.spmeta2);
        spmeta3 = (Spinner) findViewById(R.id.spmeta3);
        spmeta4 = (Spinner) findViewById(R.id.spmeta4);
        spfreq1 = (Spinner) findViewById(R.id.spfreq1);
        spfreq2 = (Spinner) findViewById(R.id.spfreq2);
        spfreq3 = (Spinner) findViewById(R.id.spfreq3);
        spfreq4 = (Spinner) findViewById(R.id.spfreq4);
        spbebidas = (Spinner) findViewById(R.id.spbebidas);
        cbmanha1 = (CheckBox) findViewById(R.id.cbmanha1);
        cbmanha2 = (CheckBox) findViewById(R.id.cbmanha2);
        cbmanha3 = (CheckBox) findViewById(R.id.cbmanha3);
        cbmanha4 = (CheckBox) findViewById(R.id.cbmanha4);
        cbtarde1 = (CheckBox) findViewById(R.id.cbtarde1);
        cbtarde2 = (CheckBox) findViewById(R.id.cbtarde2);
        cbtarde3 = (CheckBox) findViewById(R.id.cbtarde3);
        cbtarde4 = (CheckBox) findViewById(R.id.cbtarde4);
        cbnoite1 = (CheckBox) findViewById(R.id.cbnoite1);
        cbnoite2 = (CheckBox) findViewById(R.id.cbnoite2);
        cbnoite3 = (CheckBox) findViewById(R.id.cbnoite3);
        cbnoite4 = (CheckBox) findViewById(R.id.cbnoite4);
        cbmadrugada1 = (CheckBox) findViewById(R.id.cbmadrugada1);
        cbmadrugada2 = (CheckBox) findViewById(R.id.cbmadrugada2);
        cbmadrugada3 = (CheckBox) findViewById(R.id.cbmadrugada3);
        cbmadrugada4 = (CheckBox) findViewById(R.id.cbmadrugada4);
        ivbebidas = (ImageView) findViewById(R.id.ivbebidas);
        llalcool = (LinearLayout) findViewById(R.id.llalcool);
        llmaconha = (LinearLayout) findViewById(R.id.llmaconha);
        llcocaina = (LinearLayout) findViewById(R.id.llcocaina);
        llcrack = (LinearLayout) findViewById(R.id.llcrack);
        sbqtd1 = (SeekBar) findViewById(R.id.sbqtd1);
        sbqtd2 = (SeekBar) findViewById(R.id.sbqtd2);
        sbqtd3 = (SeekBar) findViewById(R.id.sbqtd3);
        sbqtd4 = (SeekBar) findViewById(R.id.sbqtd4);
        txtqtd1 = (TextView) findViewById(R.id.txtqtd1);
        txtqtd2 = (TextView) findViewById(R.id.txtqtd2);
        txtqtd3 = (TextView) findViewById(R.id.txtqtd3);
        txtqtd4 = (TextView) findViewById(R.id.txtqtd4);

        usoalcool=false;
        usomaconha=false;
        usococaina=false;
        usocrack=false;

        SugarContext.init(this);

        List<MetaGeral> mgs=SugarRecord.listAll(MetaGeral.class);
        for(MetaGeral mg:mgs){
            switch (mg.getTipo()){
                case 0:
                usoalcool=true;
                    break;
                case 1:
                    usomaconha=true;
                    break;
                case 2:
                    usococaina=true;
                    break;
                case 3:
                    usocrack=true;
                    break;
            }
        }


        if (usoalcool){
            llalcool.setVisibility(View.VISIBLE);
        }
        if (usomaconha){
            llmaconha.setVisibility(View.VISIBLE);
        }
        if (usococaina){
            llcocaina.setVisibility(View.VISIBLE);
        }
        if (usocrack){
            llcrack.setVisibility(View.VISIBLE);
        }

        sbqtd1.setMax(14);
        sbqtd2.setMax(29);
        sbqtd3.setMax(19);
        sbqtd4.setMax(14);

        sbqtd1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                txtqtd1.setText(Integer.toString(progress + 1) + " doses de " + spbebidas.getSelectedItem().toString());
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        sbqtd2.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                txtqtd2.setText(Float.toString(((float)progress + 1)/2) + " baseados de maconha");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        sbqtd3.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                txtqtd3.setText(Float.toString(((float)progress + 1)/2) + " gramas cocaina");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        sbqtd4.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                txtqtd4.setText(Integer.toString(progress + 1) + " pedras de crack");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        spmeta1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (spmeta1.getItemAtPosition(position).equals("Reduzir o uso")){
                    spfreq1.setVisibility(View.VISIBLE);
                }else{
                    spfreq1.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spmeta2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (spmeta2.getItemAtPosition(position).equals("Reduzir o uso")){
                    spfreq2.setVisibility(View.VISIBLE);
                }else{
                    spfreq2.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spmeta3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (spmeta3.getItemAtPosition(position).equals("Reduzir o uso")){
                    spfreq3.setVisibility(View.VISIBLE);
                }else{
                    spfreq3.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spmeta4.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (spmeta4.getItemAtPosition(position).equals("Reduzir o uso")){
                    spfreq4.setVisibility(View.VISIBLE);
                }else{
                    spfreq4.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    public void botaoProximo(View v){
        Intent i = new Intent(this, Fim.class); //Direcionar para home?
        startActivity(i);
    }
}
