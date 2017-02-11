package beok.beok;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import beok.beok.POJO.Usuario;

public class Cadastro extends AppCompatActivity implements View.OnClickListener {

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

        usuario.setNome(edtxtnome.getText().toString());
        usuario.setEmail(edtxtemail.getText().toString());
        usuario.setSenha(edtxtsenha.getText().toString());

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
                Intent nextActivity = new Intent(this, Tela2.class);
                startActivity(nextActivity);
                //slide from right to left
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                break;
        }
    }
}
