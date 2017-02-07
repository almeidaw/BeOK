package beok.beok;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


import beok.beok.POJO.ConsumoAtual;
import beok.beok.POJO.MetaGeral;
import beok.beok.POJO.Usuario;
import beok.beok.api.DB;
import beok.beok.api.ServiceGenerator;
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
import android.widget.ViewSwitcher;

import com.orm.SugarContext;
import com.orm.SugarRecord;

public class Cadastro extends AppCompatActivity implements Callback<Usuario> {

    Call<Usuario> ucall;

    Usuario usuario;

    private int cont1 = 1; // Intent contato 1
    private int cont2 = 2; // Intent contato 2
    private int cont3 = 3; // Intent contato 3

    Handler setDelay;   //Necessario para fazer
    Runnable startDelay;//o delay

    ViewSwitcher vs1, vs2, vs2_1, vs2_2, vs2_3, vs2_4, vs2_7,
            vs3, vs3_1, vs3_2, vs3_3, vs3_4, vs3_7, vs4;

    Animation left, right;

    Button btproximo1, btproximo2, btproximo3, btproximo4, btfimcadastro,
            btalcoolidade, btmaconhaidade, btcocainaidade, btcrackidade, btoutrasidade,
            bttrataalcool, bttratamaconha, bttratacocaina, bttratacrack, bttrataoutras;

    CheckBox cbalcool, cbmaconha, cbcocaina, cbcrack, cboutradroga,
            cbparaalcool, cbparamaconha, cbparacrack, cbparacocaina, cbparaoutras,
            cbfamilia, cbdinheiro, cbtrabalho, cbestudos, cbsfisica, cbsemocional, cboutros;

    TextView txtalcoolidade, txtmaconhaidade, txtcocainaidade, txtcrackidade, txtoutrasidade,
            txtalcoolfreq, txtmaconhafreq, txtcocainafreq, txtcrackfreq, txtoutrasfreq,
            txtalcoolqtd, txtmaconhaqtd, txtcocainaqtd, txtcrackqtd, txtoutrasqtd,
            txtalcoolultima, txtmaconhaultima, txtcocainaultima, txtcrackultima, txtoutrasultima,
            txtcontato1, txtcontato2, txtcontato3,
            txtdrogas, txtdrogasparar;

    Spinner spidade, spgenero,
            spalcoolidade, spmaconhaidade, spcocainaidade, spcrackidade, spoutrasdrogaidade,
            spalcoolfreq, spmaconhafreq, spcocainafreq, spcrackfreq, spoutrasfreq,
            spfinalalcool, spfinalmaconha, spfinalcocaina, spfinalcrack, spfinaloutras,
            spreduziralcool, spreduzirmaconha, spreduzircocaina, spreduzircrack, spreduziroutras;

    EditText edtxtnome, edtxtemail, edtxtsenha,
            edtxtoutrasdrogas,
            edtxtoutramot,
            edtxtcontato1, edtxtcontato2, edtxtcontato3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        SugarContext.init(this);

        cbalcool = (CheckBox) findViewById(R.id.cbalcool);
        cbmaconha = (CheckBox) findViewById(R.id.cbmaconha);
        cbcocaina = (CheckBox) findViewById(R.id.cbcocaina);
        cbcrack = (CheckBox) findViewById(R.id.cbcrack);
        cboutradroga = (CheckBox) findViewById(R.id.cboutradroga);

        cbparaalcool = (CheckBox) findViewById(R.id.cbparaalcool);
        cbparamaconha = (CheckBox) findViewById(R.id.cbparamaconha);
        cbparacocaina = (CheckBox) findViewById(R.id.cbparacocaina);
        cbparacrack = (CheckBox) findViewById(R.id.cbparacrack);
        cbparaoutras = (CheckBox) findViewById(R.id.cbparaoutras);

