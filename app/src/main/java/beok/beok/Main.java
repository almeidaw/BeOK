package beok.beok;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.orm.SugarContext;
import com.orm.SugarDb;
import com.orm.SugarRecord;

import java.util.List;

import beok.beok.POJO.Usuario;

public class Main extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SugarContext.init(this);

        List<Usuario> us= SugarRecord.listAll(Usuario.class);



        if(!us.isEmpty()){
            //redireciona para a home
            Intent i=new Intent(this,Home.class);
            startActivity(i);
        }else{
            //vai para a página de login/cadastro
            Intent i=new Intent(this,FirstAccess.class);
            startActivity(i);
        }
    }
}