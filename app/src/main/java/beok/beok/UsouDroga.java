package beok.beok;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

public class UsouDroga extends AppCompatActivity {

    boolean[] array = new boolean[]{false,false,false,false}; //salva quais checkbox foram selecionados

    CheckBox cbalcool, cbmaconha, cbcocaina, cbcrack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usou_droga);

        cbalcool = (CheckBox) findViewById(R.id.cbalcool);
        cbmaconha = (CheckBox) findViewById(R.id.cbmaconha);
        cbcocaina = (CheckBox) findViewById(R.id.cbcocaina);
        cbcrack = (CheckBox) findViewById(R.id.cbcrack);

        if (getIntent().getExtras() == null) {}
        else{
            Bundle bundle = getIntent().getExtras();
            cbalcool.setChecked(bundle.getBooleanArray("checkbox")[0]);
            cbmaconha.setChecked(bundle.getBooleanArray("checkbox")[1]);
            cbcocaina.setChecked(bundle.getBooleanArray("checkbox")[2]);
            cbcrack.setChecked(bundle.getBooleanArray("checkbox")[3]);
            array = bundle.getBooleanArray("checkbox");
        }

    }
    public void botaoAlcool(View v){
        if (cbalcool.isChecked()){
            array[0] = true;
            Intent i = new Intent(this, Pergunta1.class);
            Bundle bundle = new Bundle();                       //Passa informação para tela de perguntas
            bundle.putInt("Droga escolhida", 1);                //de qual droga foi selecionada
            bundle.putBooleanArray("checkbox",array);           //e quais perguntas serão apresentadas
            i.putExtras(bundle);
            startActivity(i);
            //slide from right to left
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        } else {
            array[0] = false;
            cbalcool.setChecked(false);
        }
    }
    public void botaoMaconha(View v){
        if (cbmaconha.isChecked()) {
            array[1] = true;
            Bundle bundle = new Bundle();
            bundle.putInt("Droga escolhida", 2);
            bundle.putBooleanArray("checkbox", array);
            Intent i = new Intent(this, Pergunta1.class);
            i.putExtras(bundle);
            startActivity(i);
            //slide from right to left
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        } else {
            array[1] = false;
            cbmaconha.setChecked(false);
        }
    }
    public void botaoCocaina(View v){
        if (cbcocaina.isChecked()) {
            array[2] = true;
            Bundle bundle = new Bundle();
            bundle.putInt("Droga escolhida", 3);
            bundle.putBooleanArray("checkbox", array);
            Intent i = new Intent(this, Pergunta1.class);
            i.putExtras(bundle);
            startActivity(i);
            //slide from right to left
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        } else {
            array[2] = false;
            cbcocaina.setChecked(false);
        }
    }
    public void botaoCrack(View v){
        if (cbcrack.isChecked()) {
            array[3] = true;
            Bundle bundle = new Bundle();
            bundle.putInt("Droga escolhida", 4);
            bundle.putBooleanArray("checkbox", array);
            Intent i = new Intent(this, Pergunta1.class);
            i.putExtras(bundle);
            startActivity(i);
            //slide from right to left
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        } else {
            array[3] = false;
            cbcrack.setChecked(false);
        }
    }
}
