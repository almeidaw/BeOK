package beok.beok;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

public class Tela3 extends AppCompatActivity implements View.OnClickListener{

    Button btproximo3;
    CheckBox cbfamilia, cbdinheiro, cbtrabalho, cbestudos, cbsfisica, cbsemocional, cboutros;
    EditText edtxtoutramot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela3);

        btproximo3 = (Button) findViewById(R.id.btproximo3);

        cbfamilia = (CheckBox) findViewById(R.id.cbfamilia);
        cbdinheiro = (CheckBox) findViewById(R.id.cbdinheiro);
        cbsemocional = (CheckBox) findViewById(R.id.cbsemocional);
        cbsfisica = (CheckBox) findViewById(R.id.cbsfisica);
        cbtrabalho = (CheckBox) findViewById(R.id.cbtrabalho);
        cbestudos = (CheckBox) findViewById(R.id.cbestudos);
        cboutros = (CheckBox) findViewById(R.id.cboutros);

        edtxtoutramot = (EditText) findViewById(R.id.edtxtoutramot);

        btproximo3.setOnClickListener(this);
    }

    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btproximo3:
                Intent nextActivity = new Intent(this, Tela4.class);
                startActivity(nextActivity);
                //slide from right to left
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                break;
        }
    }

    public void outrasMotivacoes(View v){
        if (cboutros.isChecked()){
            edtxtoutramot.setVisibility(View.VISIBLE);
        }else {
            edtxtoutramot.setVisibility(View.GONE);
        }
    }
}
