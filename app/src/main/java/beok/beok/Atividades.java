package beok.beok;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.VideoView;

import java.util.Random;

public class Atividades extends AppCompatActivity {

    private int atividade;

    TextView txtnum_atividade, txttema_atividade, txtatividade_descricao;
    VideoView videoView;
    Button btatividade;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_atividades);

        Bundle bundle = new Bundle();
        bundle = getIntent().getExtras();
        atividade = bundle.getInt("atividade");

        txtnum_atividade = (TextView) findViewById(R.id.txtnum_atividade);
        txttema_atividade = (TextView) findViewById(R.id.txttema_atividade);
        txtatividade_descricao = (TextView) findViewById(R.id.txtatividade_descricao);
        videoView = (VideoView) findViewById(R.id.videoView);
        btatividade = (Button) findViewById(R.id.btatividade);


        switch (atividade){
            case 1:
                txtnum_atividade.setText("1");
                txttema_atividade.setText("METAS DA SEMANA");
                //videoView.setVisibility(View.VISIBLE);
                txtatividade_descricao.setVisibility(View.VISIBLE);
                txtatividade_descricao.setText(getResources().getString(R.string.Descricao1));
                btatividade.setText("Definir meta da semana");
                break;

            case 2:
                txtnum_atividade.setText("2");
                txttema_atividade.setText("MITOS E VERDADES");
                txtatividade_descricao.setText(getResources().getString(R.string.Descricao2));
                txtatividade_descricao.setVisibility(View.VISIBLE);
                btatividade.setText("Ir para atividade");
                break;

            case 3:
                txtnum_atividade.setText("3");
                txttema_atividade.setText("MOTIVAÇÃO");
                //videoView.setVisibility(View.VISIBLE);
                txtatividade_descricao.setVisibility(View.VISIBLE);
                txtatividade_descricao.setText(getResources().getString(R.string.Descricao3));
                btatividade.setText("Ir para atividade");
                break;

        }

    }

    public void botaoAtividade(View v){
        Intent i = null;
        switch (atividade){
            case 1:
                i = new Intent(this, MetaSemanal.class);
                break;

            case 2:
                i = new Intent(this, Atividade2.class);
                break;

            case 3:
                i = new Intent(this, Atividade3.class);
                break;

        }
        startActivity(i);
    }
}
