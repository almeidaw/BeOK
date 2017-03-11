package beok.beok;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class GruposZona extends AppCompatActivity {
    Bundle b;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grupos);
        b=getIntent().getExtras();
    }
    public void zs(View v){
        b.putString("zona","0");
        Intent i = new Intent(this,Grupos.class);
        i.putExtras(b);
        startActivity(i);
    }
    public void zn(View v){
        b.putString("zona","1");
        Intent i = new Intent(this,Grupos.class);
        i.putExtras(b);
        startActivity(i);
    }
    public void zl(View v){
        b.putString("zona","2");
        Intent i = new Intent(this,Grupos.class);
        i.putExtras(b);
        startActivity(i);

    }
    public void zo(View v){
        b.putString("zona","3");
        Intent i = new Intent(this,Grupos.class);
        i.putExtras(b);
        startActivity(i);
    }
    public void zc(View v){
        b.putString("zona","4");
        Intent i = new Intent(this,Grupos.class);
        i.putExtras(b);
        startActivity(i);
    }

}
