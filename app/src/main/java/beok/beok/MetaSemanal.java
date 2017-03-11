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
import android.widget.Toast;

import com.orm.SugarContext;
import com.orm.SugarRecord;

import java.util.List;

import beok.beok.POJO.ConsumoAtual;
import beok.beok.POJO.MetaGeral;
import beok.beok.POJO.MetaSemGeral;
import beok.beok.api.DB;

public class MetaSemanal extends AppCompatActivity {

    Button btproximo;
    Spinner spmeta1, spmeta2, spmeta3, spmeta4, spfreq1, spfreq2, spfreq3, spfreq4, spbebidas, spbaseado_medio;
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

    beok.beok.POJO.MetaSemanal meta1;
    beok.beok.POJO.MetaSemanal meta2;
    beok.beok.POJO.MetaSemanal meta3;
    beok.beok.POJO.MetaSemanal meta4;

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
        spbaseado_medio = (Spinner) findViewById(R.id.spbaseado_medio);
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


        meta1=new beok.beok.POJO.MetaSemanal();

        meta2=new beok.beok.POJO.MetaSemanal();

        meta3=new beok.beok.POJO.MetaSemanal();

        meta4=new beok.beok.POJO.MetaSemanal();

        SugarContext.init(this);

        List<ConsumoAtual> cas=SugarRecord.listAll(ConsumoAtual.class);
       String str="";
        for(ConsumoAtual ca:cas){
            str+=ca.getTipo();
            switch (ca.getTipo()){
                case 0:
                    usoalcool=true;
                    break;
                case 1:
                    usoalcool=true;
                    break;
                case 2:
                    usoalcool=true;
                    break;
                case 3:
                    usomaconha=true;
                    break;
                case 4:
                    usococaina=true;
                    break;
                case 5:
                    usocrack=true;
                    break;
            }
        }


        if (usoalcool){
            llalcool.setVisibility(View.VISIBLE);
            meta1.setTipo(0);
        }
        if (usomaconha){
            llmaconha.setVisibility(View.VISIBLE);
            meta2.setTipo(3);
        }
        if (usococaina){
            llcocaina.setVisibility(View.VISIBLE);
            meta3.setTipo(4);

        }
        if (usocrack){
            llcrack.setVisibility(View.VISIBLE);
            meta4.setTipo(5);
        }

        sbqtd1.setMax(14);
        sbqtd2.setMax(19);
        sbqtd3.setMax(19);
        sbqtd4.setMax(14);

        sbqtd1.setProgress(6);
        sbqtd2.setProgress(9);
        sbqtd3.setProgress(9);
        sbqtd4.setProgress(6);

        txtqtd1.setText("7 doses");
        txtqtd2.setText("10 baseados");
        txtqtd3.setText("10 gramas");
        txtqtd4.setText("7 pedras");

        sbqtd1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                txtqtd1.setText(Integer.toString(progress + 1) + " doses");
                meta1.setTipo(spbebidas.getSelectedItemPosition());
                meta1.setQuantidade(progress+1);
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
                txtqtd2.setText(Float.toString(((float)progress + 1)/2) + " baseados");
                meta2.setTipo(3);
                meta2.setQuantidade(progress+1);
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
                txtqtd3.setText(Float.toString(((float)progress + 1)/2) + " gramas");
                meta3.setTipo(4);
                meta3.setQuantidade(progress+1);
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
                txtqtd4.setText(Integer.toString(progress + 1) + " pedras");
                meta4.setTipo(5);
                meta4.setQuantidade(progress+1);
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
                    meta1.setFreqSemanal(0);
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
                    meta2.setFreqSemanal(0);
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
                    meta3.setFreqSemanal(0);
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
                    meta4.setFreqSemanal(0);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spfreq1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                meta1.setFreqSemanal(position+1);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spfreq2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                meta2.setFreqSemanal(position+1);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spfreq3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                meta3.setFreqSemanal(position+1);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spfreq4.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                meta4.setFreqSemanal(position+1);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    public void botaoProximo(View v){
        meta1.setManha(cbmanha1.isChecked());
        meta1.setTarde(cbtarde1.isChecked());
        meta1.setNoite(cbnoite1.isChecked());
        meta1.setMadrugada(cbmadrugada1.isChecked());

        meta2.setManha(cbmanha2.isChecked());
        meta2.setTarde(cbtarde2.isChecked());
        meta2.setNoite(cbnoite2.isChecked());
        meta2.setMadrugada(cbmadrugada2.isChecked());

        meta3.setManha(cbmanha3.isChecked());
        meta3.setTarde(cbtarde3.isChecked());
        meta3.setNoite(cbnoite3.isChecked());
        meta3.setMadrugada(cbmadrugada3.isChecked());

        meta4.setManha(cbmanha4.isChecked());
        meta4.setTarde(cbtarde4.isChecked());
        meta4.setNoite(cbnoite4.isChecked());
        meta4.setMadrugada(cbmadrugada4.isChecked());

        if (usoalcool){
            meta1.setTamMedBaseado(0);
            DB.save(meta1);
        }
        if (usomaconha){
            meta2.setTamMedBaseado(spbaseado_medio.getSelectedItemPosition() + 1);
            DB.save(meta2);
        }
        if (usococaina){
            meta3.setTamMedBaseado(0);
            DB.save(meta3);
        }
        if (usocrack){
            meta4.setTamMedBaseado(0);
            DB.save(meta4);
        }

        Intent i = new Intent(this, MetaSemanalGeral.class); //Direcionar para home?
        startActivity(i);
    }
}
