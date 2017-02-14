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

import java.util.ArrayList;
import java.util.List;

/**
 * Created by william on 12/02/17.
 */

public class ShowDiary extends Fragment {

    private RecyclerView rv;
    private List<Droga> drogas;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_show_diary, container, false);

        rv = (RecyclerView)v.findViewById(R.id.recycler_view);
        LinearLayoutManager llm = new LinearLayoutManager(v.getContext());
        rv.setHasFixedSize(true);
        rv.setLayoutManager(llm);

        initializeData();
        initializeAdapter();
        return v;
    }

    private void initializeData() { // Substituir por dados do servidor
        drogas = new ArrayList<>();
        List<DataPoint> series = new ArrayList<DataPoint>();
        series.add(new DataPoint(0,1));
        series.add(new DataPoint(1,5));
        series.add(new DataPoint(2,3));
        series.add(new DataPoint(3,2));
        series.add(new DataPoint(4,6));
        drogas.add(new Droga("Maconha", 3, 2, "manhã\ntarde", 36,series));
        drogas.add(new Droga("Crack", 2, 3, "manhã\nnoite", 58,series));
    }

    private void initializeAdapter(){
        DiarioAdapter adapter = new DiarioAdapter(drogas);
        rv.setAdapter(adapter);
    }
}

class DiarioAdapter extends RecyclerView.Adapter<DiarioAdapter.CardViewHolder>{

    public static class CardViewHolder extends RecyclerView.ViewHolder {

        CardView cv;
        TextView nomeDroga, quantidade, periodo, horario, economia;
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
        CardViewHolder.nomeDroga.setText(drogas.get(i).nome);
        CardViewHolder.quantidade.setText(drogas.get(i).quantidade);
        CardViewHolder.periodo.setText(drogas.get(i).periodo);
        CardViewHolder.horario.setText(drogas.get(i).horario);
        CardViewHolder.economia.setText(drogas.get(i).economia);
        LineGraphSeries<DataPoint> ln=new LineGraphSeries<>();
        for(DataPoint dp : drogas.get(i).dados) {
            ln.appendData(dp,true,drogas.get(i).dados.size());
        }
        CardViewHolder.graph.addSeries(ln);
    }

    @Override
    public int getItemCount() {
        return drogas.size();
    }
}

class Droga { // Objeto droga e construtor
    String nome, quantidade, periodo, horario, economia;
    List<DataPoint> dados;
    Droga(String nome, int quantidade, int periodo, String horario, int economia, List<DataPoint> dados) {
        this.nome = nome;
        this.quantidade = quantidade+" baseados";
        this.periodo = periodo+" vezes\npor semana";
        this.horario = horario;
        this.economia = "R$"+economia+",00 economizados";
        this.dados=dados;

    }
}