        cbfamilia = (CheckBox) findViewById(R.id.cbfamilia);
        cbdinheiro = (CheckBox) findViewById(R.id.cbdinheiro);
        cbsemocional = (CheckBox) findViewById(R.id.cbsemocional);
        cbsfisica = (CheckBox) findViewById(R.id.cbsfisica);
        cbtrabalho = (CheckBox) findViewById(R.id.cbtrabalho);
        cbestudos = (CheckBox) findViewById(R.id.cbestudos);
        cboutros = (CheckBox) findViewById(R.id.cboutros);

        txtdrogas = (TextView) findViewById(R.id.txtdrogas);

        txtalcoolidade = (TextView) findViewById(R.id.txtalcoolidade);
        txtmaconhaidade = (TextView) findViewById(R.id.txtmaconhaidade);
        txtcocainaidade = (TextView) findViewById(R.id.txtcocainaidade);
        txtcrackidade = (TextView) findViewById(R.id.txtcrackidade);
        txtoutrasidade = (TextView) findViewById(R.id.txtoutrasidade);

        txtdrogasparar = (TextView) findViewById(R.id.txtdrogasparar);

        txtalcoolfreq = (TextView) findViewById(R.id.txtalcoolfreq);
        txtmaconhafreq = (TextView) findViewById(R.id.txtmaconhafreq);
        txtcocainafreq = (TextView) findViewById(R.id.txtcocainafreq);
        txtcrackfreq = (TextView) findViewById(R.id.txtcrackfreq);
        txtoutrasfreq = (TextView) findViewById(R.id.txtoutrasfreq);
/*
        TEXTVIEW PARA AS OUTRAS PERGUNTAS

        txtalcoolqtd = (TextView) findViewById(R.id.txtalcoolqtd);
        txtmaconhaqtd = (TextView) findViewById(R.id.txtmaconhaqtd);
        txtcocainaqtd = (TextView) findViewById(R.id.txtcocainaqtd);
        txtcrackqtd = (TextView) findViewById(R.id.txtcrackqtd);\
        txtoutrasqtd = (TextView) findViewById(R.id.txtoutrasqtd);

        txtalcoolultima = (TextView) findViewById(R.id.txtalcoolultima);
        txtmaconhaultima = (TextView) findViewById(R.id.txtmaconhaultima);
        txtcocainaultima = (TextView) findViewById(R.id.txtcocainaultima);
        txtcrackultima = (TextView) findViewById(R.id.txtcrackultima);\
        txtoutrasultima = (TextView) findViewById(R.id.txtoutrasultima);

*/
        txtcontato1 = (TextView) findViewById(R.id.txtcontato1);
        txtcontato2 = (TextView) findViewById(R.id.txtcontato2);
        txtcontato3 = (TextView) findViewById(R.id.txtcontato3);

        spidade = (Spinner) findViewById(R.id.spidade);
        spgenero = (Spinner) findViewById(R.id.spgenero);

        spalcoolidade = (Spinner) findViewById(R.id.spalcoolidade);
        spmaconhaidade = (Spinner) findViewById(R.id.spmaconhaidade);
        spcocainaidade = (Spinner) findViewById(R.id.spcocainaidade);
        spcrackidade = (Spinner) findViewById(R.id.spcrackidade);
        spoutrasdrogaidade = (Spinner) findViewById(R.id.spoutrasidade);

        spalcoolfreq = (Spinner) findViewById(R.id.spalcoolfreq);
        spmaconhafreq = (Spinner) findViewById(R.id.spmaconhafreq);
        spcocainafreq = (Spinner) findViewById(R.id.spcocainafreq);
        spcrackfreq = (Spinner) findViewById(R.id.spcrackfreq);
        spoutrasfreq = (Spinner) findViewById(R.id.spoutrasfreq);

        spfinalalcool = (Spinner) findViewById(R.id.spfinalalcool);
        spfinalmaconha = (Spinner) findViewById(R.id.spfinalmaconha);
        spfinalcocaina = (Spinner) findViewById(R.id.spfinalcocaina);
        spfinalcrack = (Spinner) findViewById(R.id.spfinalcrack);
        spfinaloutras = (Spinner) findViewById(R.id.spfinaloutras);

