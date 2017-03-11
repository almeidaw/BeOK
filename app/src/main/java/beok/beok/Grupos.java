package beok.beok;

import android.app.Fragment;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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