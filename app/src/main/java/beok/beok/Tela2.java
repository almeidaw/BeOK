package beok.beok;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

public class Tela2 extends AppCompatActivity implements View.OnClickListener{

    boolean[] array = new boolean[]{false,false,false,false}; //salva quais checkbox foram selecionados

    Button btproximo2;
    CheckBox cbparaalcool, cbparamaconha, cbparacrack, cbparacocaina;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela2);

        cbparaalcool = (CheckBox) findViewById(R.id.cbparaalcool);
        cbparamaconha = (CheckBox) findViewById(R.id.cbparamaconha);
        cbparacocaina = (CheckBox) findViewById(R.id.cbparacocaina);
        cbparacrack = (CheckBox) findViewById(R.id.cbparacrack);

        btproximo2 = (Button) findViewById(R.id.btproximo2);

        btproximo2.setOnClickListener(this);

        //Completa os checkboxs que já foram selecionados(essa informação era perdida quando muda para tela de perguntas)
        if (getIntent().getExtras() == null) {}
        else{
            Bundle bundle = getIntent().getExtras();
            cbparaalcool.setChecked(bundle.getBooleanArray("checkbox")[0]);
            cbparamaconha.setChecked(bundle.getBooleanArray("checkbox")[1]);
            cbparacocaina.setChecked(bundle.getBooleanArray("checkbox")[2]);
            cbparacrack.setChecked(bundle.getBooleanArray("checkbox")[3]);
            array = bundle.getBooleanArray("checkbox");
        }
    }

    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btproximo2:
                Intent nextActivity = new Intent(this, Tela3.class);
                startActivity(nextActivity);
                //slide from right to left
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                break;
        }
    }

    public void paraAlcool(View v){
        if (cbparaalcool.isChecked()){
            array[0] = true;
            Intent i = new Intent(this, TelaPerguntas.class);
            Bundle bundle = new Bundle();                       //Passa informação para tela de perguntas
            bundle.putInt("Droga escolhida", 1);                //de qual droga foi selecionada
            bundle.putBooleanArray("checkbox",array);           //e quais perguntas serão apresentadas
            i.putExtras(bundle);
            startActivity(i);
            //slide from right to left
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        } else {
            array[0] = false;
            cbparaalcool.setChecked(false);
        }
    }

    public void paraMaconha(View v){
        if (cbparamaconha.isChecked()) {
            array[1] = true;
            Bundle bundle = new Bundle();
            bundle.putInt("Droga escolhida", 2);
            bundle.putBooleanArray("checkbox", array);
            Intent i = new Intent(this, TelaPerguntas.class);
            i.putExtras(bundle);
            startActivity(i);
            //slide from right to left
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        } else {
            array[1] = false;
            cbparamaconha.setChecked(false);
        }
    }

    public void paraCocaina(View v){
        if (cbparacocaina.isChecked()) {
            array[2] = true;
            Bundle bundle = new Bundle();
            bundle.putInt("Droga escolhida", 3);
            bundle.putBooleanArray("checkbox", array);
            Intent i = new Intent(this, TelaPerguntas.class);
            i.putExtras(bundle);
            startActivity(i);
            //slide from right to left
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        } else {
            array[2] = false;
            cbparacocaina.setChecked(false);
        }
    }

    public void paraCrack(View v){
        if (cbparacrack.isChecked()) {
            array[3] = true;
            Bundle bundle = new Bundle();
            bundle.putInt("Droga escolhida", 4);
            bundle.putBooleanArray("checkbox", array);
            Intent i = new Intent(this, TelaPerguntas.class);
            i.putExtras(bundle);
            startActivity(i);
            //slide from right to left
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        } else {
            array[3] = false;
            cbparacrack.setChecked(false);
        }

    }
}