        spreduziralcool = (Spinner) findViewById(R.id.spreduziralcool);
        spreduzirmaconha = (Spinner) findViewById(R.id.spreduzirmaconha);
        spreduzircocaina = (Spinner) findViewById(R.id.spreduzircocaina);
        spreduzircrack = (Spinner) findViewById(R.id.spreduzircrack);
        spreduziroutras = (Spinner) findViewById(R.id.spreduziroutras);

        edtxtnome = (EditText) findViewById(R.id.edtextnome);
        edtxtemail = (EditText) findViewById(R.id.edtxtemail);
        edtxtsenha = (EditText) findViewById(R.id.edtxtsenha);

        edtxtoutrasdrogas = (EditText) findViewById(R.id.edtxtoutrasdrogas);

        edtxtoutramot = (EditText) findViewById(R.id.edtxtoutramot);

        edtxtcontato1 = (EditText) findViewById(R.id.edtxtcontato1);
        edtxtcontato2 = (EditText) findViewById(R.id.edtxtcontato2);
        edtxtcontato3 = (EditText) findViewById(R.id.edtxtcontato3);

        /// ---------------- ////////// ------------ /////////

        btproximo1 = (Button) findViewById(R.id.btproximo1);
        btproximo2 = (Button) findViewById(R.id.btproximo2);
        btproximo3 = (Button) findViewById(R.id.btproximo3);
        btproximo4 = (Button) findViewById(R.id.btproximo4);
        btfimcadastro = (Button) findViewById(R.id.btfimcadastro);

        btalcoolidade = (Button) findViewById(R.id.btalcoolidade);
        btmaconhaidade = (Button) findViewById(R.id.btmaconhaidade);
        btcocainaidade = (Button) findViewById(R.id.btcocainaidade);
        btcrackidade = (Button) findViewById(R.id.btcrackidade);
        btoutrasidade = (Button) findViewById(R.id.btoutrasidade);

        bttrataalcool = (Button) findViewById(R.id.bttrataalcool);
        bttratamaconha = (Button) findViewById(R.id.bttratamaconha);
        bttratacocaina = (Button) findViewById(R.id.bttratacocaina);
        bttratacrack = (Button) findViewById(R.id.bttratacrack);
        bttrataoutras = (Button) findViewById(R.id.bttrataoutras);

        vs1 = (ViewSwitcher) findViewById(R.id.vs1);
        vs2 = (ViewSwitcher) findViewById(R.id.vs2);
        vs2_1 = (ViewSwitcher) findViewById(R.id.vs2_1);
        vs2_2 = (ViewSwitcher) findViewById(R.id.vs2_2);
        vs2_3 = (ViewSwitcher) findViewById(R.id.vs2_3);
        vs2_4 = (ViewSwitcher) findViewById(R.id.vs2_4);
        vs2_7 = (ViewSwitcher) findViewById(R.id.vs2_7);
        vs3 = (ViewSwitcher) findViewById(R.id.vs3);
        vs3_1 = (ViewSwitcher) findViewById(R.id.vs3_1);
        vs3_2 = (ViewSwitcher) findViewById(R.id.vs3_2);
        vs3_3 = (ViewSwitcher) findViewById(R.id.vs3_3);
        vs3_4 = (ViewSwitcher) findViewById(R.id.vs3_4);
        vs3_7 = (ViewSwitcher) findViewById(R.id.vs3_7);
        vs4 = (ViewSwitcher) findViewById(R.id.vs4);

        // --------------- Animacao entre as telas ------------------------------ //
        left = AnimationUtils.loadAnimation(this, android.R.anim.slide_in_left);
        right = AnimationUtils.loadAnimation(this, android.R.anim.slide_out_right);

