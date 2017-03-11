package beok.beok;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

public class Atividade3 extends AppCompatActivity {

    private int tela;
    private String nao_usa_gosto, nao_usa_naogosto, usa_gosto, usa_naogosto;

    TextView txt_titulo1;
    EditText edtxtgosto, edtxtnao_gosto;
    ImageButton btanterior, btproximo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_atividade3);

        tela = 1;

        txt_titulo1 = (TextView) findViewById(R.id.txt_titulo1);
        edtxtgosto = (EditText) findViewById(R.id.edtxtgosto);
        edtxtnao_gosto = (EditText) findViewById(R.id.edtxtnao_gosto);
        btanterior = (ImageButton) findViewById(R.id.btanterior);
        btproximo = (ImageButton) findViewById(R.id.btproximo);

    }


    public void botaoAnterior(View v){
        if (tela == 2){
            txt_titulo1.setText("Quando você NÃO está usando a droga...");
            edtxtgosto.setText("");
            edtxtnao_gosto.setText("");
            btanterior.setVisibility(View.INVISIBLE);
            tela = 1;
        }
    }

    public void botaoProximo(View v){
        if (tela == 1){
            txt_titulo1.setText("Quando você está usando a droga...");
            edtxtnao_gosto.setText("");
            edtxtgosto.setText("");
            btanterior.setVisibility(View.VISIBLE);
            nao_usa_gosto = edtxtgosto.getText().toString();
            nao_usa_naogosto = edtxtnao_gosto.getText().toString();
            tela = 2;
        } else if (tela == 2){
            Intent i = new Intent(this, Fim.class);
            startActivity(i);
            tela = 3;
            usa_gosto = edtxtgosto.getText().toString();
            usa_naogosto = edtxtnao_gosto.getText().toString();
            // salvar as 4 Strings no banco de dados
        }
    }
    @Override
    public void onBackPressed() {

    }
}
