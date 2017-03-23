package beok.beok;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import beok.beok.api.Conf;

public class GruposZona extends AppCompatActivity {
    Bundle b;

    NavigationView nv;
    Button btmenu, btpanico;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grupos);
        b=getIntent().getExtras();


        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        View v = navigationView.getHeaderView(0);
        TextView nome_navbar = (TextView) v.findViewById(R.id.nome_navbar);
        nome_navbar.setText(Conf.getNomeUsuario());

        btpanico = (Button) findViewById(R.id.btpanico);

        nv=(NavigationView)findViewById(R.id.nav_view);

        nv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
                                                 // This method will trigger on item Click of navigation menu
                                                 @Override
                                                 public boolean onNavigationItemSelected(MenuItem item) {
                                                     // Handle navigation view item clicks here.
                                                     int id = item.getItemId();
                                                     if (id == R.id.nav_diario) {
                                                         Bundle bundle = new Bundle();
                                                         bundle.putInt("inicia_fragment", 1);
                                                         Intent i = new Intent(GruposZona.this, Main.class); // Direcionar para tela de dicas efrases motivacionais
                                                         i.putExtras(bundle);
                                                         startActivity(i);

                                                     } else if (id == R.id.nav_metas){
                                                         Bundle bundle = new Bundle();
                                                         bundle.putInt("inicia_fragment", 2);
                                                         Intent i = new Intent(GruposZona.this, Main.class); // Direcionar para tela de dicas efrases motivacionais
                                                         i.putExtras(bundle);
                                                         startActivity(i);

                                                     } else if (id == R.id.nav_inspiracao){
                                                         Bundle bundle = new Bundle();
                                                         bundle.putInt("inicia_fragment", 3);
                                                         Intent i = new Intent(GruposZona.this, Main.class); // Direcionar para tela de dicas efrases motivacionais
                                                         i.putExtras(bundle);
                                                         startActivity(i);

                                                     } else if (id == R.id.nav_terapia){
                                                         Bundle bundle = new Bundle();
                                                         bundle.putInt("inicia_fragment", 4);
                                                         Intent i = new Intent(GruposZona.this, Main.class); // Direcionar para tela de dicas efrases motivacionais
                                                         i.putExtras(bundle);
                                                         startActivity(i);

                                                     } else if (id == R.id.nav_configuracoes) {
                                                         Intent intent = new Intent(GruposZona.this, SettingsActivity.class);
                                                         startActivity(intent);

                                                     } else if (id == R.id.nav_info) {
                                                         Intent intent = new Intent(GruposZona.this, Informacoes.class);
                                                         startActivity(intent);
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
                Intent i = new Intent(GruposZona.this, BotaoPanico1.class);
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

    }
    public void zs(View v){
        b.putString("zona","0");
        Intent i = new Intent(this,Grupos.class);
        i.putExtras(b);
        startActivity(i);
    }
    public void zn(View v){
        b.putString("zona","1");
        Intent i = new Intent(this,Grupos.class);
        i.putExtras(b);
        startActivity(i);
    }
    public void zl(View v){
        b.putString("zona","2");
        Intent i = new Intent(this,Grupos.class);
        i.putExtras(b);
        startActivity(i);

    }
    public void zo(View v){
        b.putString("zona","3");
        Intent i = new Intent(this,Grupos.class);
        i.putExtras(b);
        startActivity(i);
    }
    public void zc(View v){
        b.putString("zona","4");
        Intent i = new Intent(this,Grupos.class);
        i.putExtras(b);
        startActivity(i);
    }

}
