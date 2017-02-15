package beok.beok;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

public class Atividade2 extends AppCompatActivity {

    FrameLayout layout;
    TextView txtpergunta, txtexplicacao;
    Button btverdade, btmito, btok;

    int pontos = 0;
    int contador = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_atividade2);

        Resources res = getResources();
        String[] perguntas = res.getStringArray(R.array.pergunta);

        btmito = (Button) findViewById(R.id.btmito);
        btverdade = (Button) findViewById(R.id.btverdade);
        txtpergunta = (TextView) findViewById(R.id.txtpergunta);
        layout = (FrameLayout) findViewById(R.id.layout);
        btok = (Button) findViewById(R.id.btok);
        txtexplicacao = (TextView) findViewById(R.id.txtexplicacao);

        txtpergunta.setText(perguntas[contador]);
    }


    public void botaoMito(View v){
        Resources res = getResources();
        String[] respostas = res.getStringArray(R.array.resposta);
        String[] explicacao = res.getStringArray(R.array.explicacao);
        txtexplicacao.setText(explicacao[contador]);
        //Essa parte é so pra mudar a cor do botao que estiver certo (verde = certo, vermelhor = errado)
        if (respostas[contador].equals("mito")){
            pontos++;
            btmito.setBackgroundColor(res.getColor(R.color.verde));
            btverdade.setBackgroundColor(res.getColor(R.color.vermelho));

        } else{
            btmito.setBackgroundColor(res.getColor(R.color.vermelho));
            btverdade.setBackgroundColor(res.getColor(R.color.verde));
        }
        // -------------- // ---------------- //
        layout.setVisibility(View.VISIBLE);
        btmito.setEnabled(false);
        btmito.setEnabled(false);

    }

    public void botaoVerdade(View v){
        Resources res = getResources();
        String[] respostas = res.getStringArray(R.array.resposta);
        String[] explicacao = res.getStringArray(R.array.explicacao);
        txtexplicacao.setText(explicacao[contador]);
        if (respostas[contador].equals("verdade")){
            pontos++;
            btmito.setBackgroundColor(res.getColor(R.color.vermelho));
            btverdade.setBackgroundColor(res.getColor(R.color.verde));
        } else{
            btmito.setBackgroundColor(res.getColor(R.color.verde));
            btverdade.setBackgroundColor(res.getColor(R.color.vermelho));
        }
        layout.setVisibility(View.VISIBLE);
        btmito.setEnabled(false);
        btmito.setEnabled(false);
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
            layout.setVisibility(View.INVISIBLE);
            btmito.setBackgroundColor(res.getColor(R.color.branco));
            btverdade.setBackgroundColor(res.getColor(R.color.branco));
            btmito.setEnabled(true);
            btmito.setEnabled(true);
        }
    }


}
