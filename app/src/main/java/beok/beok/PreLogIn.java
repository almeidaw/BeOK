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
import beok.beok.api.App;
import beok.beok.api.DB;
import beok.beok.api.ServiceSincronizer;

public class PreLogIn extends AppCompatActivity {


    TextView t;

    final Handler handler=new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pre_log_in);

        initialize();

        t=(TextView)findViewById(R.id.textView3);


        List<Usuario> us=DB.listAll(Usuario.class);

        if(!us.isEmpty()){
            //redireciona para a home

            Intent i=new Intent(this,Main.class);
            startActivity(i);
        }else{
            handler.postDelayed(mainStart, 3000);
            //vai para página de cadastro
        }
    }
    private void initialize(){
        SugarContext.init(this);
        DB.updateQueue();
    }
    public void clicaCadastra(View v){
        Date dtn=new Date();
        dtn.setTime(System.currentTimeMillis());
        DataTeste dteste=new DataTeste();
        dteste.setDate(dtn);
        dteste.setName("nome");

        DB.save(dteste);
    }

    private Runnable mainStart = new Runnable() {

        @Override
        public void run() {
            Intent i=new Intent(PreLogIn.this,FirstAccess.class);
            startActivity(i);
        }
    };
    @Override
    public void onBackPressed() {

    }

}