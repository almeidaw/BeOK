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

    int quantidade;

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
            sbqtd.setMax(14);
            sbqtd.setProgress(6);
            txtqtd.setText("7 doses");
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
            //txtlegenda.setText(getResources().getString(R.string.peso_baseado));
            txtlegenda.setText(null);
            sbqtd.setMax(19);
            sbqtd.setProgress(9);
            txtqtd.setText("5 baseados");
            ud.setMaconha();

        }else if (bundle.getInt("Droga escolhida") == 3){
            txtlegenda.setText(getResources().getString(R.string.peso_pedra));
            sbqtd.setMax(19);
            sbqtd.setProgress(9);
            txtqtd.setText("5 gramas");
            ud.setCocaina();
        }else if (bundle.getInt("Droga escolhida") == 4){
            txtlegenda.setVisibility(View.GONE);
            sbqtd.setMax(14);
            sbqtd.setProgress(6);
            txtqtd.setText("7 pedras");
            ud.setCrack();
        }
        array = bundle.getBooleanArray("checkbox");

        sbqtd.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (bundle.getInt("Droga escolhida") == 1){
                    sbqtd.setMax(14);
                    txtqtd.setText(Integer.toString(progress + 1) + " doses");
                    quantidade=progress + 1;
                } else if (bundle.getInt("Droga escolhida") == 2){
                    sbqtd.setMax(19);
                    txtqtd.setText(Float.toString(((float)progress + 1)/2) + " baseados");
                    quantidade = progress + 1;
                } else if (bundle.getInt("Droga escolhida") == 3){
                    sbqtd.setMax(19);
                    txtqtd.setText(Float.toString(((float)progress + 1)/2) + " gramas");
                    quantidade= progress + 1;
                } else if (bundle.getInt("Droga escolhida") == 4){
                    sbqtd.setMax(9);
                    txtqtd.setText(Integer.toString(progress + 1) + " pedras");
                    quantidade = progress + 1;
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
        bundle.putInt("qtd",quantidade);
        bundle.putInt("tipo",ud.getTipo());
        Intent i = new Intent(this, Pergunta3.class); //"Pergunta2" é a activity que possuí um mapa
        i.putExtras(bundle);
        startActivity(i);
        //slide from right to left
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }
}
