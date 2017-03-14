package beok.beok;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.orm.SugarRecord;

import beok.beok.POJO.MetaGeral;
import beok.beok.POJO.MetaSemanal;
import beok.beok.POJO.Usuario;
import beok.beok.api.App;
import beok.beok.api.DB;
import beok.beok.api.ServiceGenerator;
import beok.beok.webservice.ServiceWS;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Cadastro extends AppCompatActivity implements View.OnClickListener, Callback<Usuario> {

    Call<Usuario> ucall;

    Usuario usuario;

    Button btproximo1;
    EditText edtxtnome, edtxtemail, edtxtsenha;
    Spinner spidade, spgenero;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        usuario = new Usuario();

        spidade = (Spinner) findViewById(R.id.spidade);
        spgenero = (Spinner) findViewById(R.id.spgenero);

        edtxtnome = (EditText) findViewById(R.id.edtextnome);
        edtxtemail = (EditText) findViewById(R.id.edtxtemail);
        edtxtsenha = (EditText) findViewById(R.id.edtxtsenha);

        btproximo1 = (Button) findViewById(R.id.btproximo1);


        spidade.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                usuario.setIdade(Integer.parseInt(spidade.getSelectedItem().toString()));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spgenero.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                usuario.setGenero(spgenero.getSelectedItem().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btproximo1.setOnClickListener(this);
    }

    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btproximo1:
                usuario.setNome(edtxtnome.getText().toString());
                usuario.setEmail(edtxtemail.getText().toString());
                usuario.setSenha(edtxtsenha.getText().toString());

                if(usuario.getSenha().length()<8){
                    Toast.makeText(this, "Senha muito curta (mínimo 8 caracteres)", Toast.LENGTH_LONG).show();
                }else if(usuario.getNome().equals("") || usuario.getEmail().equals("")){
                    Toast.makeText(this, "Insira todos os campos corretamente", Toast.LENGTH_LONG).show();
                }else{
                    //Banco de dados remoto
                    if(App.DEBUG){
                        SugarRecord.save(usuario);
                        Intent nextActivity = new Intent(this, Tela2.class);
                        startActivity(nextActivity);
                    }else {
                        ServiceWS service = ServiceGenerator.createService(ServiceWS.class);
                        ucall = service.cadastrar(usuario);
                        ucall.enqueue(this);
                    }
                }
        }
    }


    //Resposta do webservice
    @Override
    public void onResponse(Call<Usuario> call, Response<Usuario> response) {
        if(response.isSuccessful()){

            //Banco de dados local
            Usuario u=response.body();
            SugarRecord.save(u);

            //Tela da home
            Intent nextActivity = new Intent(this, Tela2.class);
            startActivity(nextActivity);
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        }else{
            Toast.makeText(this, "Usuário com este e-mail já existente.", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onFailure(Call<Usuario> call, Throwable t) {
        Toast.makeText(this,"Erro na rede, tente novamente",Toast.LENGTH_SHORT).show();
        t.printStackTrace();
    }
    @Override
    public void onBackPressed() {

    }
}
