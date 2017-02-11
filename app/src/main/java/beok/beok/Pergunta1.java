package beok.beok;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

public class Pergunta1 extends AppCompatActivity {

    boolean[] array;

    Button btprox1;
    SeekBar sbqtd;
    Spinner spbebidas;
    TextView txtlegenda, txtqtd;
    ImageView ivlegenda;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pergunta1);

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
        }else if (bundle.getInt("Droga escolhida") == 2){
            txtlegenda.setText("1 baseado: fino=0,5 grama, normal = 1,2 gramas, bomba= 2 gramas");

        }else if (bundle.getInt("Droga escolhida") == 3){
            txtlegenda.setText("1 papelote/pino= 1grama");

        }else if (bundle.getInt("Droga escolhida") == 4){
            txtlegenda.setVisibility(View.GONE);
        }
        array = bundle.getBooleanArray("checkbox");

        sbqtd.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (bundle.getInt("Droga escolhida") == 1){
                    sbqtd.setMax(14);
                    txtqtd.setText(Integer.toString(progress + 1) + " doses de " + spbebidas.getSelectedItem().toString());
                } else if (bundle.getInt("Droga escolhida") == 2){
                    sbqtd.setMax(29);
                    txtqtd.setText(Float.toString(((float)progress + 1)/2) + " baseados de maconha");
                } else if (bundle.getInt("Droga escolhida") == 3){
                    sbqtd.setMax(19);
                    txtqtd.setText(Float.toString(((float)progress + 1)/2) + " gramas cocaina");
                } else if (bundle.getInt("Droga escolhida") == 4){
                    sbqtd.setMax(14);
                    txtqtd.setText(Integer.toString(progress + 1) + " pedras de crack");
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
        Intent i = new Intent(this, Pergunta2.class);
        i.putExtras(bundle);
        startActivity(i);
        //slide from right to left
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }
}
