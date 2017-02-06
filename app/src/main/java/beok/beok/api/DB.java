package beok.beok.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.orm.SugarRecord;

import java.util.ArrayList;
import java.util.List;

import beok.beok.POJO.Wrapper;

/**
 * Created by pietro on 04/02/17.
 */

public class DB extends SugarRecord {
    private static List<Wrapper> queue = new ArrayList<Wrapper>();
    private static ServiceSincronizer sc;
    public static long idUsuario;

    public static long save(Object object){
        //cadastra no banco de dados local
        long id=SugarRecord.save(object);

        //Cria container
        Wrapper w=new Wrapper();
        w.setType(object.getClass().getSimpleName());
        w.setContent(object);
        w.setIdObject(id);


        SugarRecord.save(w);

        //adiciona container na fila
        queue.add(w);

        return id;
    }

    public static void flush(){
        if(!queue.isEmpty()) {
            sc = new ServiceSincronizer();
            sc.sendData(queue);
        }
    }

    public static void updateQueue(){
        queue=SugarRecord.listAll(Wrapper.class);
    }

    public static String testWrapper(Object object){
        Wrapper w=new Wrapper();
        w.setType(object.getClass().getSimpleName());
        w.setContent(object);
        Gson g=new Gson();
        return g.toJson(w);
    }

    public static String testWrapperList(){
        Gson g = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
        return g.toJson(queue);
    }

    public static void emptyQueue(){
        queue.clear();
        SugarRecord.deleteAll(Wrapper.class);
    }

    public static int queueLength(){
        return queue.size();
    }
}
