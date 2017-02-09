package beok.beok;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.orm.SugarContext;

import java.util.Date;

import beok.beok.POJO.BotaoAtivo;
import beok.beok.api.DB;
import beok.beok.api.ServiceSincronizer;


public class Main extends AppCompatActivity {

    int period = 10000;
    final Handler handler=new Handler();
    ServiceSincronizer sc;

    BottomNavigationView bottomNavigationView;


    Home home_fragment;
    TestFragment tf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initialize();

        handler.postDelayed(runnable, period);

        home_fragment = new Home();
        tf = new TestFragment();

        bottomNavigationView = (BottomNavigationView)findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.diaryItem:
                                Toast.makeText(Main.this, "diário", Toast.LENGTH_SHORT).show();
                                Main.this.colocaFragment(tf, R.id.main_fragment_container);
                                break;

                            case R.id.goalsItem:
                                Toast.makeText(Main.this, "metas", Toast.LENGTH_SHORT).show();
                                Main.this.colocaFragment(tf, R.id.main_fragment_container);
                                break;

                            case R.id.homeItem:
                                Toast.makeText(Main.this, "principal", Toast.LENGTH_SHORT).show();
                                Main.this.colocaFragment(home_fragment, R.id.main_fragment_container);
                                break;

                            case R.id.inspirationItem:
                                Toast.makeText(Main.this, "inspiração", Toast.LENGTH_SHORT).show();
                                Main.this.colocaFragment(tf, R.id.main_fragment_container);
                                break;

                            case R.id.therapyItem:
                                Toast.makeText(Main.this, "terapia", Toast.LENGTH_SHORT).show();
                                Main.this.colocaFragment(tf, R.id.main_fragment_container);
                                break;
                        }
                        return true;
                    }
                });

    }

    private void colocaFragment(Fragment fragment, int container){
        if(fragment.isAdded())
            return;

        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(container, fragment);
        //fragmentTransaction.addToBackStack(null);

        fragmentTransaction.commit();
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

    private void initialize(){
        SugarContext.init(this);
        //Toast.makeText(getApplicationContext(), SugarRecord.listAll(Wrapper.class).size()+"", Toast.LENGTH_SHORT).show();
        sc=new ServiceSincronizer();
        ServiceSincronizer.scContext=this;
    }

    private Runnable runnable = new Runnable() {

        @Override
        public void run() {
            Toast.makeText(getApplicationContext(),"pulse", Toast.LENGTH_SHORT).show();
            //Toast.makeText(getApplicationContext(), SugarRecord.listAll(Wrapper.class).size()+"", Toast.LENGTH_SHORT).show();
            DB.flush();
            handler.postDelayed(this, period);
        }
    };


}