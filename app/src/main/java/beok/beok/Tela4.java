package beok.beok;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import beok.beok.POJO.ContatoEmergencia;
import beok.beok.api.DB;

public class Tela4 extends AppCompatActivity implements View.OnClickListener{

    private int cont1 = 1; // Intent contato 1
    private int cont2 = 2; // Intent contato 2
    private int cont3 = 3; // Intent contato 3

    Button btfimcadastro;
    EditText txtcontato1, txtcontato2, txtcontato3;
    EditText edtxtcontato1, edtxtcontato2, edtxtcontato3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela4);


        btfimcadastro = (Button) findViewById(R.id.btfimcadastro);

        txtcontato1 = (EditText) findViewById(R.id.txtcontato1);
        txtcontato2 = (EditText) findViewById(R.id.txtcontato2);
        txtcontato3 = (EditText) findViewById(R.id.txtcontato3);

        edtxtcontato1 = (EditText) findViewById(R.id.edtxtcontato1);
        edtxtcontato2 = (EditText) findViewById(R.id.edtxtcontato2);
        edtxtcontato3 = (EditText) findViewById(R.id.edtxtcontato3);

        btfimcadastro.setOnClickListener(this);

    }

    public void onClick(View v) {

        SharedPreferences SP = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = SP.edit();

        switch (v.getId()) {
            case R.id.btfimcadastro:
                if(!edtxtcontato1.getText().toString().equals("")){
                    ContatoEmergencia ce=new ContatoEmergencia();
                    ce.setNome(txtcontato1.getText().toString());
                    ce.setTelefone(edtxtcontato1.getText().toString());
                    ce.setPrioridade(0);
                    DB.save(ce);

                    editor.putString("contact1Name", txtcontato1.getText().toString());
                    editor.putString("contact1Number", edtxtcontato1.getText().toString());
                    editor.putInt("contact1Priority", 0);

                }
                if(!edtxtcontato2.getText().toString().equals("")){
                    ContatoEmergencia ce=new ContatoEmergencia();
                    ce.setNome(edtxtcontato2.getText().toString());
                    ce.setTelefone(txtcontato2.getText().toString());
                    ce.setPrioridade(1);
                    DB.save(ce);

                    editor.putString("contact2Name", txtcontato2.getText().toString());
                    editor.putString("contact2Number", edtxtcontato2.getText().toString());
                    editor.putInt("contact2Priority", 1);
                }
                if(!edtxtcontato3.getText().toString().equals("")){
                    ContatoEmergencia ce=new ContatoEmergencia();
                    ce.setNome(txtcontato3.getText().toString());
                    ce.setTelefone(edtxtcontato3.getText().toString());
                    ce.setPrioridade(2);
                    DB.save(ce);

                    editor.putString("contact3Name", txtcontato3.getText().toString());
                    editor.putString("contact3Number", edtxtcontato3.getText().toString());
                    editor.putInt("contact3Priority", 2);
                }
                Intent nextActivity = new Intent(this, Atividade1.class);
                startActivity(nextActivity);
                //slide from right to left
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);

                editor.commit();
                break;
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
}
