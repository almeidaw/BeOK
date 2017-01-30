package beok.beok;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import beok.beok.POJO.Usuario;
import beok.beok.api.ServiceGenerator;
import beok.beok.localdb.DBLocal;
import beok.beok.webservice.ServiceWS;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Spinner;
import android.widget.Toast;

public class Cadastro extends AppCompatActivity implements Callback<Usuario> {

    private int cont1 = 1; // Intent contato 1
    private int cont2 = 2; // Intent contato 2
    private int cont3 = 3; // Intent contato 3

    Call<Usuario> ucall;
    DBLocal db;

    Usuario usuario;

    CheckBox cbalcool, cbmaconha, cbcocaina, cbcrack, cbecstasy, cbalucinogeno, cboutradroga,
            cbparaalcool, cbparamaconha, cbparacrack, cbparacocaina, cbparaecstasy, cbparaalucinogeno, cbparaoutras,
            cbfamilia, cbdinheiro, cbtrabalho, cbestudos, cbsfisica, cbsemocional, cboutro;

    TextView txtalcoolidade, txtmaconhaidade, txtcocainaidade, txtcrackidade, txtecstasyidade, txtalucinogenoidade, txtoutrasidade,
            txtalcoolfreq, txtmaconhafreq, txtcocainafreq, txtcrackfreq, txtecstasyfreq, txtalucinogenofreq, txtoutrasfreq,
            txtalcoolqtd, txtmaconhaqtd, txtcocainaqtd, txtcrackqtd, txtecstasyqtd, txtalucinogenoqtd, txtoutrasqtd,
            txtalcoolultima, txtmaconhaultima, txtcocainaultima, txtcrackultima, txtecstasyultima, txtalucinogenoultima, txtoutrasultima,
            txtcontato1, txtcontato2, txtcontato3;

    Spinner spidade, spgenero,
            spalcoolidade, spmaconhaidade, spcocainaidade, spcrackidade, specstasyidade, spalucinogenoidade, spoutrasdrogaidade,
            spalcoolfreq, spmaconhafreq, spcocainafreq, spcrackfreq, specstasyfreq, spalucinogenofreq, spoutrasfreq,
            spmetafinal, spreduzir;

    TextView edtxtnome, edtxtemail, edtxtsenha,
            edtxtoutrasdrogas,
            edtxtcontato1, edtxtcontato2, edtxtcontato3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        cbalcool = (CheckBox) findViewById(R.id.cbalcool);
        cbmaconha = (CheckBox) findViewById(R.id.cbmaconha);
        cbcocaina = (CheckBox) findViewById(R.id.cbcocaina);
        cbcrack = (CheckBox) findViewById(R.id.cbcrack);
        cbecstasy = (CheckBox) findViewById(R.id.cbecstasy);
        cbalucinogeno = (CheckBox) findViewById(R.id.cbalucinogeno);
        cboutradroga = (CheckBox) findViewById(R.id.cboutradroga);

        cbparaalcool = (CheckBox) findViewById(R.id.cbparaalcool);
        cbparamaconha = (CheckBox) findViewById(R.id.cbparamaconha);
        cbparacocaina = (CheckBox) findViewById(R.id.cbparacocaina);
        cbparacrack = (CheckBox) findViewById(R.id.cbparacrack);
        cbparaecstasy = (CheckBox) findViewById(R.id.cbparaecstasy);
        cbparaalucinogeno = (CheckBox) findViewById(R.id.cbparaalucinogeno);
        cbparaoutras = (CheckBox) findViewById(R.id.cbparaoutras);

        cbfamilia = (CheckBox) findViewById(R.id.cbfamilia);
        cbdinheiro = (CheckBox) findViewById(R.id.cbdinheiro);
        cbsemocional = (CheckBox) findViewById(R.id.cbsemocional);
        cbsfisica = (CheckBox) findViewById(R.id.cbsfisica);
        cbtrabalho = (CheckBox) findViewById(R.id.cbtrabalho);
        cbestudos = (CheckBox) findViewById(R.id.cbestudos);
        cboutro = (CheckBox) findViewById(R.id.cboutros);

        txtalcoolidade = (TextView) findViewById(R.id.txtalcoolidade);
        txtmaconhaidade = (TextView) findViewById(R.id.txtmaconhaidade);
        txtcocainaidade = (TextView) findViewById(R.id.txtcocainaidade);
        txtcrackidade = (TextView) findViewById(R.id.txtcrackidade);
        txtecstasyidade = (TextView) findViewById(R.id.txtecstasyidade);
        txtalucinogenoidade = (TextView) findViewById(R.id.txtalucinogenoidade);
        txtoutrasidade = (TextView) findViewById(R.id.txtoutrasidade);

