package beok.beok;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by william on 12/02/17.
 */

public class Diario extends AppCompatActivity {

    private RecyclerView rv;
    private List<Droga> drogas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diario);

        rv = (RecyclerView)findViewById(R.id.recycler_view);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(llm);

        initializeData();
        initializeAdapter();
    }

    private void initializeData() { // Substituir por dados do servidor
        drogas = new ArrayList<>();
        drogas.add(new Droga("Maconha", 3, 2, "manhã\ntarde", 36));
        drogas.add(new Droga("Crack", 2, 3, "manhã\nnoite", 58));
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

        CardViewHolder(View itemView) {
            super(itemView);
            cv = (CardView)itemView.findViewById(R.id.card_view);
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
    }

    @Override
    public int getItemCount() {
        return drogas.size();
    }
}

class Droga { // Objeto droga e construtor
    String nome, quantidade, periodo, horario, economia;

    Droga(String nome, int quantidade, int periodo, String horario, int economia) {
        this.nome = nome;
        this.quantidade = quantidade+" baseados";
        this.periodo = periodo+" vezes\npor semana";
        this.horario = horario;
        this.economia = "R$"+economia+",00 economizados";

    }
}