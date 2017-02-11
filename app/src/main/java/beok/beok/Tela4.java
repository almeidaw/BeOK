package beok.beok;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Tela4 extends AppCompatActivity implements View.OnClickListener{

    private int cont1 = 1; // Intent contato 1
    private int cont2 = 2; // Intent contato 2
    private int cont3 = 3; // Intent contato 3

    Button btfimcadastro;
    TextView txtcontato1, txtcontato2, txtcontato3;
    EditText edtxtcontato1, edtxtcontato2, edtxtcontato3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela4);


        btfimcadastro = (Button) findViewById(R.id.btfimcadastro);

        txtcontato1 = (TextView) findViewById(R.id.txtcontato1);
        txtcontato2 = (TextView) findViewById(R.id.txtcontato2);
        txtcontato3 = (TextView) findViewById(R.id.txtcontato3);

        edtxtcontato1 = (EditText) findViewById(R.id.edtxtcontato1);
        edtxtcontato2 = (EditText) findViewById(R.id.edtxtcontato2);
        edtxtcontato3 = (EditText) findViewById(R.id.edtxtcontato3);

        btfimcadastro.setOnClickListener(this);

    }

    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btfimcadastro:
                Intent nextActivity = new Intent(this, Main.class);
                startActivity(nextActivity);
                //slide from right to left
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
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