        txtalcoolfreq = (TextView) findViewById(R.id.txtalcoolfreq);
        txtmaconhafreq = (TextView) findViewById(R.id.txtmaconhafreq);
        txtcocainafreq = (TextView) findViewById(R.id.txtcocainafreq);
        txtcrackfreq = (TextView) findViewById(R.id.txtcrackfreq);
        txtecstasyfreq = (TextView) findViewById(R.id.txtecstasyfreq);
        txtalucinogenofreq = (TextView) findViewById(R.id.txtalucinogenofreq);
        txtoutrasfreq = (TextView) findViewById(R.id.txtoutrasfreq);

        txtalcoolqtd = (TextView) findViewById(R.id.txtalcoolqtd);
        txtmaconhaqtd = (TextView) findViewById(R.id.txtmaconhaqtd);
        txtcocainaqtd = (TextView) findViewById(R.id.txtcocainaqtd);
        txtcrackqtd = (TextView) findViewById(R.id.txtcrackqtd);
        txtecstasyqtd = (TextView) findViewById(R.id.txtecstasyqtd);
        txtalucinogenoqtd = (TextView) findViewById(R.id.txtalucinogenoqtd);
        txtoutrasqtd = (TextView) findViewById(R.id.txtoutrasqtd);

        txtalcoolultima = (TextView) findViewById(R.id.txtalcoolultima);
        txtmaconhaultima = (TextView) findViewById(R.id.txtmaconhaultima);
        txtcocainaultima = (TextView) findViewById(R.id.txtcocainaultima);
        txtcrackultima = (TextView) findViewById(R.id.txtcrackultima);
        txtecstasyultima = (TextView) findViewById(R.id.txtecstasyultima);
        txtalucinogenoultima = (TextView) findViewById(R.id.txtalucinogenoultima);
        txtoutrasultima = (TextView) findViewById(R.id.txtoutrasultima);

        txtcontato1 = (TextView) findViewById(R.id.txtcontato1);
        txtcontato2 = (TextView) findViewById(R.id.txtcontato2);
        txtcontato3 = (TextView) findViewById(R.id.txtcontato3);

        spidade = (Spinner) findViewById(R.id.spidade);
        spgenero = (Spinner) findViewById(R.id.spgenero);

        spalcoolidade = (Spinner) findViewById(R.id.spalcoolidade);
        spmaconhaidade = (Spinner) findViewById(R.id.spmaconhaidade);
        spcocainaidade = (Spinner) findViewById(R.id.spcocainaidade);
        spcrackidade = (Spinner) findViewById(R.id.spcrackidade);
        specstasyidade = (Spinner) findViewById(R.id.specstasyidade);
        spalucinogenoidade = (Spinner) findViewById(R.id.spalucinogenoidade);
        spoutrasdrogaidade = (Spinner) findViewById(R.id.spoutrasidade);

        spalcoolfreq = (Spinner) findViewById(R.id.spalcoolfreq);
        spmaconhafreq = (Spinner) findViewById(R.id.spmaconhafreq);
        spcocainafreq = (Spinner) findViewById(R.id.spcocainafreq);
        spcrackfreq = (Spinner) findViewById(R.id.spcrackfreq);
        specstasyfreq = (Spinner) findViewById(R.id.specstasyfreq);
        spalucinogenofreq = (Spinner) findViewById(R.id.spalucinogenofreq);
        spoutrasfreq = (Spinner) findViewById(R.id.spoutrasfreq);

        spmetafinal = (Spinner) findViewById(R.id.spmetafinal);
        spreduzir = (Spinner) findViewById(R.id.spreduzir);

        edtxtnome = (TextView) findViewById(R.id.edtextnome);
        edtxtemail = (TextView) findViewById(R.id.edtxtemail);
        edtxtsenha = (TextView) findViewById(R.id.edtxtsenha);

        edtxtoutrasdrogas = (TextView) findViewById(R.id.edtxtoutrasdrogas);
        edtxtcontato1 = (TextView) findViewById(R.id.edtxtcontato1);
        edtxtcontato2 = (TextView) findViewById(R.id.edtxtcontato2);
        edtxtcontato3 = (TextView) findViewById(R.id.edtxtcontato3);

        usuario = new Usuario();

