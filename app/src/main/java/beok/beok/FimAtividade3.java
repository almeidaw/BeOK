package beok.beok;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class FimAtividade3 extends AppCompatActivity {

    Button btfim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fim_atividade3);

        btfim = (Button) findViewById(R.id.btfim);

    }

    public void BotaoFim (View v){
        Intent intent = new Intent(this, Main.class);
        startActivity(intent);
    }
}
