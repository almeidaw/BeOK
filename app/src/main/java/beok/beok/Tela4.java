package beok.beok;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.provider.ContactsContract;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import beok.beok.POJO.ContatoEmergencia;
import beok.beok.api.DB;

public class Tela4 extends AppCompatActivity implements View.OnClickListener{

    private final int cont1 = 1; // Intent contato 1
    private final int cont2 = 2; // Intent contato 2
    private final int cont3 = 3; // Intent contato 3

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
                    editor.putString("contact1Name", txtcontato1.getText().toString());
                    editor.putString("contact1Number", edtxtcontato1.getText().toString());
                    editor.putInt("contact1Priority", 0);
                }
                if(!edtxtcontato2.getText().toString().equals("")){
                    editor.putString("contact2Name", txtcontato2.getText().toString());
                    editor.putString("contact2Number", edtxtcontato2.getText().toString());
                    editor.putInt("contact2Priority", 1);
                }
                if(!edtxtcontato3.getText().toString().equals("")){
                    editor.putString("contact3Name", txtcontato3.getText().toString());
                    editor.putString("contact3Number", edtxtcontato3.getText().toString());
                    editor.putInt("contact3Priority", 2);
                }
                if(edtxtcontato1.getText().toString().equals("")){
                    Toast.makeText(this,"Escreva pelo menos o primeiro contato", Toast.LENGTH_LONG).show();
                }else {
                    Intent nextActivity = new Intent(this, Tela5.class);
                    startActivity(nextActivity);
                    editor.commit();
                    //slide from right to left
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                }
                break;
        }
    }

    public void selecionarContato1(View v) {
         // Verifica se a permissao para acessar o Contacts foi concedida e so permite o acesso se for true
        if ((ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS))
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_CONTACTS},cont1);
        }
        else {
            Intent i = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
            startActivityForResult(i, cont1);
        }
    }
    public void selecionarContato2(View v) {
        if ((ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS))
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_CONTACTS},cont2);
        }
        else {
            Intent i = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
            startActivityForResult(i, cont2);
        }
    }
    public void selecionarContato3(View v) {
        if ((ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS))
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_CONTACTS},cont3);
        }
        else {
            Intent i = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
            startActivityForResult(i, cont3);
        }
    }

    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case cont1:
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    Intent i = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
                    startActivityForResult(i, cont1);
                }
            break;
            case cont2:
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    Intent i = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
                    startActivityForResult(i, cont2);
                }
            break;
            case cont3:
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    Intent i = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
                    startActivityForResult(i, cont3);
                }
            break;
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent i) {
        if(i!=null) {
            Uri u = i.getData();
            Cursor cursor = getContentResolver().query(u, null, null, null, null);
            int idcont = cursor.getColumnIndex(ContactsContract.Contacts._ID);
            if(cursor.moveToNext()) {
                String id = cursor.getString(idcont);

                Cursor c = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, ContactsContract.CommonDataKinds.Email.CONTACT_ID + " = ?", new String[]{id}, null);
                int num = c.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
                int nome = c.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME);

                if(c.moveToFirst()) {

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
        }
    }
    @Override
    public void onBackPressed() {

    }
}