        // Colocando as informacoes do usuario no banco de dados interno
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


    }

    public void usouAlcool(View v) {
        if (cbalcool.isChecked()) {
            txtalcoolidade.setVisibility(View.VISIBLE);
            spalcoolidade.setVisibility(View.VISIBLE);
        } else {
            txtalcoolidade.setVisibility(View.GONE);
            spalcoolidade.setVisibility(View.GONE);
        }
    }

    public void usouMaconha(View v) {
        if (cbmaconha.isChecked()) {
            txtmaconhaidade.setVisibility(View.VISIBLE);
            spmaconhaidade.setVisibility(View.VISIBLE);
        } else {
            txtmaconhaidade.setVisibility(View.GONE);
            spmaconhaidade.setVisibility(View.GONE);
        }
    }

    public void usouCocaina(View v) {
        if (cbcocaina.isChecked()) {
            txtcocainaidade.setVisibility(View.VISIBLE);
            spcocainaidade.setVisibility(View.VISIBLE);
        } else {
            txtcocainaidade.setVisibility(View.GONE);
            spcocainaidade.setVisibility(View.GONE);
        }
    }

    public void usouCrack(View v) {
        if (cbcrack.isChecked()) {
            txtcrackidade.setVisibility(View.VISIBLE);
            spcrackidade.setVisibility(View.VISIBLE);
        } else {
            txtcrackidade.setVisibility(View.GONE);
            spcrackidade.setVisibility(View.GONE);
        }
    }

    public void usouEcstasy(View v) {
        if (cbecstasy.isChecked()) {
            txtecstasyidade.setVisibility(View.VISIBLE);
            specstasyidade.setVisibility(View.VISIBLE);
        } else {
            txtecstasyidade.setVisibility(View.GONE);
            specstasyidade.setVisibility(View.GONE);
        }
    }

    public void usouAlucinogenos(View v) {
        if (cbalucinogeno.isChecked()) {
            txtalucinogenoidade.setVisibility(View.VISIBLE);
            spalucinogenoidade.setVisibility(View.VISIBLE);
        } else {
            txtalucinogenoidade.setVisibility(View.GONE);
            spalucinogenoidade.setVisibility(View.GONE);
        }
    }

    public void usouOutras(View v) {
        if (cboutradroga.isChecked()) {
            txtoutrasidade.setVisibility(View.VISIBLE);
            spoutrasdrogaidade.setVisibility(View.VISIBLE);
            edtxtoutrasdrogas.setVisibility(View.VISIBLE);
        } else {
            txtoutrasidade.setVisibility(View.GONE);
            spoutrasdrogaidade.setVisibility(View.GONE);
            edtxtoutrasdrogas.setVisibility(View.GONE);
        }
    }

    public void trataAlcool(View v) {
        if (cbparaalcool.isChecked()) {
            txtalcoolfreq.setVisibility(View.VISIBLE);
            txtalcoolqtd.setVisibility(View.VISIBLE);
            txtalcoolultima.setVisibility(View.VISIBLE);
            spalcoolfreq.setVisibility(View.VISIBLE);
        } else {
            txtalcoolfreq.setVisibility(View.GONE);
            txtalcoolqtd.setVisibility(View.GONE);
            txtalcoolultima.setVisibility(View.GONE);
            spalcoolfreq.setVisibility(View.GONE);
        }
    }

    public void trataMaconha(View v) {
        if (cbparamaconha.isChecked()) {
            txtmaconhafreq.setVisibility(View.VISIBLE);
            txtmaconhaqtd.setVisibility(View.VISIBLE);
            txtmaconhaultima.setVisibility(View.VISIBLE);
            spmaconhafreq.setVisibility(View.VISIBLE);
        } else {
            txtmaconhafreq.setVisibility(View.GONE);
            txtmaconhaqtd.setVisibility(View.GONE);
            txtmaconhaultima.setVisibility(View.GONE);
            spmaconhafreq.setVisibility(View.GONE);
        }
    }

    public void trataCocaina(View v) {
        if (cbparacocaina.isChecked()) {
            txtcocainafreq.setVisibility(View.VISIBLE);
            txtcocainaqtd.setVisibility(View.VISIBLE);
            txtcocainaultima.setVisibility(View.VISIBLE);
            spcocainafreq.setVisibility(View.VISIBLE);
        } else {
            txtcocainafreq.setVisibility(View.GONE);
            txtcocainaqtd.setVisibility(View.GONE);
            txtcocainaultima.setVisibility(View.GONE);
            spcocainafreq.setVisibility(View.GONE);
        }
    }

    public void trataCrack(View v) {
        if (cbparacrack.isChecked()) {
            txtcrackfreq.setVisibility(View.VISIBLE);
            txtcrackqtd.setVisibility(View.VISIBLE);
            txtcrackultima.setVisibility(View.VISIBLE);
            spcrackfreq.setVisibility(View.VISIBLE);
        } else {
            txtcrackfreq.setVisibility(View.GONE);
            txtcrackqtd.setVisibility(View.GONE);
            txtcrackultima.setVisibility(View.GONE);
            spcrackfreq.setVisibility(View.GONE);
        }
    }

    public void trataEcstasy(View v) {
        if (cbparaecstasy.isChecked()) {
            txtecstasyfreq.setVisibility(View.VISIBLE);
            txtecstasyqtd.setVisibility(View.VISIBLE);
            txtecstasyultima.setVisibility(View.VISIBLE);
            specstasyfreq.setVisibility(View.VISIBLE);
        } else {
            txtecstasyfreq.setVisibility(View.GONE);
            txtecstasyqtd.setVisibility(View.GONE);
            txtecstasyultima.setVisibility(View.GONE);
            specstasyfreq.setVisibility(View.GONE);
        }
    }

    public void trataAlucinogenos(View v) {
        if (cbparaalucinogeno.isChecked()) {
            txtalucinogenofreq.setVisibility(View.VISIBLE);
            txtalucinogenoqtd.setVisibility(View.VISIBLE);
            txtalucinogenoultima.setVisibility(View.VISIBLE);
            spalucinogenofreq.setVisibility(View.VISIBLE);
        } else {
            txtalucinogenofreq.setVisibility(View.GONE);
            txtalucinogenoqtd.setVisibility(View.GONE);
            txtalucinogenoultima.setVisibility(View.GONE);
            spalucinogenofreq.setVisibility(View.GONE);
        }
    }

    public void trataOutras(View v) {
        if (cbparaoutras.isChecked()) {
            txtoutrasfreq.setVisibility(View.VISIBLE);
            txtoutrasqtd.setVisibility(View.VISIBLE);
            txtoutrasultima.setVisibility(View.VISIBLE);
            spoutrasfreq.setVisibility(View.VISIBLE);
        } else {
            txtoutrasfreq.setVisibility(View.GONE);
            txtoutrasqtd.setVisibility(View.GONE);
            txtoutrasultima.setVisibility(View.GONE);
            spoutrasfreq.setVisibility(View.GONE);
        }
    }

    public void selecionarContato1(View v) {
        Intent i = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
        startActivityForResult(i, cont1);
    }

    public void selecionarContato2(View v) {
        Intent i = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
        startActivityForResult(i, cont2);
    }

    public void selecionarContato3(View v) {
        Intent i = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
        startActivityForResult(i, cont3);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent i) {
        Uri u = i.getData();
        Cursor cursor = getContentResolver().query(u, null, null, null, null);
        int idcont = cursor.getColumnIndex(ContactsContract.Contacts._ID);
        cursor.moveToNext();
        String id = cursor.getString(idcont);


        Cursor c = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, ContactsContract.CommonDataKinds.Email.CONTACT_ID + " = ?", new String[]{id}, null);
        int num = c.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
        int nome = c.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME);

        c.moveToFirst();

        if (requestCode == cont1) {
            edtxtcontato1.setText(c.getString(num));
            txtcontato1.setText(c.getString(nome));
        } else if (requestCode == cont2) {
            edtxtcontato2.setText(c.getString(num));
            txtcontato2.setText(c.getString(nome));
        } else if (requestCode == cont3) {
            edtxtcontato3.setText(c.getString(num));
            txtcontato3.setText(c.getString(nome));
        }
    }

    public void confirmaCadastro(View v) {
        usuario.setNome(edtxtnome.getText().toString());
        usuario.setEmail(edtxtemail.getText().toString());
        usuario.setSenha(edtxtsenha.getText().toString());
        //Banco de dados remoto
        ServiceWS service = ServiceGenerator.createService(ServiceWS.class);
        ucall = service.cadastrar(usuario);
        ucall.enqueue(this);

    }
    //Resposta do webservice
    @Override
    public void onResponse(Call<Usuario> call, Response<Usuario> response) {
        if(response.isSuccessful()){

            //Banco de dados local
            db=new DBLocal(this);
            db.resetaTabela();
            db.insereUsuario(response.body());

            //Tela da home
            Intent i=new Intent(this,Home.class);
            startActivity(i);
        }else{
            Toast.makeText(this, "Usuário com este e-mail já existente.", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onFailure(Call<Usuario> call, Throwable t) {
        Toast.makeText(this,"Erro na rede, tente novamente",Toast.LENGTH_SHORT).show();
        t.printStackTrace();
    }

}