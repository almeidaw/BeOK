package beok.beok;

import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Random;

public class Fim extends AppCompatActivity {

    TextView txtmsg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fim);

        txtmsg = (TextView) findViewById(R.id.txtmsg);

        Random r = new Random();
        Resources res = getResources();

        String[] msg = res.getStringArray(R.array.mensagens);
        String mensagem = String.format(msg[r.nextInt(2)], "Erick"); //Inserir o nome do usuario segundo parametro

        txtmsg.setText(mensagem);
        
    }
}
