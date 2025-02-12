package beok.beok;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.orm.SugarContext;

public class Pergunta2 extends AppCompatActivity {


    Button btprox2;
    CheckBox cboutrolugar;
    EditText edtxtoutrolugar;
    Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pergunta2);

        SugarContext.init(this);

        btprox2 = (Button) findViewById(R.id.btprox2);

        cboutrolugar = (CheckBox) findViewById(R.id.cboutrolugar);

        edtxtoutrolugar = (EditText) findViewById(R.id.edtxtoutrolugar);

        bundle = getIntent().getExtras();

        if (cboutrolugar.isChecked()){
            edtxtoutrolugar.setVisibility(View.VISIBLE);
        }
    }

    public void botaoProximo2(View v){
        //adicionar as coordenadas do local em que usou
        Intent i = new Intent(this, Pergunta3.class);
        i.putExtras(bundle);
        startActivity(i);
        //slide from right to left
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }


    public void pularPergunta(View v){
        Intent i = new Intent(this, Pergunta3.class);
        i.putExtras(bundle);
        startActivity(i);
        //slide from right to left
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }

}
