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
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.orm.SugarContext;

import java.util.Date;

import beok.beok.POJO.*;
import beok.beok.POJO.MetaSemanal;
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


        bottomNavigationView = (BottomNavigationView)findViewById(R.id.bottom_navigation);

        nv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
                 // This method will trigger on item Click of navigation menu
                 @Override
                 public boolean onNavigationItemSelected(MenuItem item) {
                     // Handle navigation view item clicks here.
                     int id = item.getItemId();
                     if (id == R.id.nav_diario) {
                         Main.this.colocaFragment (show_diary_fragment, R.id.main_fragment_container);
                         content_main.setBackgroundResource(R.drawable.bg_diario);
                         bottomNavigationView.getMenu().getItem(0).setChecked(true);

                     } else if (id == R.id.nav_metas){
                         Main.this.colocaFragment(fragment_metas_semanal, R.id.main_fragment_container);
                         content_main.setBackgroundResource(R.drawable.bg_metas);
                         bottomNavigationView.getMenu().getItem(1).setChecked(true);


                     } else if (id == R.id.nav_inspiracao){
                         Main.this.colocaFragment(fragment_inspiracao, R.id.main_fragment_container);
                         content_main.setBackgroundResource(R.drawable.bg_inspiracao);
                         bottomNavigationView.getMenu().getItem(2).setChecked(true);


                     } else if (id == R.id.nav_terapia){
                         Main.this.colocaFragment(tf, R.id.main_fragment_container);
                         content_main.setBackgroundResource(R.drawable.bg_terapia);
                         bottomNavigationView.getMenu().getItem(3).setChecked(true);

                     } else if (id == R.id.nav_configuracoes) {
                         beok.beok.POJO.MetaSemanal ms=new MetaSemanal();
                         ms.setTipo(0);
                         ms.setQuantidade(23);
                         ms.setFreqSemanal(2);
                         ms.setManha(true);
                         ms.setMadrugada(false);
                         ms.setNoite(false);
                         ms.setTarde(false);
                         Date now=new Date();
                         now.setTime(System.currentTimeMillis());
                         ms.setSemana(now);
                         DB.save(ms);
                         Intent intent = new Intent(Main.this, SettingsActivity.class);
                         startActivity(intent);

                     } else if (id == R.id.nav_info) {

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

        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        UsoTela ut=new UsoTela();
                        Date now=new Date();
                        now.setTime(System.currentTimeMillis());
                        ut.setLastClicked(now);
                        switch (item.getItemId()) {
                            case R.id.showDiaryItem:
                                //Toast.makeText(Main.this, "diário", Toast.LENGTH_SHORT).show();
                                Main.this.colocaFragment(show_diary_fragment, R.id.main_fragment_container);
                                content_main.setBackgroundResource(R.drawable.bg_diario);
                                nv.getMenu().getItem(0).setChecked(true);
                                ut.setTela(0);
                                break;

                            case R.id.goalsItem:
                                //Toast.makeText(Main.this, "metas", Toast.LENGTH_SHORT).show();
                                Main.this.colocaFragment(fragment_metas_semanal, R.id.main_fragment_container);
                                content_main.setBackgroundResource(R.drawable.bg_metas);
                                nv.getMenu().getItem(1).setChecked(true);
                                ut.setTela(1);
                                break;

                            case R.id.inspirationItem:
                                //Toast.makeText(Main.this, "inspiração", Toast.LENGTH_SHORT).show();
                                Main.this.colocaFragment(fragment_inspiracao, R.id.main_fragment_container);
                                content_main.setBackgroundResource(R.drawable.bg_inspiracao);
                                nv.getMenu().getItem(2).setChecked(true);
                                ut.setTela(2);
                                break;

                            case R.id.therapyItem:
                                //Toast.makeText(Main.this, "terapia", Toast.LENGTH_SHORT).show();
                                Main.this.colocaFragment(tf, R.id.main_fragment_container);
                                content_main.setBackgroundResource(R.drawable.bg_terapia);
                                nv.getMenu().getItem(3).setChecked(true);
                                ut.setTela(3);
                                break;
                        }
                        DB.save(ut);
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