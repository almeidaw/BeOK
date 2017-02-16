package beok.beok;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.orm.SugarContext;

import java.util.Date;

import beok.beok.POJO.BotaoAtivo;
import beok.beok.api.App;
import beok.beok.api.Conf;
import beok.beok.api.DB;
import beok.beok.api.ServiceSincronizer;

import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.widget.Button;


public class Main extends AppCompatActivity {

    Button btmenu, btpanico;

    static boolean b;

    int period = 10000;
    final Handler handler=new Handler();
    ServiceSincronizer sc;

    BottomNavigationView bottomNavigationView;


    Home home_fragment;
    TestFragment tf;
    ShowDiary show_diary_fragment;
    MetasSemana fragment_metas_semanal;
    Inspiracao fragment_inspiracao;
    NavigationView nv;

    TextView texto_navbar;
    RelativeLayout content_main;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btpanico = (Button) findViewById(R.id.btpanico);

        content_main = (RelativeLayout) findViewById(R.id.content_main);

        initialize();

        if(!App.DEBUG)
            handler.postDelayed(startRunnable, period);

        b=false;

        show_diary_fragment = new ShowDiary();
        fragment_metas_semanal = new MetasSemana();
        fragment_inspiracao = new Inspiracao();

        tf = new TestFragment();
        colocaFragment(show_diary_fragment, R.id.main_fragment_container);

        nv=(NavigationView)findViewById(R.id.nav_view);

        texto_navbar=(TextView)findViewById(R.id.texto_navbar);
        //texto_navbar.setText(Conf.getNomeUsuario());

        nv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

                                                 // This method will trigger on item Click of navigation menu
                                                 @Override
                                                 public boolean onNavigationItemSelected(MenuItem item) {
                                                     // Handle navigation view item clicks here.
                                                     int id = item.getItemId();
                                                     if (id == R.id.nav_acompanhamento) {
                                                         // Handle the "acompanhamento" action

                                                     } else if (id == R.id.nav_configuracoes) {

                                                        Intent intent = new Intent(Main.this, SettingsActivity.class);
                                                        startActivity(intent);

                                                     } else if (id == R.id.nav_info) {

                                                     } else if (id == R.id.nav_diario){

                                                     } else if (id == R.id.nav_inspiracao){

                                                     } else if (id == R.id.nav_metas){

                                                     } else if (id == R.id.nav_terapia){

                                                     } else if (id == R.id.nav_favoritos){

                                                     }

                                                     DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                                                     drawer.closeDrawer(GravityCompat.START);
                                                     return true;
                                                 }
                                             }
                );



        btpanico.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Main.this, BotaoPanico1.class);
                startActivity(i);
            }
        });

        final DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        btmenu = (Button) findViewById(R.id.btmenu);

        btmenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                drawer.openDrawer(GravityCompat.START);

            }
        });

        bottomNavigationView = (BottomNavigationView)findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.showDiaryItem:
                                //Toast.makeText(Main.this, "diário", Toast.LENGTH_SHORT).show();
                                Main.this.colocaFragment(show_diary_fragment, R.id.main_fragment_container);
                                content_main.setBackgroundResource(R.drawable.bg_diario);
                                break;

                            case R.id.goalsItem:
                                //Toast.makeText(Main.this, "metas", Toast.LENGTH_SHORT).show();
                                Main.this.colocaFragment(fragment_metas_semanal, R.id.main_fragment_container);
                                content_main.setBackgroundResource(R.drawable.bg_metas);
                                break;

                            case R.id.inspirationItem:
                                //Toast.makeText(Main.this, "inspiração", Toast.LENGTH_SHORT).show();
                                Main.this.colocaFragment(tf, R.id.main_fragment_container);
                                content_main.setBackgroundResource(R.drawable.bg_inspiracao);
                                break;

                            case R.id.therapyItem:
                                //Toast.makeText(Main.this, "terapia", Toast.LENGTH_SHORT).show();
                                Main.this.colocaFragment(tf, R.id.main_fragment_container);
                                content_main.setBackgroundResource(R.drawable.bg_terapia);
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


    private Runnable startRunnable = new Runnable() {

        @Override
        public void run() {
            if(App.VERB)
                Toast.makeText(getApplicationContext(),"pulse", Toast.LENGTH_SHORT).show();
            DB.flush();
            b=true;
            handler.postDelayed(runnable, period);
        }
    };

    private Runnable runnable = new Runnable() {

        @Override
        public void run() {
            if(b) {
                if(App.VERB)
                    Toast.makeText(getApplicationContext(),"pulse", Toast.LENGTH_SHORT).show();
                DB.flush();

                handler.postDelayed(this, period);
            }
        }
    };

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            //super.onBackPressed();
        }
    }

}