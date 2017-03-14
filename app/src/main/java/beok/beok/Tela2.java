package beok.beok;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

import com.google.gson.Gson;
import com.orm.SugarContext;

import java.util.Date;

import beok.beok.POJO.ConsumoAtual;
import beok.beok.POJO.MetaGeral;
import beok.beok.api.DB;

public class Tela2 extends AppCompatActivity implements View.OnClickListener{

    boolean[] array = new boolean[]{false,false,false,false}; //salva quais checkbox foram selecionados

    Button btproximo2;
    CheckBox cbparaalcool, cbparamaconha, cbparacrack, cbparacocaina;

    Bundle bundle;
    Bundle newBundle;

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

        bundle=getIntent().getExtras();
        newBundle=new Bundle();

        SugarContext.init(this);
        //Completa os checkboxs que já foram selecionados(essa informação era perdida quando muda para tela de perguntas)
        if (bundle != null){
            cbparaalcool.setChecked(bundle.getBooleanArray("checkbox")[0]);
            cbparamaconha.setChecked(bundle.getBooleanArray("checkbox")[1]);
            cbparacocaina.setChecked(bundle.getBooleanArray("checkbox")[2]);
            cbparacrack.setChecked(bundle.getBooleanArray("checkbox")[3]);
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
        }
    }

    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btproximo2:
                Intent nextActivity = new Intent(this, Tela3.class);
                Gson g=new Gson();
                for(int i=0;i<6;i++){
                    String str=newBundle.getString("tipoDroga"+i);
                    Date now=new Date();
                    now.setTime(System.currentTimeMillis());
                    if(str!=null){
                        ConsumoAtual ca=g.fromJson(str,ConsumoAtual.class);
                        ca.setDataInicio(now);
                        DB.save(ca);
                    }
                    str=newBundle.getString("metaTipo"+i);
                    if(str!=null){
                        MetaGeral m=g.fromJson(str,MetaGeral.class);
                        m.setDataInicio(now);
                        DB.save(m);
                    }
                }
                startActivity(nextActivity);
                //slide from right to left
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                break;
        }
    }

    public void paraAlcool(View v){
        if (cbparaalcool.isChecked()){
            array[0] = true;
            Intent i = new Intent(this, TelaPerguntas.class);                   //Passa informação para tela de perguntas
            newBundle.putInt("Droga escolhida", 1);                //de qual droga foi selecionada
            newBundle.putBooleanArray("checkbox",array);           //e quais perguntas serão apresentadas
            i.putExtras(newBundle);
            startActivity(i);
            //slide from right to left
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        } else {
            array[0] = false;
            cbparaalcool.setChecked(false);
            newBundle.putString("tipoDroga0",null);
            newBundle.putString("metaTipo0",null);
            newBundle.putString("tipoDroga1",null);
            newBundle.putString("metaTipo1",null);
            newBundle.putString("tipoDroga2",null);
            newBundle.putString("metaTipo2",null);
        }
    }

    public void paraMaconha(View v){
        if (cbparamaconha.isChecked()) {
            array[1] = true;
            newBundle.putInt("Droga escolhida", 2);
            newBundle.putBooleanArray("checkbox", array);
            Intent i = new Intent(this, TelaPerguntas.class);
            i.putExtras(newBundle);
            startActivity(i);
            //slide from right to left
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        } else {
            array[1] = false;
            cbparamaconha.setChecked(false);
            newBundle.putString("tipoDroga3",null);
            newBundle.putString("metaTipo3",null);
        }
    }

    public void paraCocaina(View v){
        if (cbparacocaina.isChecked()) {
            array[2] = true;
            newBundle.putInt("Droga escolhida", 3);
            newBundle.putBooleanArray("checkbox", array);
            Intent i = new Intent(this, TelaPerguntas.class);
            i.putExtras(newBundle);
            startActivity(i);
            //slide from right to left
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        } else {
            array[2] = false;
            cbparacocaina.setChecked(false);
            newBundle.putString("tipoDroga4",null);
            newBundle.putString("metaTipo4",null);
        }
    }

    public void paraCrack(View v){
        if (cbparacrack.isChecked()) {
            array[3] = true;
            newBundle.putInt("Droga escolhida", 4);
            newBundle.putBooleanArray("checkbox", array);
            Intent i = new Intent(this, TelaPerguntas.class);
            i.putExtras(newBundle);
            startActivity(i);
            //slide from right to left
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        } else {
            array[3] = false;
            cbparacrack.setChecked(false);
            newBundle.putString("tipoDroga5",null);
            newBundle.putString("metaTipo5",null);
        }

    }
    @Override
    public void onBackPressed() {

    }
}
