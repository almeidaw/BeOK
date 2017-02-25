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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.orm.SugarRecord;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import beok.beok.POJO.CreatedObjects;
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

public class Inspiracao extends Fragment implements Callback<List<InspServ>> {

    Call<List<InspServ>> call;
    ServiceWS service;

    private RecyclerView rv;
    private List<Inspi> inspis;

    Context tContext;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v;
        v = inflater.inflate(R.layout.fragment_inspiracao, container, false);
        rv = (RecyclerView)v.findViewById(R.id.recycler_view_inspiracao);
        LinearLayoutManager llm = new LinearLayoutManager(v.getContext());
        tContext=v.getContext();
        rv.setHasFixedSize(true);
        rv.setLayoutManager(llm);

        service = ServiceGenerator.createService(ServiceWS.class);

        callWs();

        initializeData();
        initializeAdapter();


        return v;
    }

    private void initializeData() {
        inspis = new ArrayList<Inspi>();

        for(Insp inspiracao:DB.listAll(Insp.class)){
            try {
                FileInputStream in = tContext.openFileInput(inspiracao.getId() + ".jpg");
                Bitmap bMap = BitmapFactory.decodeStream(in);
                inspis.add(new Inspi(bMap, inspiracao.getTexto()));
            }catch(FileNotFoundException e) {

            }
        }
    }

    private void initializeAdapter(){
        InspiAdapter adapter = new InspiAdapter(inspis);
        rv.setAdapter(adapter);
    }
    private void callWs(){
        List<Long> listaIdInsp=new ArrayList<Long>();
        List<Insp> listaInspiracao = DB.listAll(Insp.class);
        for(Insp insp:listaInspiracao) {
            listaIdInsp.add(insp.getId());
        }
        call = service.checkInsp(listaIdInsp);
        call.enqueue(this);
    }

    @Override
    public void onResponse(Call<List<InspServ>> call, Response<List<InspServ>> response) {
        if(response.body()!=null) {
            List<InspServ> insps = response.body();
            for (InspServ insp : insps) {
                FileOutputStream fos = null;
                try {
                    if (insp.getImagem() != null) {
                        fos = tContext.openFileOutput(insp.getId()+".jpg", Context.MODE_PRIVATE);
                        byte[] decodedString = android.util.Base64.decode(insp.getImagem(), android.util.Base64.DEFAULT);
                        fos.write(decodedString);
                        fos.flush();
                        fos.close();
                        Insp inspiracao=new Insp();
                        inspiracao.setId(insp.getId());
                        inspiracao.setTexto(insp.getTexto());
                        SugarRecord.save(inspiracao);
                        Toast.makeText(tContext,"ok!",Toast.LENGTH_LONG).show();
                        FileInputStream in = tContext.openFileInput(inspiracao.getId()+".jpg");
                        Bitmap bMap = BitmapFactory.decodeStream(in);
                        inspis.add(new Inspi(bMap, inspiracao.getTexto()));
                        initializeAdapter();
                    }

                } catch (Exception e) {
                    Toast.makeText(tContext,"merda r",Toast.LENGTH_LONG).show();
                } finally {
                    if (fos != null) {
                        fos = null;
                    }
                }
            }
        }else {

        }
    }

    @Override
    public void onFailure(Call<List<InspServ>> call, Throwable throwable) {
        throwable.printStackTrace();
        Toast.makeText(tContext,"merda f",Toast.LENGTH_LONG).show();
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
        //CardViewHolder.imagem.setImageResource(inspis.get(i).imagem);
        CardViewHolder.imagem.setImageBitmap(inspis.get(i).imagem);
        CardViewHolder.reflexao.setText(inspis.get(i).reflexao);
    }

    @Override
    public int getItemCount() {
        return inspis.size();
    }


}

class Inspi {
    String reflexao;
    Bitmap imagem;

    Inspi(Bitmap imagem, String reflexao) {
        this.imagem = imagem;
        this.reflexao = reflexao;
    }
}