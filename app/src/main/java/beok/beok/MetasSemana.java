package beok.beok;

import android.app.Fragment;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;
import com.orm.SugarContext;
import com.orm.SugarRecord;

import java.util.ArrayList;
import java.util.List;

import beok.beok.POJO.MetaGeral;
import beok.beok.POJO.MetaSemanal;
import beok.beok.POJO.UsoDroga;
import beok.beok.api.DB;

/**
 * Created by erick on 16/02/17.
 */

public class MetasSemana extends Fragment {
    private RecyclerView rv;
    private List<Metas> metas_semanal;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        List<MetaGeral> metas = DB.listAll(MetaGeral.class);
        View v;
        v = inflater.inflate(R.layout.fragment_metas_semanal, container, false);
        rv = (RecyclerView) v.findViewById(R.id.recycler_view);
        LinearLayoutManager llm = new LinearLayoutManager(v.getContext());
        rv.setHasFixedSize(true);
        rv.setLayoutManager(llm);

        initializeData();
        initializeAdapter();

        return v;
    }

    private void initializeData() { // Substituir por dados do servidor
        metas_semanal = new ArrayList<>();

        List<MetaSemanal> metas = DB.listAll(MetaSemanal.class);
        for(MetaSemanal meta : metas){
            metas_semanal.add(new Metas(meta.getTipo(),meta.getQuantidade(),meta.getFreqSemanal(),meta.getManha(),meta.getTarde(),meta.getNoite(),meta.getMadrugada()));
        }

    }

    private void initializeAdapter(){
        MetaAdapter adapter = new MetaAdapter(metas_semanal);
        rv.setAdapter(adapter);
    }
}

class MetaAdapter extends RecyclerView.Adapter<MetaAdapter.CardViewHolder>{

    public static class CardViewHolder extends RecyclerView.ViewHolder {

        CardView cv;
        TextView nomeDroga, quantidade, frequencia, manha, tarde, noite, madrugada, tempo_restante;

        CardViewHolder(View itemView) {
            super(itemView);
            cv = (CardView)itemView.findViewById(R.id.card_view);
            nomeDroga = (TextView)itemView.findViewById(R.id.nome_droga);
            quantidade = (TextView)itemView.findViewById(R.id.quantidade);
            frequencia = (TextView)itemView.findViewById(R.id.frequencia);
            manha = (TextView)itemView.findViewById(R.id.manha);
            tarde = (TextView)itemView.findViewById(R.id.tarde);
            noite = (TextView)itemView.findViewById(R.id.noite);
            madrugada = (TextView)itemView.findViewById(R.id.madrugada);
            tempo_restante = (TextView) itemView.findViewById(R.id.tempo_restante);

        }
    }

    List<Metas> metas_semanal;

    MetaAdapter(List<Metas> metas){
        this.metas_semanal = metas;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public CardViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cardview_layout_metas, viewGroup, false);
        CardViewHolder cvh = new CardViewHolder(v);
        return cvh;
    }

    @Override
    public void onBindViewHolder(CardViewHolder CardViewHolder, int i) {
        Metas meta = metas_semanal.get(i);
        CardViewHolder.nomeDroga.setText(getNomeDroga(meta.tipo));
        CardViewHolder.quantidade.setText(meta.quantidade+"");
        //CardViewHolder.frequencia.setText(meta.frequencia);
       /* if (meta.manha){CardViewHolder.manha.setVisibility(View.VISIBLE);}
        if (meta.manha){CardViewHolder.tarde.setVisibility(View.VISIBLE);}
        if (meta.manha){CardViewHolder.noite.setVisibility(View.VISIBLE);}
        if (meta.manha){CardViewHolder.madrugada.setVisibility(View.VISIBLE);}*/
        //FALTA COLOCAR QUANTO TEMPO RESTA PARA META DA SEMANA
    }

    @Override
    public int getItemCount() {
        return metas_semanal.size();
    }

    public String getNomeDroga(int tipo){
        switch (tipo){
            case 0:
                return "Álcool";
            case 3:
                return "Maconha";
            case 4:
                return "Cocaína";
            case 5:
                return "Crack";
        }
        return "";
    }
}

class Metas { // Objeto droga e construtor
    String quantidade, frequencia;
    boolean fimdesemana, manha, tarde, noite, madrugada;
    int tipo;

    Metas(int tipo, float quantidade, int frequencia, boolean manha, boolean tarde, boolean noite, boolean madrugada) {
        this.tipo = tipo;
        this.fimdesemana = fimdesemana;
        this.quantidade = quantidade + " baseados";
        this.frequencia = frequencia + " vezes\npor semana";
        this.manha = manha;
        this.tarde = tarde;
        this.noite = noite;
        this.madrugada = madrugada;
    }
}
