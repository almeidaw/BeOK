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
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;
import com.orm.SugarContext;
import com.orm.SugarRecord;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import beok.beok.POJO.ConsumoAtual;
import beok.beok.POJO.MetaGeral;
import beok.beok.POJO.MetaSemGeral;
import beok.beok.POJO.MetaSemanal;
import beok.beok.POJO.UsoDroga;
import beok.beok.POJO.Usuario;
import beok.beok.api.DB;

/**
 * Created by erick on 16/02/17.
 */

public class MetasSemana extends Fragment {
    private RecyclerView rv;
    private List<Meta> metas_semanal;

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
        metas_semanal = new ArrayList<Meta>();

      /*  Usuario u=new Usuario();
        u.setNome("jasdj");

        DB.save(u);

        List<UsoDroga> usoDrogas=DB.listAll(UsoDroga.class);

        DataTeste dt=new DataTeste();
        dt.setNameTeste("nomeee");

        Calendar c = Calendar.getInstance();
        c.set(2017, 1, 3, 20, 30,0);
        dt.setDataHora(c.getTime());
        dt.setBoolTeste(true);


        c.set(2017, 1, 3, 20, 29,59);
        Date minDate=c.getTime();

        c.set(2017, 1, 3, 20, 20,01);
        Date maxDate=c.getTime();

        Date data=new Date();

        data.setTime(System.currentTimeMillis());


        List<DataTeste> result = SugarRecord.findWithQuery(DataTeste.class,
                "SELECT * FROM data_teste WHERE data_hora >= ?",
                minDate.getTime() + "");*/

        //Calculo do gasto
        int nDrogas=DB.listAll(ConsumoAtual.class).size();



        List<MetaGeral> metasGerais = DB.listAll(MetaGeral.class,"id Desc");
        List<MetaSemanal> metas = DB.listAll(MetaSemanal.class, "id Desc");
       for(int i=0;i<nDrogas;i++) {
           MetaGeral meta1 = metasGerais.get(i);
           metas_semanal.add(new Meta(meta1.getTipo(), meta1.getQuantidade(), meta1.getFreqSemanal(), meta1.getManha(), meta1.getTarde(), meta1.getNoite(), meta1.getMadrugada(), 0, null));
           MetaSemanal meta2 = metas.get(i);
           metas_semanal.add(new Meta(meta2.getTipo(), meta2.getQuantidade(), meta2.getFreqSemanal(), meta2.getManha(), meta2.getTarde(), meta2.getNoite(), meta2.getMadrugada(), 1, null));
       }

        List<MetaSemGeral> metasg = DB.listAll(MetaSemGeral.class,"id Desc");
        MetaSemGeral meta3=metasg.get(0);
        metas_semanal.add(new Meta(0,0,0,false,false,false,false,2,meta3.getTexto()));

    }

    private void initializeAdapter(){
        MetaAdapter adapter = new MetaAdapter(metas_semanal);
        rv.setAdapter(adapter);
    }
}

class MetaAdapter extends RecyclerView.Adapter<MetaAdapter.CardViewHolder>{

    public static class CardViewHolder extends RecyclerView.ViewHolder {

        CardView cv;
        TextView nomeTipoMeta, quantidade, meta_tratamento_metas, frequencia, horario,meta_texto_desc;
        LinearLayout layout_tipo;
        View box_metas;

        CardViewHolder(View itemView) {
            super(itemView);
            cv = (CardView)itemView.findViewById(R.id.card_view);
            nomeTipoMeta = (TextView)itemView.findViewById(R.id.nome_tipo_meta);
            quantidade = (TextView)itemView.findViewById(R.id.quantidade_metas);
            meta_tratamento_metas=(TextView)itemView.findViewById(R.id.meta_tratamento_metas);
            frequencia = (TextView)itemView.findViewById(R.id.frequencia_metas);
            horario = (TextView)itemView.findViewById(R.id.horario_metas);
            layout_tipo=(LinearLayout)itemView.findViewById(R.id.layout_tipo01);
            meta_texto_desc=(TextView)itemView.findViewById(R.id.txt_metas_desc);
            box_metas=itemView.findViewById(R.id.view_box_metas);

        }
    }

