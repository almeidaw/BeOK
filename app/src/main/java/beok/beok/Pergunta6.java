package beok.beok;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.orm.SugarContext;

public class Pergunta6 extends AppCompatActivity {

    EditText edtxt;
    Button btprox6;

    Bundle bundle;
    String relato;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pergunta6);

        SugarContext.init(this);

        edtxt = (EditText) findViewById(R.id.edtxt);
        btprox6 = (Button) findViewById(R.id.btprox6);

        bundle = getIntent().getExtras();
    }

    public void botaoProximo6(View v){
        //adicionar o que ele escreveu na caixa de texto

        relato=edtxt.getText().toString();

        bundle.putString("relato",relato);

        Intent i = new Intent(this, Pergunta7.class);
        i.putExtras(bundle);
        startActivity(i);
        //slide from right to left
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }

}
