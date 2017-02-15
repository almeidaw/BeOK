package beok.beok;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class BotaoPanico1 extends AppCompatActivity {

    Button btfissura, btusei;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_botao_panico1);

        btfissura = (Button) findViewById(R.id.btfissura);
        btusei = (Button) findViewById(R.id.btusei);

    }

    public void botaoFissura(View v){
        Intent i = new Intent(this, BotaoPanico2.class);
        startActivity(i);
    }
    public void botaoUsei(View v){
        Intent i = new Intent(this, BotaoPanico2.class);
        startActivity(i);
    }

}
