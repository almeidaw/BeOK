package beok.beok;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.orm.SugarContext;

import beok.beok.POJO.UsoDroga;

public class Pergunta1 extends AppCompatActivity {

    boolean[] array;

    Button btprox1;
    SeekBar sbqtd;
    Spinner spbebidas;
    TextView txtlegenda, txtqtd;
    ImageView ivlegenda;

    UsoDroga ud;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pergunta1);

        SugarContext.init(this);

        ud=new UsoDroga();

        btprox1 = (Button) findViewById(R.id.btprox1);
        spbebidas = (Spinner) findViewById(R.id.spbebidas);
        sbqtd = (SeekBar) findViewById(R.id.sbqtd);
        txtlegenda = (TextView) findViewById(R.id.txtlegenda);
        txtqtd = (TextView) findViewById(R.id.txtqtd);
        ivlegenda = (ImageView) findViewById(R.id.ivlegenda);

        final Bundle bundle = getIntent().getExtras();
        if (bundle.getInt("Droga escolhida") == 1){
            spbebidas.setVisibility(View.VISIBLE);
            ivlegenda.setVisibility(View.VISIBLE);
            txtlegenda.setVisibility(View.GONE);
            ud.setCerveja();
            spbebidas.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                    switch (position){
                        case 0:
                            ud.setCerveja();
                            break;
                        case 1:
                            ud.setVinho();
                            break;
                        case 2:
                            ud.setDestilado();
                            break;
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parentView) {

                }

            });
        }else if (bundle.getInt("Droga escolhida") == 2){
            txtlegenda.setText("1 baseado: fino=0,5 grama, normal = 1,2 gramas, bomba= 2 gramas");
            ud.setMaconha();

        }else if (bundle.getInt("Droga escolhida") == 3){
            txtlegenda.setText("1 papelote/pino= 1grama");
            ud.setCocaina();
        }else if (bundle.getInt("Droga escolhida") == 4){
            txtlegenda.setVisibility(View.GONE);
            ud.setCrack();
        }
        array = bundle.getBooleanArray("checkbox");

        sbqtd.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (bundle.getInt("Droga escolhida") == 1){
                    sbqtd.setMax(14);
                    txtqtd.setText(Integer.toString(progress + 1) + " doses de " + spbebidas.getSelectedItem().toString());
                    ud.setQuantidade(progress + 1);
                } else if (bundle.getInt("Droga escolhida") == 2){
                    sbqtd.setMax(19);
                    txtqtd.setText(Float.toString(((float)progress + 1)/2) + " baseados de maconha");
                    ud.setQuantidade(progress + 1);
                } else if (bundle.getInt("Droga escolhida") == 3){
                    sbqtd.setMax(19);
                    txtqtd.setText(Float.toString(((float)progress + 1)/2) + " gramas cocaina");
                    ud.setQuantidade(progress + 1);
                } else if (bundle.getInt("Droga escolhida") == 4){
                    sbqtd.setMax(9);
                    txtqtd.setText(Integer.toString(progress + 1) + " pedras de crack");
                    ud.setQuantidade(progress + 1);
                }
                btprox1.setVisibility(View.VISIBLE);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }

    public void botaoProximo1(View v){
        //adicionar a quantidade de droga que ele usou
        Bundle bundle = new Bundle();
        bundle.putBooleanArray("checkbox", array);
        bundle.putInt("qtd",ud.getQuantidade());
        bundle.putInt("tipo",ud.getTipo());
        Intent i = new Intent(this, Pergunta2.class);
        i.putExtras(bundle);
        startActivity(i);
        //slide from right to left
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }
}
