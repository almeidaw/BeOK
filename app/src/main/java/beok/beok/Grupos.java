package beok.beok;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.orm.SugarContext;
import com.orm.SugarRecord;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import beok.beok.POJO.CreatedObjects;
import beok.beok.POJO.GrupoA;
import beok.beok.POJO.Insp;
import beok.beok.POJO.InspServ;
import beok.beok.api.App;
import beok.beok.api.Conf;
import beok.beok.api.DB;
import beok.beok.api.ServiceGenerator;
import beok.beok.webservice.ServiceWS;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by william on 16/02/17.
 */

public class Grupos extends AppCompatActivity implements Callback<List<GrupoA>> {

    Call<List<GrupoA>> call;
    ServiceWS service;

    private RecyclerView rv;
    private List<GruposAANA> grupos;
    Bundle b;
    String zona;
    String tipo;

    NavigationView nv;
    Button btmenu, btpanico;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grupos2);
        rv = (RecyclerView)findViewById(R.id.recycler_view_grupos);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(llm);

        b=getIntent().getExtras();
        tipo=b.getString("tipo");
        zona=b.getString("zona");

        SugarContext.init(this);

        service = ServiceGenerator.createService(ServiceWS.class);
        if(!App.DEBUG)
            callWs();

        initializeData();
        initializeAdapter();

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
                                                         Intent i = new Intent(Grupos.this, Main.class); // Direcionar para tela de dicas efrases motivacionais
                                                         i.putExtras(bundle);
                                                         startActivity(i);

                                                     } else if (id == R.id.nav_metas){
                                                         Bundle bundle = new Bundle();
                                                         bundle.putInt("inicia_fragment", 2);
                                                         Intent i = new Intent(Grupos.this, Main.class); // Direcionar para tela de dicas efrases motivacionais
                                                         i.putExtras(bundle);
                                                         startActivity(i);

                                                     } else if (id == R.id.nav_inspiracao){
                                                         Bundle bundle = new Bundle();
                                                         bundle.putInt("inicia_fragment", 3);
                                                         Intent i = new Intent(Grupos.this, Main.class); // Direcionar para tela de dicas efrases motivacionais
                                                         i.putExtras(bundle);
                                                         startActivity(i);

                                                     } else if (id == R.id.nav_terapia){
                                                         Bundle bundle = new Bundle();
                                                         bundle.putInt("inicia_fragment", 4);
                                                         Intent i = new Intent(Grupos.this, Main.class); // Direcionar para tela de dicas efrases motivacionais
                                                         i.putExtras(bundle);
                                                         startActivity(i);

                                                     } else if (id == R.id.nav_configuracoes) {
                                                         Intent intent = new Intent(Grupos.this, SettingsActivity.class);
                                                         startActivity(intent);

                                                     } else if (id == R.id.nav_info) {
                                                         Intent intent = new Intent(Grupos.this, Informacoes.class);
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
                Intent i = new Intent(Grupos.this, BotaoPanico1.class);
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


    private void initializeData() {
        grupos = new ArrayList<GruposAANA>();

        for(GrupoA grupo:DB.find(GrupoA.class,"tipo = ? and zona = ?",tipo,zona)){
            GruposAANA g=new GruposAANA(grupo.getNome(),grupo.getEndereco(),grupo.getTelefone(),grupo.getDescricao());
            grupos.add(g);
        }
    }

    private void initializeAdapter(){
        GruposAdapter adapter = new GruposAdapter(grupos);
        rv.setAdapter(adapter);
    }
    private void callWs(){
        call = service.getGrupos(tipo,zona);
        call.enqueue(this);
    }

    @Override
    public void onResponse(Call<List<GrupoA>> call, Response<List<GrupoA>> response) {
        if(response.body()!=null) {
            List<GrupoA> grups = response.body();
            for(GrupoA grupo:DB.find(GrupoA.class,"tipo = ? and zona = ?",tipo,zona)){
                DB.delete(grupo);
            }
            for (GrupoA grupo : grups) {
                GruposAANA g=new GruposAANA(grupo.getNome(),grupo.getEndereco(),grupo.getTelefone(),grupo.getDescricao());
                grupos.add(g);
                SugarRecord.save(grupo);
            }
            initializeData();
            initializeAdapter();
        }
    }
    @Override
    public void onFailure(Call<List<GrupoA>> call, Throwable throwable) {
        throwable.printStackTrace();
    }
}

class GruposAdapter extends RecyclerView.Adapter<GruposAdapter.CardViewHolder>{

    public static class CardViewHolder extends RecyclerView.ViewHolder {

        CardView cv;
        TextView nome, site,telefone,descricao;

        CardViewHolder(View itemView) {
            super(itemView);
            cv = (CardView)itemView.findViewById(R.id.card_layout_infos);
            nome = (TextView)itemView.findViewById(R.id.nome_grupo);
            site = (TextView)itemView.findViewById(R.id.site_grupo);
            telefone = (TextView)itemView.findViewById(R.id.telefone_grupo);
            descricao = (TextView)itemView.findViewById(R.id.descricao_grupo);
        }
    }

    List<GruposAANA> grupos;

    GruposAdapter(List<GruposAANA> grupos){
        this.grupos = grupos;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public CardViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_layout_infos, viewGroup, false);
        CardViewHolder cvh = new CardViewHolder(v);
        return cvh;
    }

    @Override
    public void onBindViewHolder(CardViewHolder CardViewHolder, int i) {
        CardViewHolder.nome.setText(grupos.get(i).nome);
        CardViewHolder.site.setClickable(true);
        CardViewHolder.site.setMovementMethod(LinkMovementMethod.getInstance());
        String text = "<a href='http://"+grupos.get(i).site+"'> "+grupos.get(i).site+" </a>";
        CardViewHolder.site.setText(Html.fromHtml(text));
        CardViewHolder.telefone.setText(grupos.get(i).telefone);
        CardViewHolder.descricao.setText(grupos.get(i).descricao);
    }

    @Override
    public int getItemCount() {
        return grupos.size();
    }


}

class GruposAANA {
    protected String nome,site,telefone,descricao;
    GruposAANA(String nome, String site,String telefone,String descricao) {
        this.nome=nome;
        this.site=site;
        this.telefone=telefone;
        this.descricao=descricao;
    }
}