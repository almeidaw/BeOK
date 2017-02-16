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
 * Created by william on 12/02/17.
 */

public class ShowDiary extends Fragment {

    private RecyclerView rv;
    private List<Droga> drogas;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        List<MetaGeral> metas = DB.listAll(MetaGeral.class);
        View v;
        if(metas.isEmpty()){
            v = inflater.inflate(R.layout.fragment_show_diary_empty,container,false);
        }else {
            v = inflater.inflate(R.layout.fragment_show_diary, container, false);


            rv = (RecyclerView) v.findViewById(R.id.recycler_view);
            LinearLayoutManager llm = new LinearLayoutManager(v.getContext());
            rv.setHasFixedSize(true);
            rv.setLayoutManager(llm);

            initializeData();
            initializeAdapter();
        }
        return v;
    }

    private void initializeData() { // Substituir por dados do servidor
        drogas = new ArrayList<>();

        List<MetaSemanal> metas = DB.listAll(MetaSemanal.class);
        for(MetaSemanal meta : metas){
            drogas.add(new Droga(convertTipos(meta.getTipo()),meta.getQuantidade(),meta.getFreqSemanal(),meta.getManha(),meta.getTarde(),meta.getNoite(),meta.getMadrugada(),new ArrayList<DataPoint>()));
        }

        List<UsoDroga> uds = DB.listAll(UsoDroga.class);

        for(UsoDroga ud:uds){
            for(Droga droga:drogas){
                if(droga.tipo==convertTipos(ud.getTipo())){
                    droga.dados.add(new DataPoint(droga.dados.size(),ud.getQuantidade()));
                }
            }
        }

        List<DataPoint> series = new ArrayList<DataPoint>();
        //drogas.add(new Droga("Maconha", 3, 2, "manhã\ntarde", 36,series));
        //drogas.add(new Droga("Crack", 2, 3, "manhã\nnoite", 58,series));
    }

    private void initializeAdapter(){
        DiarioAdapter adapter = new DiarioAdapter(drogas);
        rv.setAdapter(adapter);
    }
    private int convertTipos(int tipo){
        if(tipo==0 || tipo==1 || tipo==2){
            return 0;
        }else{
            return tipo;
        }
    }
}

class DiarioAdapter extends RecyclerView.Adapter<DiarioAdapter.CardViewHolder>{

    public static class CardViewHolder extends RecyclerView.ViewHolder {

        CardView cv;
        TextView nomeDroga, quantidade, periodo, horario, economia,n_relato_diario;
        GraphView graph;

        CardViewHolder(View itemView) {
            super(itemView);
            cv = (CardView)itemView.findViewById(R.id.card_view);
            graph = (GraphView) itemView.findViewById(R.id.graph);
            nomeDroga = (TextView)itemView.findViewById(R.id.nome_droga);
            quantidade = (TextView)itemView.findViewById(R.id.quantidade);
            periodo = (TextView)itemView.findViewById(R.id.periodo);
            horario = (TextView)itemView.findViewById(R.id.horario);
            economia = (TextView)itemView.findViewById(R.id.economia);
            n_relato_diario = (TextView)itemView.findViewById(R.id.texto_n_relato);

        }
    }

    List<Droga> drogas;

    DiarioAdapter(List<Droga> drogas){
            this.drogas = drogas;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public CardViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cardview_layout, viewGroup, false);
        CardViewHolder cvh = new CardViewHolder(v);
        return cvh;
    }

    @Override
    public void onBindViewHolder(CardViewHolder CardViewHolder, int i) {
            Droga droga=drogas.get(i);
            CardViewHolder.nomeDroga.setText(getNomeDroga(droga.tipo));
            CardViewHolder.quantidade.setText(droga.quantidade+"");
            CardViewHolder.periodo.setText(droga.frequencia);
           // CardViewHolder.horario.setText(droga.horario);
            //CardViewHolder.economia.setText(droga.economia);
            if(droga.dados.isEmpty()){
                CardViewHolder.graph.setVisibility(View.INVISIBLE);
                CardViewHolder.n_relato_diario.setVisibility(View.VISIBLE);

            }else{
                CardViewHolder.n_relato_diario.setVisibility(View.INVISIBLE);
                LineGraphSeries<DataPoint> ln = new LineGraphSeries<>();
                for (DataPoint dp : droga.dados) {
                    ln.appendData(dp, true, droga.dados.size());
                }

                CardViewHolder.graph.addSeries(ln);
            }
    }

    @Override
    public int getItemCount() {
        return drogas.size();
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

class Droga { // Objeto droga e construtor
    String quantidade, frequencia;
    boolean manha, tarde, noite, madrugada;
    int tipo;
    List<DataPoint> dados;

    Droga(int tipo, float quantidade, int frequencia, boolean manha, boolean tarde, boolean noite, boolean madrugada, List<DataPoint> dados) {
        this.tipo = tipo;
        this.quantidade = quantidade + " baseados";
        this.frequencia = frequencia + " vezes\npor semana";
        this.manha = manha;
        this.tarde = tarde;
        this.noite = noite;
        this.madrugada = madrugada;
        this.dados = dados;
    }
}
