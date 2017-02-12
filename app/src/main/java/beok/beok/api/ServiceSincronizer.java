package beok.beok.api;

import android.content.Context;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.orm.SugarRecord;

import beok.beok.POJO.CreatedObjects;
import beok.beok.POJO.DataTeste;
import beok.beok.POJO.Wrapper;
import beok.beok.webservice.ServiceWS;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by pietro on 04/02/17.
 */

public class ServiceSincronizer implements Callback<CreatedObjects> {
    Call<CreatedObjects> call;
    ServiceWS service;

    public static Context scContext;

    Context c;

    public ServiceSincronizer(){
        service = ServiceGenerator.createService(ServiceWS.class);
    }

    public void sendData(Object object){
        call = service.create(object,DB.idUsuario);
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
        //Toast.makeText(scContext,gson.toJson(object), Toast.LENGTH_SHORT).show();
        call.enqueue(this);
    }

    public void test(){
        call = service.teste();
        call.enqueue(this);
    }
    @Override
    public void onResponse(Call<CreatedObjects> call, Response<CreatedObjects> response) {
        if(response.body()!=null && (response.body().getCreatedObjects() == response.body().getReceivedObjects()) && (response.body().getReceivedObjects() == DB.queueLength())){
            DB.emptyQueue();
            //Toast.makeText(scContext,response.body().getCreatedObjects()+"", Toast.LENGTH_SHORT).show();
        }else{
            //Toast.makeText(scContext,"nao", Toast.LENGTH_SHORT).show();
            //Toast.makeText(scContext,"nao: created="+response.body().getCreatedObjects(), Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onFailure(Call<CreatedObjects> call, Throwable throwable) {
        //Toast.makeText(scContext,"falha", Toast.LENGTH_SHORT).show();
    }
}
