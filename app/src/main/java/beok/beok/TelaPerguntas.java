package beok.beok;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.orm.SugarContext;

import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.util.Date;

import beok.beok.POJO.ConsumoAtual;
import beok.beok.api.DB;

public class TelaPerguntas extends AppCompatActivity implements View.OnClickListener{

    boolean[] array;
    int drogaescolhida;

    ImageView ivlegenda;
    SeekBar sbqtd;
    Button btconfirma;
    TextView txtunidade, txtlegenda,txtqtddroga, txttamanho;
    Spinner spfreq, spultimavez, spmetafinal, spbebidas, spbaseado_medio;
    EditText edtxtgasto;
    LinearLayout lltamanho;

    ConsumoAtual ca;

    Bundle bundle;
    Bundle newBundle;

    int quantidade;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_perguntas);

        ca=new ConsumoAtual();

        SugarContext.init(this);

        edtxtgasto = (EditText) findViewById(R.id.edtxtgasto);

        txttamanho = (TextView) findViewById(R.id.txttamanho);
        spbaseado_medio = (Spinner) findViewById(R.id.spbaseado_medio);
        lltamanho = (LinearLayout) findViewById(R.id.lltamanho);

        btconfirma = (Button) findViewById(R.id.btconfirma);
        txtunidade = (TextView) findViewById(R.id.txtunidade);
        txtlegenda = (TextView) findViewById(R.id.txtlegenda);

        txtqtddroga = (TextView) findViewById(R.id.txtqtddroga);

        //spmetafinal = (Spinner) findViewById(R.id.spmetafinal);
        //spultimavez = (Spinner) findViewById(R.id.spultimavez);
        spfreq = (Spinner) findViewById(R.id.spfreq);
        spbebidas = (Spinner) findViewById(R.id.spbebidas);

        sbqtd = (SeekBar) findViewById(R.id.sbqtd);

        ivlegenda = (ImageView) findViewById(R.id.ivlegenda);

        btconfirma.setOnClickListener(this);
        btconfirma.setVisibility(View.INVISIBLE);
        bundle = getIntent().getExtras();
        newBundle = new Bundle();

        drogaescolhida=bundle.getInt("Droga escolhida");
        if (bundle.getInt("Droga escolhida") == 1){
            spbebidas.setVisibility(View.VISIBLE);
            txtunidade.setText("1 dose de alcool = ");
            txtlegenda.setText("Uma dose é igual a");
            ivlegenda.setVisibility(View.VISIBLE);
            ca.setCerveja();
            sbqtd.setMax(14);
            sbqtd.setProgress(6);
            txtqtddroga.setText("7 doses");
            spbebidas.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                    switch (position){
                        case 0:
                            ca.setCerveja();
                            break;
                        case 1:
                            ca.setVinho();
                            break;
                        case 2:
                            ca.setDestilado();
                            break;
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parentView) {

                }

            });
        }else if (bundle.getInt("Droga escolhida") == 2){
            //txtunidade.setText("1 baseado de maconha = ");
            txtlegenda.setText("baseado fino = 0,5 grama \nbaseado normal = 1,2 grama\nbomba = 2 grama");
            ca.setMaconha();
            sbqtd.setMax(19);
            sbqtd.setProgress(9);
            txtqtddroga.setText("10 baseados");
            lltamanho.setVisibility(View.VISIBLE);
        }else if (bundle.getInt("Droga escolhida") == 3){
            txtunidade.setText("1 grama de cocaina = ");
            txtlegenda.setText("1 papelote/pino= 1grama");
            ca.setCocaina();
            sbqtd.setMax(19);
            sbqtd.setProgress(9);
            txtqtddroga.setText("10 gramas");

        }else if (bundle.getInt("Droga escolhida") == 4){
            txtunidade.setText("1 pedra de crack = ");
            //txtlegenda.setText("LEGENDA ALCOOL");
            txtlegenda.setVisibility(View.GONE);
            ca.setCrack();
            sbqtd.setMax(14);
            sbqtd.setProgress(6);
            txtqtddroga.setText("7 pedras");

        }
        array = bundle.getBooleanArray("checkbox");
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
                if (bundle.getInt("Droga escolhida") == 1){
                    txtqtddroga.setText(Integer.toString(progress + 1) + " doses");
                    quantidade= progress+1;
                } else if (bundle.getInt("Droga escolhida") == 2){
                    txtqtddroga.setText(Float.toString(((float)progress + 1)/2) + " baseados");
                    quantidade= progress+1;
                } else if (bundle.getInt("Droga escolhida") == 3){
                    txtqtddroga.setText(Float.toString(((float)progress + 1)/2) + " gramas");
                    quantidade= progress+1;
                } else if (bundle.getInt("Droga escolhida") == 4){
                    txtqtddroga.setText(Integer.toString(progress + 1) + " pedras");
                    quantidade= progress+1;
                }
                btconfirma.setVisibility(View.VISIBLE);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


    }

    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btconfirma:
                ca.setFreqSemanal(spfreq.getSelectedItemPosition()+1);
                if(!edtxtgasto.getText().toString().equals("")) {
                    try {
                        ca.setGasto(Integer.parseInt(edtxtgasto.getText().toString()));
                    } catch (Exception e) {
                        Toast.makeText(this, "Insira um número em gasto",Toast.LENGTH_LONG).show();
                    }
                }
                ca.setQuantidade(quantidade);

                if (lltamanho.getVisibility() == View.VISIBLE){
                    ca.setTamMedBaseado(spbaseado_medio.getSelectedItemPosition() + 1);
                } else {
                    ca.setTamMedBaseado(0);
                }

                Date now=new Date();
                now.setTime(System.currentTimeMillis());
                ca.setDataInicio(now);
                Gson g=new Gson();

                newBundle.putBooleanArray("checkbox", array);
                newBundle.putInt("Droga escolhida", drogaescolhida);
                newBundle.putString("tipoDroga"+ca.getTipo(),g.toJson(ca));
                Intent nextActivity = new Intent(this, MetaTratamento.class);
                nextActivity.putExtras(newBundle);
                startActivity(nextActivity);
                //slide from left to right
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
                break;
        }
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
