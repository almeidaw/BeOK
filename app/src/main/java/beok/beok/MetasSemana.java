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


        int nDrogas=DB.listAll(ConsumoAtual.class).size();


        List<MetaGeral> metasGerais = DB.listAll(MetaGeral.class);
        List<MetaSemanal> metas = DB.listAll(MetaSemanal.class, "id Desc");
       for(int i=0;i<nDrogas;i++) {
           MetaGeral m1 = metasGerais.get(i);
           for(int j=0;j<nDrogas;j++){
               MetaSemanal m2=metas.get(i);
               if(convertTipos(m2.getTipo())==convertTipos(m1.getTipo())){
                   metas_semanal.add(new Meta(m1.getTipo(), m2.getQuantidade(),m1.getQuantidade(), m2.getFreqSemanal(),m1.getFreqSemanal(), m2.getManha(), m2.getTarde(), m2.getNoite(), m2.getMadrugada(),m1.getManha(), m1.getTarde(), m1.getNoite(), m1.getMadrugada(), 0, null));
               }
           }
       }

        List<MetaSemGeral> metasg = DB.listAll(MetaSemGeral.class,"id Desc");
        MetaSemGeral meta3=metasg.get(0);

        metas_semanal.add(new Meta(0,0,0,0,0,false,false,false,false,false,false,false,false,1,meta3.getTexto()));

    }

    private void initializeAdapter(){
        MetaAdapter adapter = new MetaAdapter(metas_semanal);
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

class MetaAdapter extends RecyclerView.Adapter<MetaAdapter.CardViewHolder>{

    public static class CardViewHolder extends RecyclerView.ViewHolder {

        CardView cv;
        TextView nomeDroga,quantidade_sem,red_abst_sem,freq_sem,horario_sem,meta_left_sem;
        TextView quantidade_geral,red_abst_geral,freq_geral,horario_geral,meta_left_geral;
        LinearLayout containerMetas,layout_metas_geral,layout_metas_sem;


        CardViewHolder(View itemView) {
            super(itemView);
            cv = (CardView)itemView.findViewById(R.id.card_view);
            nomeDroga=(TextView)itemView.findViewById(R.id.nome_droga_metas);
            quantidade_sem=(TextView)itemView.findViewById(R.id.quantidade_metas_sem);
            red_abst_sem=(TextView)itemView.findViewById(R.id.reduzir_ou_abst_sem);
            freq_sem=(TextView)itemView.findViewById(R.id.frequencia_metas_sem);
            horario_sem=(TextView)itemView.findViewById(R.id.horario_metas_sem);
            meta_left_sem=(TextView)itemView.findViewById(R.id.txt_metas_left_sem);
            quantidade_geral=(TextView)itemView.findViewById(R.id.quantidade_metas_geral);
            red_abst_geral=(TextView)itemView.findViewById(R.id.reduzir_ou_abst_geral);
            freq_geral=(TextView)itemView.findViewById(R.id.frequencia_metas_geral);
            horario_geral=(TextView)itemView.findViewById(R.id.horario_metas_geral);
            meta_left_geral=(TextView)itemView.findViewById(R.id.txt_metas_left_geral);
            containerMetas=(LinearLayout)itemView.findViewById(R.id.container_metas);
            layout_metas_geral=(LinearLayout)itemView.findViewById(R.id.layout_metas_geral);
            layout_metas_sem=(LinearLayout)itemView.findViewById(R.id.layout_metas_sem);
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
    public void onBindViewHolder(CardViewHolder cvh, int i) {
        Meta meta = metas_semanal.get(i);
        if(meta.tipo_meta==0) {
            cvh.nomeDroga.setText(getNomeDroga(meta.tipo));
            if (meta.abstinencia_sem) {
                cvh.red_abst_sem.setText("Nessa semana, eu não consumirei mais");
                cvh.layout_metas_sem.setVisibility(View.GONE);
                cvh.meta_left_sem.setText("Faltam 7 dias para completar");
            } else {
                cvh.red_abst_sem.setText("Nessa semana, eu consumirei");
                cvh.quantidade_sem.setText(meta.quantidade_sem);
                cvh.freq_sem.setText(meta.frequencia_sem);
                cvh.horario_sem.setText(meta.horario_sem);
                cvh.meta_left_sem.setText("Faltam 7 dias para completar");
            }
            if (meta.abstinencia_geral) {
                cvh.red_abst_geral.setText("Eu não consumirei mais");
                cvh.layout_metas_geral.setVisibility(View.GONE);
                cvh.meta_left_geral.setText("Faltam 12 semanas para completar");
            } else {
                cvh.red_abst_geral.setText("Eu consumirei");
                cvh.quantidade_geral.setText(meta.quantidade_geral);
                cvh.freq_geral.setText(meta.frequencia_geral);
                cvh.horario_geral.setText(meta.horario_geral);
                cvh.meta_left_geral.setText("Faltam 12 semanas para completar");
            }
        }else{
            cvh.nomeDroga.setText("Meta pessoal da semana");
            cvh.containerMetas.setVisibility(View.GONE);
            cvh.meta_left_sem.setText(meta.mensagem);
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
    String quantidade_sem, frequencia_sem, horario_sem,unidade,mensagem;
    String quantidade_geral, frequencia_geral, horario_geral;
    int tipo, tipo_meta;

    boolean abstinencia_sem;
    boolean abstinencia_geral;

    Meta(int tipo, float quantidade_sem,float quantidade_geral, int frequencia_sem,int frequencia_geral, boolean manha_sem, boolean tarde_sem, boolean noite_sem, boolean madrugada_sem,boolean manha_geral, boolean tarde_geral, boolean noite_geral, boolean madrugada_geral, int tipo_meta,String mensagem) {
        this.tipo_meta=tipo_meta;
        if(tipo_meta==0) {
            this.tipo = tipo;
            if (frequencia_sem == 0) {
                abstinencia_sem = true;
            } else {
                abstinencia_sem = false;

                switch (tipo) {
                    case 0:
                        this.quantidade_sem = quantidade_sem + " doses(cerveja)";
                        this.unidade = "doses";
                        break;
                    case 1:
                        this.quantidade_sem = quantidade_sem + " doses(vinho)";
                        this.unidade = "doses";
                        break;
                    case 2:
                        this.quantidade_sem = quantidade_sem + " doses(destilado)";
                        this.unidade = "doses";
                        break;
                    case 3:
                        this.quantidade_sem = quantidade_sem + " baseados";
                        this.unidade = "baseados";
                        break;
                    case 4:
                        this.quantidade_sem = quantidade_sem + " gramas";
                        this.unidade = "gramas";
                        break;
                    case 5:
                        this.quantidade_sem = quantidade_sem + " pedras";
                        this.unidade = "pedras";
                        break;
                }

                switch (frequencia_sem) {
                    case 1:
                        this.frequencia_sem = "1 dia\n por semana";
                        break;
                    case 2:
                        this.frequencia_sem = "2 dias\n por semana";
                        break;
                    case 3:
                        this.frequencia_sem = "3 a 5 dias\n por semana";
                        break;
                    case 4:
                        this.frequencia_sem = "Todos os dias";
                        break;
                    case 5:
                        this.frequencia_sem = "Só fins\n de semana";
                        break;
                }
                if (manha_sem && tarde_sem && noite_sem && madrugada_sem) {
                    this.horario_sem = "Qualquer horário";
                } else if (manha_sem && tarde_sem && noite_sem) {
                    this.horario_sem = "O dia todo";
                } else {
                    this.horario_sem = "";
                    if (manha_sem) {
                        this.horario_sem += "manha\n";
                    }
                    if (tarde_sem) {
                        this.horario_sem += "tarde\n";
                    }
                    if (noite_sem) {
                        this.horario_sem += "noite\n";
                    }
                    if (manha_sem) {
                        this.horario_sem += "madrugada\n";
                    }
                }
            }
            if (frequencia_geral == 0) {
                abstinencia_geral = true;
            } else {
                abstinencia_geral = false;

                switch (tipo) {
                    case 0:
                        this.quantidade_geral = quantidade_geral + " doses(cerveja)";
                        this.unidade = "doses";
                        break;
                    case 1:
                        this.quantidade_geral = quantidade_geral + " doses(vinho)";
                        this.unidade = "doses";
                        break;
                    case 2:
                        this.quantidade_geral = quantidade_geral + " doses(destilado)";
                        this.unidade = "doses";
                        break;
                    case 3:
                        this.quantidade_geral = quantidade_geral + " baseados";
                        this.unidade = "baseados";
                        break;
                    case 4:
                        this.quantidade_geral = quantidade_geral + " gramas";
                        this.unidade = "gramas";
                        break;
                    case 5:
                        this.quantidade_geral = quantidade_geral + " pedras";
                        this.unidade = "pedras";
                        break;
                }

                switch (frequencia_geral) {
                    case 1:
                        this.frequencia_geral = "1 dia\n por semana";
                        break;
                    case 2:
                        this.frequencia_geral = "2 dias\n por semana";
                        break;
                    case 3:
                        this.frequencia_geral = "3 a 5 dias\n por semana";
                        break;
                    case 4:
                        this.frequencia_geral = "Todos os dias";
                        break;
                    case 5:
                        this.frequencia_geral = "Só fins\n de semana";
                        break;
                }
                if (manha_geral && tarde_geral && noite_geral && madrugada_geral) {
                    this.horario_geral = "Qualquer horário";
                } else if (manha_geral && tarde_geral && noite_geral) {
                    this.horario_geral = "O dia todo";
                } else {
                    this.horario_geral = "";
                    if (manha_geral) {
                        this.horario_geral += "manha\n";
                    }
                    if (tarde_geral) {
                        this.horario_geral += "tarde\n";
                    }
                    if (noite_geral) {
                        this.horario_geral += "noite\n";
                    }
                    if (manha_geral) {
                        this.horario_geral += "madrugada\n";
                    }
                }
            }
        }else{
            this.mensagem=mensagem;
        }
    }
}