    List<Meta> metas_semanal;

    MetaAdapter(List<Meta> metas){
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
        Meta meta = metas_semanal.get(i);
        if(meta.tipo_meta==0) {
            if (meta.abstinencia) {
                CardViewHolder.nomeTipoMeta.setText("Meta do tratamento para " + getNomeDroga(meta.tipo));
                CardViewHolder.meta_tratamento_metas.setText("Nessa semana, eu não consumirei mais");
                CardViewHolder.layout_tipo.setVisibility(View.GONE);
                CardViewHolder.meta_texto_desc.setText("Faltam 12 semanas para completar");
            } else {
                CardViewHolder.nomeTipoMeta.setText("Meta do tratamento para " + getNomeDroga(meta.tipo));
                CardViewHolder.quantidade.setText(meta.quantidade);
                CardViewHolder.frequencia.setText(meta.frequencia);
                CardViewHolder.horario.setText(meta.horario);
                CardViewHolder.meta_texto_desc.setText("Faltam 12 semanas para completar");
            }
        }else if(meta.tipo_meta==1){
            if (meta.abstinencia) {
                CardViewHolder.nomeTipoMeta.setText("Meta da semana para " + getNomeDroga(meta.tipo));
                CardViewHolder.meta_tratamento_metas.setText("Nessa semana, eu não consumirei mais");
                CardViewHolder.layout_tipo.setVisibility(View.GONE);
                CardViewHolder.meta_texto_desc.setText("Faltam 7 dias para completar");
            } else {
                CardViewHolder.nomeTipoMeta.setText("Meta da semana para " + getNomeDroga(meta.tipo));
                CardViewHolder.quantidade.setText(meta.quantidade);
                CardViewHolder.frequencia.setText(meta.frequencia);
                CardViewHolder.horario.setText(meta.horario);
                CardViewHolder.meta_texto_desc.setText("Faltam 7 dias para completar");
            }
        }else{
            CardViewHolder.nomeTipoMeta.setText("Meta pessoal da semana");
            CardViewHolder.layout_tipo.setVisibility(View.GONE);
            CardViewHolder.meta_tratamento_metas.setVisibility(View.GONE);
            CardViewHolder.box_metas.setVisibility(View.GONE);
            CardViewHolder.meta_texto_desc.setText(meta.mensagem);

        }
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

class Meta { // Objeto droga e construtor
    String quantidade, frequencia, horario,unidade,mensagem;
    int tipo, tipo_meta;

    boolean abstinencia;

    Meta(int tipo, float quantidade, int frequencia, boolean manha, boolean tarde, boolean noite, boolean madrugada, int tipo_meta,String mensagem) {
        this.tipo_meta=tipo_meta;
        if(tipo_meta==0 || tipo_meta==1) {
            this.tipo = tipo;
            if (frequencia == 0) {
                abstinencia = true;
            } else {
                abstinencia = false;
            }
            switch (tipo) {
                case 0:
                    this.quantidade = quantidade + " doses(cerveja)";
                    this.unidade = "doses";
                    break;
                case 1:
                    this.quantidade = quantidade + " doses(vinho)";
                    this.unidade = "doses";
                    break;
                case 2:
                    this.quantidade = quantidade + " doses(destilado)";
                    this.unidade = "doses";
                    break;
                case 3:
                    this.quantidade = quantidade + " baseados";
                    this.unidade = "baseados";
                    break;
                case 4:
                    this.quantidade = quantidade + " gramas";
                    this.unidade = "gramas";
                    break;
                case 5:
                    this.quantidade = quantidade + " pedras";
                    this.unidade = "pedras";
                    break;
            }

            switch (frequencia) {
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
            if (manha && tarde && noite && madrugada) {
                this.horario = "Qualquer horário";
            } else if (manha && tarde && noite) {
                this.horario = "O dia todo";
            } else {
                this.horario = "";
                if (manha) {
                    this.horario += "manha\n";
                }
                if (tarde) {
                    this.horario += "tarde\n";
                }
                if (noite) {
                    this.horario += "noite\n";
                }
                if (manha) {
                    this.horario += "madrugada\n";
                }
            }
        }else{
            this.mensagem=mensagem;
        }
    }
}
