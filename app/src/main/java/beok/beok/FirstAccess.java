package beok.beok;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class FirstAccess extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_access);
    }
    public void loginClickFirst(View v){
        Intent i = new Intent(this, Login.class);
        startActivity(i);
    }
    public void cadastroClickFirst(View v){
        Intent i = new Intent(this, Cadastro.class);
        startActivity(i);
    }
    @Override
    public void onBackPressed() {

    }
}
