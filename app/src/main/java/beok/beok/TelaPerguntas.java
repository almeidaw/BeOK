package beok.beok;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.orm.SugarContext;

import beok.beok.POJO.ConsumoAtual;
import beok.beok.api.DB;

public class TelaPerguntas extends AppCompatActivity implements View.OnClickListener{

    boolean[] array;
    int drogaescolhida;

    ImageView ivlegenda;
    SeekBar sbqtd;
    Button btconfirma;
    TextView txtunidade, txtlegenda,txtqtddroga;
    Spinner spfreq, spultimavez, spmetafinal, spbebidas;
    EditText edtxtgasto;

    ConsumoAtual ca;

    Bundle bundle;

    int quantidade;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_perguntas);

        ca=new ConsumoAtual();

        SugarContext.init(this);

        edtxtgasto = (EditText) findViewById(R.id.edtxtgasto);

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

        drogaescolhida=bundle.getInt("Droga escolhida");
        if (bundle.getInt("Droga escolhida") == 1){
            spbebidas.setVisibility(View.VISIBLE);
            txtunidade.setText("1 dose de alcool = ");
            txtlegenda.setText("Uma dose é igual a");
            ivlegenda.setVisibility(View.VISIBLE);
            ca.setCerveja();
            sbqtd.setMax(15);
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
            sbqtd.setMax(10);
        }else if (bundle.getInt("Droga escolhida") == 3){
            txtunidade.setText("1 grama de cocaina = ");
            txtlegenda.setText("1 papelote/pino= 1grama");
            ca.setCocaina();
            sbqtd.setMax(10);
        }else if (bundle.getInt("Droga escolhida") == 4){
            txtunidade.setText("1 pedra de crack = ");
            txtlegenda.setText("LEGENDA ALCOOL");
            txtlegenda.setVisibility(View.GONE);
            ca.setCrack();
            sbqtd.setMax(10);
        }
        array = bundle.getBooleanArray("checkbox");


        sbqtd.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (bundle.getInt("Droga escolhida") == 1){
                    sbqtd.setMax(14);
                    txtqtddroga.setText(Integer.toString(progress + 1) + " doses de " + spbebidas.getSelectedItem().toString());
                    quantidade= progress+1;
                } else if (bundle.getInt("Droga escolhida") == 2){
                    sbqtd.setMax(19);
                    txtqtddroga.setText(Float.toString(((float)progress + 1)/2) + " baseados de maconha");
                    quantidade= progress+1;
                } else if (bundle.getInt("Droga escolhida") == 3){
                    sbqtd.setMax(19);
                    txtqtddroga.setText(Float.toString(((float)progress + 1)/2) + " gramas cocaina");
                    quantidade= progress+1;
                } else if (bundle.getInt("Droga escolhida") == 4){
                    sbqtd.setMax(9);
                    txtqtddroga.setText(Integer.toString(progress + 1) + " pedras de crack");
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
                DB.save(ca);
                Bundle bundle = new Bundle();
                bundle.putBooleanArray("checkbox", array);
                bundle.putInt("Droga escolhida", drogaescolhida);
                Intent nextActivity = new Intent(this, MetaTratamento.class);
                nextActivity.putExtras(bundle);
                startActivity(nextActivity);
                //slide from left to right
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
                break;
        }
    }
}
