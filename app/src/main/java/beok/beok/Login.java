package beok.beok;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import beok.beok.POJO.Usuario;
import beok.beok.api.ServiceGenerator;
import beok.beok.localdb.DBLocal;
import beok.beok.webservice.ServiceWS;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login extends AppCompatActivity implements Callback<Usuario> {
    Call<Usuario> ucall;

    EditText email;
    EditText senha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        email=(EditText)findViewById(R.id.email_login);
        senha=(EditText)findViewById(R.id.senha_login);
    }
    public void loginClickLogin(View v){
        ServiceWS service= ServiceGenerator.createService(ServiceWS.class);
        ucall=service.getUsuario(email.getText().toString(),senha.getText().toString());
        ucall.enqueue(this);
    }
    public void cadastroClickLogin(View v){
        Intent i = new Intent(this, Cadastro.class);
        startActivity(i);
    }

    //Resposta do webservice
    @Override
    public void onResponse(Call<Usuario> call, Response<Usuario> response) {
        if(response.isSuccessful()){
            DBLocal db=new DBLocal(this);
            db.resetaTabela();
            db.insereUsuario(response.body());
            Intent i=new Intent(this,Home.class);
            startActivity(i);
        }else{
            Toast.makeText(this,"Erro no servidor.",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onFailure(Call<Usuario> call, Throwable t) {
        Toast.makeText(this,"Erro na rede, verifique sua conexão na internet e tente novamente",Toast.LENGTH_SHORT).show();
        t.printStackTrace();
    }
}
