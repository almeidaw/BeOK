package beok.beok;

import android.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by william on 16/02/17.
 */

public class Inspiracao extends Fragment {

    private RecyclerView rv;
    private List<Inspi> inspis;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v;
        v = inflater.inflate(R.layout.fragment_inspiracao, container, false);
        rv = (RecyclerView)v.findViewById(R.id.recycler_view_inspiracao);
        LinearLayoutManager llm = new LinearLayoutManager(v.getContext());
        rv.setHasFixedSize(true);
        rv.setLayoutManager(llm);

        initializeData();
        initializeAdapter();

        return v;
    }

    private void initializeData() {
        inspis = new ArrayList<>();
       // inspis.add(new Inspi(R.drawable.inspiracao1, "\"Viver é como andar de bicicleta: É preciso estar em constante movimento para manter o equilíbrio\"", "- Albert Einstein"));
        //inspis.add(new Inspi(R.drawable.inspiracao2, "\"E chegou o dia em que o risco de continuar espremido dentro do botão era mais doloroso que o de desabrochar\"", "- Anaïs Nin"));
        //inspis.add(new Inspi(R.drawable.inspiracao3, "\"Todo mundo tem dentro de si um fragmento de boas notícias. A boa notícia é que você não sabe quão extraordinário você pode ser! O quanto você pode amar! O que você pode executar! E qual é o seu potencial!\"", "- Anne Frank"));
    }

    private void initializeAdapter(){
        InspiAdapter adapter = new InspiAdapter(inspis);
        rv.setAdapter(adapter);
    }
}

class InspiAdapter extends RecyclerView.Adapter<InspiAdapter.CardViewHolder>{

    public static class CardViewHolder extends RecyclerView.ViewHolder {

        CardView cv;
        TextView autor, reflexao;
        ImageView imagem;

        CardViewHolder(View itemView) {
            super(itemView);
            cv = (CardView)itemView.findViewById(R.id.card_layout_inspiracao);
            autor = (TextView)itemView.findViewById(R.id.autor_inspi);
            reflexao = (TextView)itemView.findViewById(R.id.reflexao_inspi);
            imagem = (ImageView) itemView.findViewById(R.id.image_inspi);
            imagem.setScaleType(ImageView.ScaleType.CENTER_CROP);
        }
    }

    List<Inspi> inspis;

    InspiAdapter(List<Inspi> inspis){
        this.inspis = inspis;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public CardViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_layout_inspiracao, viewGroup, false);
        CardViewHolder cvh = new CardViewHolder(v);
        return cvh;
    }

    @Override
    public void onBindViewHolder(CardViewHolder CardViewHolder, int i) {
        CardViewHolder.imagem.setImageResource(inspis.get(i).imagem);
        CardViewHolder.reflexao.setText(inspis.get(i).reflexao);
        CardViewHolder.autor.setText(inspis.get(i).autor);
    }

    @Override
    public int getItemCount() {
        return inspis.size();
    }
}

class Inspi {
    String autor, reflexao;
    int imagem;

    Inspi(int imagem, String reflexao, String autor) {
        this.imagem = imagem;
        this.autor = autor;
        this.reflexao = reflexao;
    }
}