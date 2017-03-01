package beok.beok;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.orm.SugarContext;

import java.util.Calendar;
import java.util.Date;

import beok.beok.POJO.RelatoDiario;
import beok.beok.api.DB;

public class NaoUsouDroga extends AppCompatActivity {

    Button btsim, btnao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nao_usou_droga);

        SugarContext.init(this);

        btsim = (Button) findViewById(R.id.btsim);
        btnao = (Button) findViewById(R.id.btnao);

    }

    public void botaoSim(View v){
        Intent i = new Intent(this, VontadeUsa.class);
        startActivity(i);
    }

    public void botaoNao(View v){
        Bundle bundle = new Bundle();
        bundle.putInt("se_usou",1);

        RelatoDiario rd=new RelatoDiario();

        Date dtnow=new Date();
        dtnow.setTime(System.currentTimeMillis());

        rd.setDataDiario(dtnow);
        rd.setUso(false);
        rd.setVontade(false);

        DB.save(rd);

        Intent i = new Intent(this, Fim.class);
        i.putExtras(bundle);
        startActivity(i);
    }
}
