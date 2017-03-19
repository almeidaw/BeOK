package beok.beok;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;
import android.widget.Toast;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.helper.DateAsXAxisLabelFormatter;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;
import com.orm.SugarContext;
import com.orm.SugarRecord;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import beok.beok.POJO.ConsumoAtual;
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


            rv = (RecyclerView) v.findViewById(R.id.recycler_view_diary);
            LinearLayoutManager llm = new LinearLayoutManager(v.getContext());
            rv.setHasFixedSize(true);
            rv.setLayoutManager(llm);

            initializeData();
            initializeAdapter();
        }
        //ADICIONA RELATO DIÁRIO
        FloatingActionButton fab = (FloatingActionButton) v.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getActivity(),Diary1.class);
                startActivity(i);
            }
        });
        return v;
    }

    private void initializeData() { // Substituir por dados do servidor
        drogas = new ArrayList<>();

        List<MetaSemanal> metas = DB.listAll(MetaSemanal.class,"id Desc");

        int nDrogas=DB.listAll(ConsumoAtual.class).size();

        int gastoOriginal[]=new int[nDrogas];
        int gastoReal[]=new int[nDrogas];
        int j=0;
        for(ConsumoAtual ca:DB.listAll(ConsumoAtual.class)){
            int diasPorSemana=0;
            switch (ca.getFreqSemanal()){
                case 3:
                    diasPorSemana=4;
                    break;
                case 4:
                    diasPorSemana=7;
                    break;
                case 5:
                    diasPorSemana=2;
                    break;
                default:
                    diasPorSemana=ca.getFreqSemanal();
                    break;
            }
            Date now=new Date();
            now.setTime(System.currentTimeMillis());
            Date then=ca.getDataInicio();
            float delta_t=(now.getTime()-then.getTime())/(7*24*60*60*1000);
            gastoOriginal[j]=(int)(diasPorSemana*ca.getQuantidade()*ca.getGasto()*delta_t);
            List<UsoDroga> ud=DB.listAll(UsoDroga.class);
            for(UsoDroga u:ud){
                if(convertTipos(u.getTipo())==convertTipos(ca.getTipo())){
                    gastoReal[j]+=(int)(u.getQuantidade()*ca.getGasto());
                }
            }
            j++;
        }
        for(int i=0;i<nDrogas;i++) {
            MetaSemanal meta=metas.get(i);
            int economia=gastoOriginal[i]-gastoReal[i];
            drogas.add(new Droga(convertTipos(meta.getTipo()),meta.getQuantidade(),meta.getFreqSemanal(),meta.getManha(),meta.getTarde(),meta.getNoite(),meta.getMadrugada(),economia,new ArrayList<DataPoint>()));
        }

        List<UsoDroga> uds = DB.listAll(UsoDroga.class);

        for(UsoDroga ud:uds){
            for(Droga droga:drogas){
                if(droga.tipo==convertTipos(ud.getTipo())){
                    droga.dados.add(new DataPoint(ud.getQuando(),ud.getQuantidade()));
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
        TextView nomeDroga, quantidade, periodo, horario, economia,n_relato_diario,quantidade_unidade,meta_texto;
        GraphView graph;

        CardViewHolder(View itemView) {
            super(itemView);
            cv = (CardView)itemView.findViewById(R.id.card_view_diary);
            graph = (GraphView) itemView.findViewById(R.id.graph);
            nomeDroga = (TextView)itemView.findViewById(R.id.nome_droga);
            quantidade = (TextView)itemView.findViewById(R.id.quantidade);
            periodo = (TextView)itemView.findViewById(R.id.periodo);
            horario = (TextView)itemView.findViewById(R.id.horario);
            economia = (TextView)itemView.findViewById(R.id.economia);
            quantidade_unidade = (TextView)itemView.findViewById(R.id.gramas);
            n_relato_diario = (TextView)itemView.findViewById(R.id.texto_n_relato);
            meta_texto=(TextView)itemView.findViewById(R.id.meta_texto);

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
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_layout_show_diary, viewGroup, false);
        CardViewHolder cvh = new CardViewHolder(v);
        return cvh;
    }

    @Override
    public void onBindViewHolder(CardViewHolder CardViewHolder, int i) {
        Droga droga=drogas.get(i);
        if(droga.abstinencia) {
            CardViewHolder.nomeDroga.setText(getNomeDroga(droga.tipo));
            CardViewHolder.meta_texto.setText("Nessa semana, eu não consumirei mais");
            CardViewHolder.quantidade.setVisibility(View.GONE);
            CardViewHolder.periodo.setVisibility(View.GONE);
            CardViewHolder.horario.setVisibility(View.GONE);
        }else {
            CardViewHolder.nomeDroga.setText(getNomeDroga(droga.tipo));
            CardViewHolder.quantidade.setText(droga.quantidade);
            CardViewHolder.periodo.setText(droga.frequencia);
            CardViewHolder.horario.setText(droga.horario);
        }
        CardViewHolder.economia.setText(droga.economia);
        CardViewHolder.quantidade_unidade.setText("Quantidade em "+droga.unidade);
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

            List<UsoDroga> usoDrogaList = DB.listAll(UsoDroga.class);
            List<Date> datas = new ArrayList<>();
            for (UsoDroga ud : usoDrogaList){
                datas.add(ud.getQuando());
            }

            // ------------- Formatacao do grafico -----------------
            CardViewHolder.graph.getGridLabelRenderer().setLabelFormatter(new DateAsXAxisLabelFormatter(CardViewHolder.graph.getContext()));
            CardViewHolder.graph.getGridLabelRenderer().setNumHorizontalLabels(3);

            CardViewHolder.graph.getViewport().setMinY(0);
            CardViewHolder.graph.getViewport().setYAxisBoundsManual(true);

            if (datas.size() > 2){
                CardViewHolder.graph.getViewport().setMinX(datas.get(datas.size() - 3).getTime());
                CardViewHolder.graph.getViewport().setMaxX(datas.get(datas.size() - 1).getTime());
            } else {
                CardViewHolder.graph.setVisibility(View.GONE);
            }
            CardViewHolder.graph.getViewport().setXAxisBoundsManual(true);

            CardViewHolder.graph.getGridLabelRenderer().setTextSize(25);

            CardViewHolder.graph.getViewport().setScrollable(true);
            CardViewHolder.graph.getGridLabelRenderer().setHumanRounding(false); //Não sei o que isso faz nem sei se é necessário
            //-------------- // -----------------------------------------
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
            case 1:
                return "Álcool";
            case 2:
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
    String quantidade, frequencia, horario, economia,unidade;
    int tipo;
    List<DataPoint> dados;

    boolean abstinencia;

    Droga(int tipo, float quantidade, int frequencia, boolean manha, boolean tarde, boolean noite, boolean madrugada,int economia, List<DataPoint> dados) {
        this.tipo = tipo;
        if(frequencia==0){
            abstinencia=true;
        }else{
            abstinencia=false;
        }
        switch(tipo){
            case 0:
                this.quantidade = quantidade + " doses(cerveja)";
                this.unidade="doses";
                break;
            case 1:
                this.quantidade = quantidade + " doses(vinho)";
                this.unidade="doses";
                break;
            case 2:
                this.quantidade = quantidade + " doses(destilado)";
                this.unidade="doses";
                break;
            case 3:
                this.quantidade = quantidade + " baseados";
                this.unidade="baseados";
                break;
            case 4:
                this.quantidade = quantidade + " gramas";
                this.unidade="gramas";
                break;
            case 5:
                this.quantidade = quantidade + " pedras";
                this.unidade="pedras";
                break;
        }

        switch(frequencia){
            case 1:
                this.frequencia = "1 dia\n por semana";
                break;
            case 2:
                this.frequencia = "2 dias\n por semana";
                break;
            case 3:
                this.frequencia = "3 a 5 dias\n por semana";
                break;
            case 4:
                this.frequencia = "Todos os dias";
                break;
            case 5:
                this.frequencia = "Só fins\n de semana";
                break;
        }
        if(manha && tarde && noite && madrugada){
            this.horario="Qualquer horário";
        }else if(manha && tarde && noite){
            this.horario="O dia todo";
        }else{
            this.horario="";
            if(manha){
                this.horario+="manha\n";
            }
            if(tarde){
                this.horario+="tarde\n";
            }
            if(noite){
                this.horario+="noite\n";
            }
            if(manha){
                this.horario+="madrugada\n";
            }
        }
        if(economia>0)
            this.economia = "Cerca de R$"+economia+",00 economizados no total";
        else
            this.economia = "Cerca de R$"+(-economia)+",00 gastos a mais no total";
        this.dados = dados;
    }
}
