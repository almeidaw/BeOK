package beok.beok;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.orm.SugarContext;
import com.orm.SugarRecord;

import java.util.Date;
import java.util.List;
import java.util.logging.LogRecord;

import beok.beok.POJO.BotaoAtivo;
import beok.beok.POJO.DataTeste;
import beok.beok.POJO.Usuario;
import beok.beok.POJO.Wrapper;
import beok.beok.api.DB;
import beok.beok.api.ServiceSincronizer;

public class PreLogIn extends AppCompatActivity {

    int period = 10000;
    final Handler handler=new Handler();
    ServiceSincronizer sc;

    TextView t;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pre_log_in);

        initialize();

        t=(TextView)findViewById(R.id.textView3);

        handler.postDelayed(runnable, period);

        List<Usuario> us=DB.listAll(Usuario.class);

        if(!us.isEmpty()){
            DB.idUsuario=us.get(0).getId();
            //redireciona para a home
            Intent i=new Intent(this,Home.class);
            startActivity(i);
        }else{
            //vai para a página de login/cadastro
            Intent i=new Intent(this,FirstAccess.class);
            startActivity(i);
        }
    }
    private void initialize(){
        SugarContext.init(this);
        DB.updateQueue();
        Toast.makeText(getApplicationContext(), SugarRecord.listAll(Wrapper.class).size()+"", Toast.LENGTH_SHORT).show();
        sc=new ServiceSincronizer();
        ServiceSincronizer.scContext=getApplicationContext();
    }
    public void clicaCadastra(View v){
        Date dtn=new Date();
        dtn.setTime(System.currentTimeMillis());
        DataTeste dteste=new DataTeste();
        dteste.setDate(dtn);
        dteste.setName("nome");

        DB.save(dteste);
    }
    private Runnable runnable = new Runnable() {

        @Override
        public void run() {
            //Toast.makeText(getApplicationContext(),"pulse", Toast.LENGTH_SHORT).show();
            //Toast.makeText(getApplicationContext(), SugarRecord.listAll(Wrapper.class).size()+"", Toast.LENGTH_SHORT).show();
            DB.flush();
            handler.postDelayed(this, period);
        }
    };
}