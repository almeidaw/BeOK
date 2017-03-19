package beok.beok;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.orm.SugarContext;

import java.util.Calendar;
import java.util.Date;

import beok.beok.POJO.Mural;
import beok.beok.POJO.RelatoDiario;
import beok.beok.POJO.UsoDroga;
import beok.beok.api.DB;

public class Pergunta7 extends AppCompatActivity {

    boolean[] array;

    Button btsim, btnao;
    UsoDroga ud;
    RelatoDiario rd;
    Mural mr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pergunta7);

        SugarContext.init(this);

        ud=new UsoDroga();
        rd=new RelatoDiario();
        mr=new Mural();

        Date dtnow=new Date();
        dtnow.setTime(System.currentTimeMillis());


        btnao = (Button) findViewById(R.id.btnao);
        btsim = (Button) findViewById(R.id.btsim);

        Bundle bundle = getIntent().getExtras();
        array = bundle.getBooleanArray("checkbox");

        Date date = new Date();
        Calendar calendar=Calendar.getInstance();
        calendar.setTime(date);
        //calendar.clear();
        //calendar.set(Calendar.YEAR,bundle.getInt("ano"));
        //calendar.set(Calendar.MONTH,bundle.getInt("mes"));
        //calendar.set(Calendar.DAY_OF_MONTH,bundle.getInt("dia"));
        Date data_quando = calendar.getTime();

        rd.setDataDiario(dtnow);
        rd.setUso(true);
        rd.setVontade(false);

        long idDiario = DB.save(rd);

        ud.setQuantidade(bundle.getInt("qtd"));
        ud.setTipo(bundle.getInt("tipo"));
        ud.setComQuem(bundle.getInt("usoucom"));
        ud.setMotivo(bundle.getInt("motivouso"));
        ud.setQuando(data_quando);
        ud.setidRelatoDiario((int)idDiario);

        mr.setPublico(false);
        mr.setTexto(bundle.getString("relato"));
        mr.setDataMural(dtnow);
        mr.setidRelatoDiario((int)idDiario);

        DB.save(ud);
        DB.save(mr);

    }

    public void botaoSim(View v){
        Bundle bundle = new Bundle();
        bundle.putBooleanArray("checkbox", array);
        Intent i = new Intent(this, UsouDroga.class);
        i.putExtras(bundle);
        startActivity(i);
        //slide from left to right
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }
    public void botaoNao(View v){
        Bundle bundle = new Bundle();
        bundle.putInt("se_usou",0);
        //Registra no diario
        Intent i = new Intent(this, Fim.class);
        i.putExtras(bundle);
        startActivity(i);
        //slide from right to left
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }
}
