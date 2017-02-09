package beok.beok;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class Diary2 extends AppCompatActivity {

    Button btalcool, btmaconha, btcocaina, btcrack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary_2);

        btalcool = (Button) findViewById(R.id.btalcool);
        btmaconha = (Button) findViewById(R.id.btmaconha);
        btcocaina = (Button) findViewById(R.id.btcocaina);
        btcrack = (Button) findViewById(R.id.btcrack);

    }
    public void botaoAlcool(View v){
        Intent i = new Intent(this, Diary3Alcohol.class);
        startActivity(i);
    }
    public void botaoMaconha(View v){
        Intent i = new Intent(this, Diary3Marijuana.class);
        startActivity(i);
    }
    public void botaoCocaina(View v){
        Intent i = new Intent(this, Diary3Cocaine.class);
        startActivity(i);
    }
    public void botaoCrack(View v){
        Intent i = new Intent(this, Diary3Crack.class);
        startActivity(i);
    }
}
