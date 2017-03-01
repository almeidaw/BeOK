package beok.beok.api;

import com.orm.SugarRecord;

import java.util.List;

import beok.beok.POJO.MetaGeral;
import beok.beok.POJO.Usuario;

/**
 * Created by pietro on 12/02/17.
 */

public class Conf {
    public static int getIdUsuario(){
        List<Usuario> us=SugarRecord.listAll(Usuario.class);
        return (int)(long)us.get(0).getId();
    }
    public static String getNomeUsuario(){
        List<Usuario> us=SugarRecord.listAll(Usuario.class);
        return us.get(0).getNome();
    }/*
    public static boolean getTratamentoAlcool(){

    }
    public static boolean getTratamentoMaconha(){

    }
    public static boolean getTratamentoCrack(){

    }
    public static boolean getTratamentoCocaina(){

    }*/

}
