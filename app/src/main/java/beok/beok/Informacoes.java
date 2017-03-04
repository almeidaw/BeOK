package beok.beok;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Informacoes extends AppCompatActivity {
    Bundle b;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informacoes);
        b=new Bundle();
    }

    public void gruposAAClick(View v){
        b.putString("tipo","0");
        Intent i = new Intent(this,GruposZona.class);
        i.putExtras(b);
        startActivity(i);
    }
    public void gruposNAClick(View v){
        b.putString("tipo","1");
        Intent i = new Intent(this,GruposZona.class);
        i.putExtras(b);
        startActivity(i);
    }
}
