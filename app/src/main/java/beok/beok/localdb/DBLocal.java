package beok.beok.localdb;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import beok.beok.POJO.Usuario;

/**
 * Created by Tomás on 28/01/2017.
 */
public class DBLocal extends SQLiteOpenHelper {


    public static String NOME_DB = "beok";

    public static String NOME_TABELA_USUARIO = "usuario";

    public static String COLUNA_ID = "id";
    public static String COLUNA_NOME = "nome";
    public static String COLUNA_IDADE = "idade";
    public static String COLUNA_GENERO = "genero";
    public static String COLUNA_SENHA = "senha";
    public static String COLUNA_EMAIL = "email";

    public static String CRIA_TABELA_USUARIO = "create table usuario (id integer primary key autoincrement, nome text, idade integer, genero text, senha text, email text)";
    public static String DROP_TABELA_USUARIO = "drop table if exists usuario";



    public DBLocal(Context context){
        super(context, NOME_DB, null, 1);
        SQLiteDatabase db = getWritableDatabase();
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CRIA_TABELA_USUARIO);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL(DROP_TABELA_USUARIO);
        onCreate(db);
    }

    public void resetaTabela(){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(DROP_TABELA_USUARIO);
        db.execSQL(CRIA_TABELA_USUARIO);
    }




    //método para inserir usuário, recebe um objeto da classe Usuario
    public void insereUsuario(Usuario usuario){

        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COLUNA_NOME, usuario.getNome());
        values.put(COLUNA_IDADE, usuario.getIdade());
        values.put(COLUNA_GENERO, usuario.getGenero());
        values.put(COLUNA_SENHA, usuario.getSenha());
        values.put(COLUNA_EMAIL, usuario.getEmail());


        db.insert(NOME_TABELA_USUARIO, null, values);
    }




    //método para percorrer a tabela usuário
    public Usuario obterUsuario(){

        SQLiteDatabase db = getWritableDatabase();
        String seleciona = "select * from usuario";

        Cursor cursor = db.rawQuery(seleciona, null);
        cursor.moveToFirst();
        Usuario us=new Usuario();
        us.setId(cursor.getInt(cursor.getColumnIndexOrThrow("id")));
        us.setEmail(cursor.getString(cursor.getColumnIndexOrThrow("email")));
        us.setGenero(cursor.getString(cursor.getColumnIndexOrThrow("genero")));
        us.setIdade(cursor.getInt(cursor.getColumnIndexOrThrow("idade")));
        us.setNome(cursor.getString(cursor.getColumnIndexOrThrow("nome")));
        us.setSenha(cursor.getString(cursor.getColumnIndexOrThrow("senha")));
        return us;
    }
    public boolean usuarioLogado(){
        SQLiteDatabase db=getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from usuario",null);
        return !(cursor.getCount()==0);
    }



}