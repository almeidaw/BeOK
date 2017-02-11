package beok.beok;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.lang.reflect.Array;

public class TelaPerguntas extends AppCompatActivity implements View.OnClickListener{

    boolean[] array;

    ImageView ivlegenda;
    SeekBar sbqtd;
    Button btconfirma;
    TextView txtunidade, txtlegenda;
    Spinner spfreq, spultimavez, spmetafinal, spbebidas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_perguntas);

        btconfirma = (Button) findViewById(R.id.btconfirma);
        txtunidade = (TextView) findViewById(R.id.txtunidade);
        txtlegenda = (TextView) findViewById(R.id.txtlegenda);

        spmetafinal = (Spinner) findViewById(R.id.spmetafinal);
        spultimavez = (Spinner) findViewById(R.id.spultimavez);
        spfreq = (Spinner) findViewById(R.id.spfreq);
        spbebidas = (Spinner) findViewById(R.id.spbebidas);

        sbqtd = (SeekBar) findViewById(R.id.sbqtd);

        ivlegenda = (ImageView) findViewById(R.id.ivlegenda);

        btconfirma.setOnClickListener(this);

        Bundle bundle = getIntent().getExtras();
        if (bundle.getInt("Droga escolhida") == 1){
            spbebidas.setVisibility(View.VISIBLE);
            txtunidade.setText("1 dose de alcool = ");
            txtlegenda.setVisibility(View.GONE);
            ivlegenda.setVisibility(View.VISIBLE);

        }else if (bundle.getInt("Droga escolhida") == 2){
            txtunidade.setText("1 baseado de maconha = ");
            txtlegenda.setText("1 baseado: fino=0,5 grama, normal = 1,2 gramas, bomba= 2 gramas");
        }else if (bundle.getInt("Droga escolhida") == 3){
            txtunidade.setText("1 grama de cocaina = ");
            txtlegenda.setText("1 papelote/pino= 1grama");
        }else if (bundle.getInt("Droga escolhida") == 4){
            txtunidade.setText("1 pedra de crack = ");
            txtlegenda.setText("LEGENDA ALCOOL");
            txtlegenda.setVisibility(View.GONE);
        }
        array = bundle.getBooleanArray("checkbox");

    }

    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btconfirma:
                Bundle bundle = new Bundle();
                bundle.putBooleanArray("checkbox", array);
                Intent nextActivity = new Intent(this, Tela2.class);
                nextActivity.putExtras(bundle);
                startActivity(nextActivity);
                //slide from left to right
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
                break;
        }
    }
}
