package beok.beok;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Terapia extends AppCompatActivity {

    LinearLayout layout1, layout2;
    TextView txtpergunta, txtexplicacao;
    Button btverdade, btmito, resposta2;
    ImageButton btok;
    ImageView imagem;

    int pontos = 0;
    int contador = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_atividade2);


        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        
        // Give the TabLayout the ViewPager
        TabLayout tabLayout = (TabLayout) findViewById(R.id.sliding_tabs);
        tabLayout.setupWithViewPager(viewPager);

       /* Resources res = getResources();
        String[] perguntas = res.getStringArray(R.array.pergunta);

        btmito = (Button) findViewById(R.id.btmito);
        btverdade = (Button) findViewById(R.id.btverdade);
        txtpergunta = (TextView) findViewById(R.id.txtpergunta);
        layout1 = (LinearLayout) findViewById(R.id.layout1);
        layout2 = (LinearLayout) findViewById(R.id.layout2);
        btok = (ImageButton) findViewById(R.id.btok);
        txtexplicacao = (TextView) findViewById(R.id.txtexplicacao);
        txtpergunta.setText(perguntas[contador]);
        imagem = (ImageView)findViewById(R.id.terapia1);
        resposta2 = (Button) findViewById(R.id.resposta1);
    }

    public void botaoMito(View v){
        Resources res = getResources();
        String[] respostas = res.getStringArray(R.array.resposta);
        String[] explicacao = res.getStringArray(R.array.explicacao);
        txtexplicacao.setText(explicacao[contador]);
        //Essa parte é so pra mudar a cor do botao que estiver certo (verde = certo, vermelhor = errado)
        if (respostas[contador].equals("mito")){
           // resposta.setBackgroundColor(res.getColor(R.color.verde));
           // resposta.setText("MITO");
            pontos++;
        }
        else {
           // resposta.setBackgroundColor(res.getColor(R.color.vermelho));
           // resposta.setText("MITO");
        }
         -------------- // ---------------- //
        layout2.setVisibility(View.VISIBLE);
        layout1.setVisibility(View.GONE);
        btok.setVisibility(View.VISIBLE);
    }

    public void botaoVerdade(View v){
        Resources res = getResources();
        String[] respostas = res.getStringArray(R.array.resposta);
        String[] explicacao = res.getStringArray(R.array.explicacao);
        txtexplicacao.setText(explicacao[contador]);
        if (respostas[contador].equals("verdade")){
           // resposta.setBackgroundColor(res.getColor(R.color.verde));
           // resposta.setText("VERDADE");
            pontos++;
        }
        else {
            //resposta.setBackgroundColor(res.getColor(R.color.vermelho));
            //resposta.setText("VERDADE");
        }
        layout1.setVisibility(View.VISIBLE);
        layout2.setVisibility(View.GONE);
        btok.setVisibility(View.VISIBLE);

    }

    public void botaoOk(View v){
        Resources res = getResources();
        String[] perguntas = res.getStringArray(R.array.pergunta);
        if (contador == perguntas.length - 1){
            Intent i = new Intent(this, Fim.class);
            i.putExtra("pontos", pontos);
            startActivity(i);
        } else{
            contador++;
            txtpergunta.setText(perguntas[contador]);
            layout1.setVisibility(View.GONE);
            layout2.setVisibility(View.VISIBLE);
        }*/
    }
}
