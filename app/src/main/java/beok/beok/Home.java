package beok.beok;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.orm.SugarContext;
import com.orm.SugarRecord;

import org.w3c.dom.Text;

import java.util.Date;
import java.util.List;

import beok.beok.POJO.BotaoAtivo;
import beok.beok.POJO.DataTeste;
import beok.beok.POJO.Usuario;
import beok.beok.POJO.Wrapper;
import beok.beok.api.DB;


public class Home extends AppCompatActivity {

    TextView txt11, txt12, txt21, txt22, txt31, txt32, txt41, txt42, txt51, txt52, txt61, txt62;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        txt11 = (TextView) findViewById(R.id.txt1_1);
        txt12 = (TextView) findViewById(R.id.txt1_2);
        txt21 = (TextView) findViewById(R.id.txt2_1);
        txt22 = (TextView) findViewById(R.id.txt2_2);
        txt31 = (TextView) findViewById(R.id.txt3_1);
        txt32 = (TextView) findViewById(R.id.txt3_2);
       // txt41 = (TextView) findViewById(R.id.txt4_1);
        //txt42 = (TextView) findViewById(R.id.txt4_2);
        txt51 = (TextView) findViewById(R.id.txt5_1);
        txt52 = (TextView) findViewById(R.id.txt5_2);
        txt61 = (TextView) findViewById(R.id.txt6_1);
        txt62 = (TextView) findViewById(R.id.txt6_2);
        SugarContext.init(this);
       // Usuario u=SugarRecord.listAll(Usuario.class).get(0);
       // Toast.makeText(this,"ID ext e interno é "+ u.getId(),Toast.LENGTH_LONG).show();


        /*
        * TODO:
        * Recuperar dos bancos de dados os valores que aparecerão nos TextViews acima
        * (alguns dados armazenados no local, outros armazenados no remoto)
        * DEVE SER FEITO NO ONCREATE()
        * */
    }
    public void saveClick(View v){
        Date dtn=new Date();
        dtn.setTime(System.currentTimeMillis());
        BotaoAtivo btA=new BotaoAtivo();
        btA.setMotivo(true);
        btA.setOQueFez(5);
        btA.setDataAtivo(dtn);

        DB.save(btA);
    }
    public void deslogaClick(View v){
       // DBLocal db=new DBLocal(this);
       // db.resetaTabela();
        Intent i = new Intent(this, Cadastro.class);
        startActivity(i);
    }
}
