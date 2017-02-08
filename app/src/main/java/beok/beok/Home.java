package beok.beok;

import android.app.Fragment;
import android.content.Intent;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import beok.beok.api.ServiceSincronizer;


public class Home extends Fragment {

    TextView txt11, txt12, txt21, txt22, txt31, txt32, txt41, txt42, txt51, txt52, txt61, txt62;


    @Nullable
    int period = 10000;
    final Handler handler=new Handler();
    ServiceSincronizer sc;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_home, container, false);

/*        setContentView(R.layout.fragment_home);
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

       // Usuario u=SugarRecord.listAll(Usuario.class).get(0);
       // Toast.makeText(this,"ID ext e interno é "+ u.getId(),Toast.LENGTH_LONG).show();


        /*
        * TODO:
        * Recuperar dos bancos de dados os valores que aparecerão nos TextViews acima
        * (alguns dados armazenados no local, outros armazenados no remoto)
        * DEVE SER FEITO NO ONCREATE()
        * */

        initialize();

        handler.postDelayed(runnable, period);
        return v;
    }



    private void initialize(){
        SugarContext.init(getActivity());
        //Toast.makeText(getApplicationContext(), SugarRecord.listAll(Wrapper.class).size()+"", Toast.LENGTH_SHORT).show();
        sc=new ServiceSincronizer();
        ServiceSincronizer.scContext=getActivity();
    }

    private Runnable runnable = new Runnable() {

        @Override
        public void run() {
            Toast.makeText(getActivity(),"pulse", Toast.LENGTH_SHORT).show();
            //Toast.makeText(getApplicationContext(), SugarRecord.listAll(Wrapper.class).size()+"", Toast.LENGTH_SHORT).show();
            DB.flush();
            handler.postDelayed(this, period);
        }
    };
}
