package beok.beok;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Atividade1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_atividade1);
    }

    public void iniciarAtividade1(View v){
        Intent i = new Intent(this, MetaSemanal.class);
        startActivity(i);
    }
}