        vs1.setInAnimation(left);vs1.setOutAnimation(right);
        vs2.setInAnimation(left);vs2.setOutAnimation(right);
        vs2_1.setInAnimation(left);vs2_1.setOutAnimation(right);
        vs2_2.setInAnimation(left);vs2_2.setOutAnimation(right);
        vs2_3.setInAnimation(left);vs2_3.setOutAnimation(right);
        vs2_4.setInAnimation(left);vs2_4.setOutAnimation(right);
        vs2_7.setInAnimation(left);vs2_7.setOutAnimation(right);
        vs3.setInAnimation(left);vs3.setOutAnimation(right);
        vs3_1.setInAnimation(left);vs3_1.setOutAnimation(right);
        vs3_2.setInAnimation(left);vs3_2.setOutAnimation(right);
        vs3_3.setInAnimation(left);vs3_3.setOutAnimation(right);
        vs3_4.setInAnimation(left);vs3_4.setOutAnimation(right);
        vs3_7.setInAnimation(left);vs3_7.setOutAnimation(right);
        vs4.setInAnimation(left);vs4.setOutAnimation(right);
        // ------------------------ // ----------------------------------------- //

        usuario = new Usuario();

        setDelay = new Handler(); //Delay

        spfinalalcool.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (spfinalalcool.getItemAtPosition(position).equals("Reduzir o uso")){
                    spreduziralcool.setVisibility(View.VISIBLE);
                }else{spreduziralcool.setVisibility(View.GONE);}
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spfinalmaconha.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (spfinalmaconha.getItemAtPosition(position).equals("Reduzir o uso")){
                    spreduzirmaconha.setVisibility(View.VISIBLE);
                }else{spreduzirmaconha.setVisibility(View.GONE);}
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spfinalcocaina.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (spfinalcocaina.getItemAtPosition(position).equals("Reduzir o uso")){
                    spreduzircocaina.setVisibility(View.VISIBLE);
                }else{spreduzircocaina.setVisibility(View.GONE);}
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spfinalcrack.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (spfinalcrack.getItemAtPosition(position).equals("Reduzir o uso")){
                    spreduzircrack.setVisibility(View.VISIBLE);
                }else{spreduzircrack.setVisibility(View.GONE);}
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spfinaloutras.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (spfinaloutras.getItemAtPosition(position).equals("Reduzir o uso")){
                    spreduziroutras.setVisibility(View.VISIBLE);
                }else{spreduziroutras.setVisibility(View.GONE);}
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        // Colocando as informacoes do usuario no banco de dados interno
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


    }
    public void botaoProximo1(View v){
        vs1.showNext();
    }
    public void botaoProximo2(View v){
        vs2.showNext();
    }
    public void botaoProximo3(View v){
        vs3.showNext();
    }
    public void botaoProximo4(View v){
        vs4.showNext();
    }
    public void salvaDroga(CheckBox check,Spinner spfreq,Spinner spfinal, int tipo, String outros){
        if (check.isChecked()){
            ConsumoAtual c=new ConsumoAtual();
            c.setTipo(tipo);
            if(outros!=null){
                c.setOutros(outros);
            }
            int pos=spfreq.getSelectedItemPosition();
            switch (pos){
                case 0:
                    c.setFreqSemanal(7);
                    c.setFreqDia(1);
                    c.setFimDeSem(false);
                    break;
                case 1:
                    c.setFreqDia(0);
                    c.setFreqSemanal(7);
                    c.setFimDeSem(false);
                    break;
                case 2:
                    c.setFreqSemanal(4);
                    c.setFreqDia(0);
                    c.setFimDeSem(false);
                case 3:
                    c.setFimDeSem(true);
                    c.setFreqSemanal(0);
                    c.setFreqDia(0);
                    break;
            }
            MetaGeral mg=new MetaGeral();
            if(spfinal.getSelectedItemPosition()==0) {
                mg.setFreqDia(0);
            }else{
                mg.setFreqDia(1);
            }
            DB.save(mg);
            DB.save(c);
        }
    }
    public void FinalizarCadastro(View v) {
        salvaDroga(cbalcool,spalcoolfreq,spfinalalcool,0,null);
        salvaDroga(cbmaconha,spmaconhafreq,spfinalmaconha,1,null);
        salvaDroga(cbcocaina,spcocainafreq,spfinalcocaina,2,null);
        salvaDroga(cbcrack,spcrackfreq,spfinalcrack,3,null);
        salvaDroga(cboutros,spoutrasfreq,spfinaloutras,3,edtxtoutrasdrogas.getText().toString());
        if(cbfamilia.isChecked()) {
            usuario.setMotivacao(0);
        }else if(cbdinheiro.isChecked()){
            usuario.setMotivacao(0);
        }

        usuario.setNome(edtxtnome.getText().toString());
        usuario.setEmail(edtxtemail.getText().toString());
        usuario.setSenha(edtxtsenha.getText().toString());
        //Banco de dados remoto
        ServiceWS service = ServiceGenerator.createService(ServiceWS.class);
        ucall = service.cadastrar(usuario);
        ucall.enqueue(this);
    }

    public void botaoAlcoolIdade(View v){
        vs2_1.showPrevious();
        startDelay = new Runnable() {
            @Override
            public void run() {
                txtdrogas.setVisibility(View.VISIBLE);
                cbalcool.setVisibility(View.VISIBLE);
                cbmaconha.setVisibility(View.VISIBLE);
                cbcocaina.setVisibility(View.VISIBLE);
                cbcrack.setVisibility(View.VISIBLE);
                cboutradroga.setVisibility(View.VISIBLE);
                btproximo2.setVisibility(View.VISIBLE);
            }
        };
        setDelay.postDelayed(startDelay, 400);
    }
    public void botaoMaconhaIdade(View v){
        vs2_2.showPrevious();
        startDelay = new Runnable() {
            @Override
            public void run() {
                txtdrogas.setVisibility(View.VISIBLE);
                cbalcool.setVisibility(View.VISIBLE);
                cbmaconha.setVisibility(View.VISIBLE);
                cbcocaina.setVisibility(View.VISIBLE);
                cbcrack.setVisibility(View.VISIBLE);
                cboutradroga.setVisibility(View.VISIBLE);
                btproximo2.setVisibility(View.VISIBLE);
            }
        };
        setDelay.postDelayed(startDelay, 400);
    }
    public void botaoCocainaIdade(View v){
        vs2_3.showPrevious();
        startDelay = new Runnable() {
            @Override
            public void run() {
                txtdrogas.setVisibility(View.VISIBLE);
                cbalcool.setVisibility(View.VISIBLE);
                cbmaconha.setVisibility(View.VISIBLE);
                cbcocaina.setVisibility(View.VISIBLE);
                cbcrack.setVisibility(View.VISIBLE);
                cboutradroga.setVisibility(View.VISIBLE);
                btproximo2.setVisibility(View.VISIBLE);
            }
        };
        setDelay.postDelayed(startDelay, 400);
    }
    public void botaoCrackIdade(View v){
        vs2_4.showPrevious();
        startDelay = new Runnable() {
            @Override
            public void run() {
                txtdrogas.setVisibility(View.VISIBLE);
                cbalcool.setVisibility(View.VISIBLE);
                cbmaconha.setVisibility(View.VISIBLE);
                cbcocaina.setVisibility(View.VISIBLE);
                cbcrack.setVisibility(View.VISIBLE);
                cboutradroga.setVisibility(View.VISIBLE);
                btproximo2.setVisibility(View.VISIBLE);
            }
        };
        setDelay.postDelayed(startDelay, 400);
    }
    public void botaoOutrasIdade(View v){
        vs2_7.showPrevious();
        startDelay = new Runnable() {
            @Override
            public void run() {
                txtdrogas.setVisibility(View.VISIBLE);
                cbalcool.setVisibility(View.VISIBLE);
                cbmaconha.setVisibility(View.VISIBLE);
                cbcocaina.setVisibility(View.VISIBLE);
                cbcrack.setVisibility(View.VISIBLE);
                cboutradroga.setVisibility(View.VISIBLE);
                btproximo2.setVisibility(View.VISIBLE);
            }
        };
        setDelay.postDelayed(startDelay, 400);
    }

    public void botaoTrataAlcool(View v){
        vs3_1.showPrevious();
        startDelay = new Runnable() {
            @Override
            public void run() {
                txtdrogasparar.setVisibility(View.VISIBLE);
                cbparaalcool.setVisibility(View.VISIBLE);
                cbparamaconha.setVisibility(View.VISIBLE);
                cbparacocaina.setVisibility(View.VISIBLE);
                cbparacrack.setVisibility(View.VISIBLE);
                cbparaoutras.setVisibility(View.VISIBLE);
                btproximo3.setVisibility(View.VISIBLE);
            }
        };
        setDelay.postDelayed(startDelay, 400);
    }
    public void botaoTrataMaconha(View v){
        vs3_2.showPrevious();
        startDelay = new Runnable() {
            @Override
            public void run() {
                txtdrogasparar.setVisibility(View.VISIBLE);
                cbparaalcool.setVisibility(View.VISIBLE);
                cbparamaconha.setVisibility(View.VISIBLE);
                cbparacocaina.setVisibility(View.VISIBLE);
                cbparacrack.setVisibility(View.VISIBLE);
                cbparaoutras.setVisibility(View.VISIBLE);
                btproximo3.setVisibility(View.VISIBLE);
            }
        };
        setDelay.postDelayed(startDelay, 400);
    }
    public void botaoTrataCocaina(View v){
        vs3_3.showPrevious();
        startDelay = new Runnable() {
            @Override
            public void run() {
                txtdrogasparar.setVisibility(View.VISIBLE);
                cbparaalcool.setVisibility(View.VISIBLE);
                cbparamaconha.setVisibility(View.VISIBLE);
                cbparacocaina.setVisibility(View.VISIBLE);
                cbparacrack.setVisibility(View.VISIBLE);
                cbparaoutras.setVisibility(View.VISIBLE);
                btproximo3.setVisibility(View.VISIBLE);
            }
        };
        setDelay.postDelayed(startDelay, 400);
    }
    public void botaoTrataCrack(View v){
        vs3_4.showPrevious();
        startDelay = new Runnable() {
            @Override
            public void run() {
                txtdrogasparar.setVisibility(View.VISIBLE);
                cbparaalcool.setVisibility(View.VISIBLE);
                cbparamaconha.setVisibility(View.VISIBLE);
                cbparacocaina.setVisibility(View.VISIBLE);
                cbparacrack.setVisibility(View.VISIBLE);
                cbparaoutras.setVisibility(View.VISIBLE);
                btproximo3.setVisibility(View.VISIBLE);
            }
        };
        setDelay.postDelayed(startDelay, 400);
    }
    public void botaoTrataOutas(View v){
        vs3_7.showPrevious();
        startDelay = new Runnable() {
            @Override
            public void run() {
                txtdrogasparar.setVisibility(View.VISIBLE);
                cbparaalcool.setVisibility(View.VISIBLE);
                cbparamaconha.setVisibility(View.VISIBLE);
                cbparacocaina.setVisibility(View.VISIBLE);
                cbparacrack.setVisibility(View.VISIBLE);
                cbparaoutras.setVisibility(View.VISIBLE);
                btproximo3.setVisibility(View.VISIBLE);
            }
        };
        setDelay.postDelayed(startDelay, 400);

    }

    public void usouAlcool(View v) {
        vs2_1.showNext();
        txtdrogas.setVisibility(View.GONE);
        cbalcool.setVisibility(View.GONE);
        cbmaconha.setVisibility(View.GONE);
        cbcocaina.setVisibility(View.GONE);
        cbcrack.setVisibility(View.GONE);
        cboutradroga.setVisibility(View.GONE);
        btproximo2.setVisibility(View.GONE);
    }
    public void usouMaconha(View v) {
        vs2_2.showNext();
        txtdrogas.setVisibility(View.GONE);
        cbalcool.setVisibility(View.GONE);
        cbmaconha.setVisibility(View.GONE);
        cbcocaina.setVisibility(View.GONE);
        cbcrack.setVisibility(View.GONE);
        cboutradroga.setVisibility(View.GONE);
        btproximo2.setVisibility(View.GONE);
    }
    public void usouCocaina(View v) {
        vs2_3.showNext();
        txtdrogas.setVisibility(View.GONE);
        cbalcool.setVisibility(View.GONE);
        cbmaconha.setVisibility(View.GONE);
        cbcocaina.setVisibility(View.GONE);
        cbcrack.setVisibility(View.GONE);
        cboutradroga.setVisibility(View.GONE);
        btproximo2.setVisibility(View.GONE);
    }
    public void usouCrack(View v) {
        vs2_4.showNext();
        txtdrogas.setVisibility(View.GONE);
        cbalcool.setVisibility(View.GONE);
        cbmaconha.setVisibility(View.GONE);
        cbcocaina.setVisibility(View.GONE);
        cbcrack.setVisibility(View.GONE);
        cboutradroga.setVisibility(View.GONE);
        btproximo2.setVisibility(View.GONE);
    }
    public void usouOutras(View v) {
        vs2_7.showNext();
        txtdrogas.setVisibility(View.GONE);
        cbalcool.setVisibility(View.GONE);
        cbmaconha.setVisibility(View.GONE);
        cbcocaina.setVisibility(View.GONE);
        cbcrack.setVisibility(View.GONE);
        cboutradroga.setVisibility(View.GONE);
        btproximo2.setVisibility(View.GONE);
    }

    public void trataAlcool(View v) {
        vs3_1.showNext();
        txtdrogasparar.setVisibility(View.GONE);
        cbparaalcool.setVisibility(View.GONE);
        cbparamaconha.setVisibility(View.GONE);
        cbparacocaina.setVisibility(View.GONE);
        cbparacrack.setVisibility(View.GONE);
        cbparaoutras.setVisibility(View.GONE);
        btproximo3.setVisibility(View.GONE);

    }
    public void trataMaconha(View v) {
        vs3_2.showNext();
        txtdrogasparar.setVisibility(View.GONE);
        cbparaalcool.setVisibility(View.GONE);
        cbparamaconha.setVisibility(View.GONE);
        cbparacocaina.setVisibility(View.GONE);
        cbparacrack.setVisibility(View.GONE);
        cbparaoutras.setVisibility(View.GONE);
        btproximo3.setVisibility(View.GONE);
    }
    public void trataCocaina(View v) {
        vs3_3.showNext();
        txtdrogasparar.setVisibility(View.GONE);
        cbparaalcool.setVisibility(View.GONE);
        cbparamaconha.setVisibility(View.GONE);
        cbparacocaina.setVisibility(View.GONE);
        cbparacrack.setVisibility(View.GONE);
        cbparaoutras.setVisibility(View.GONE);
        btproximo3.setVisibility(View.GONE);
    }
    public void trataCrack(View v) {
        vs3_4.showNext();
        txtdrogasparar.setVisibility(View.GONE);
        cbparaalcool.setVisibility(View.GONE);
        cbparamaconha.setVisibility(View.GONE);
        cbparacocaina.setVisibility(View.GONE);
        cbparacrack.setVisibility(View.GONE);
        cbparaoutras.setVisibility(View.GONE);
        btproximo3.setVisibility(View.GONE);
    }
    public void trataOutras(View v) {
        vs3_7.showNext();
        txtdrogasparar.setVisibility(View.GONE);
        cbparaalcool.setVisibility(View.GONE);
        cbparamaconha.setVisibility(View.GONE);
        cbparacocaina.setVisibility(View.GONE);
        cbparacrack.setVisibility(View.GONE);
        cbparaoutras.setVisibility(View.GONE);
        btproximo3.setVisibility(View.GONE);
    }

    public void outrasMotivacoes(View v){
        if (cboutros.isChecked()){
            edtxtoutramot.setVisibility(View.VISIBLE);
        }else {
            edtxtoutramot.setVisibility(View.GONE);
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


    //Resposta do webservice
    @Override
    public void onResponse(Call<Usuario> call, Response<Usuario> response) {
        if(response.isSuccessful()){

            //Banco de dados local
            Usuario u=response.body();
            SugarRecord.save(u);

            DB.idUsuario=u.getId();

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