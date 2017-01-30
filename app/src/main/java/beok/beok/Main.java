package beok.beok;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import beok.beok.localdb.DBLocal;

public class Main extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DBLocal db= new DBLocal(this);
        if(db.usuarioLogado()){
            //redireciona para a home
            Intent i=new Intent(this,Home.class);
            startActivity(i);
        }else{
            //vai para a página de login/cadastro
            Intent i=new Intent(this,Cadastro.class);
            startActivity(i);
        }
    }
}