package beok.beok;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class Diary5 extends AppCompatActivity {

    Button btsim, btnao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary_5);

        btnao = (Button) findViewById(R.id.btnao);
        btsim = (Button) findViewById(R.id.btsim);

    }

    public void botaoSim(View v){
        Intent i = new Intent(this, Diary2.class);
        startActivity(i);
    }
    public void botaoNao(View v) {
        Intent i = new Intent(this, Diary6.class);
        startActivity(i);
    }
}
