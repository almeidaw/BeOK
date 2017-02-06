package beok.beok.webservice;

import beok.beok.POJO.CreatedObjects;
import beok.beok.POJO.DataTeste;
import beok.beok.POJO.Usuario;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PUT;
import retrofit2.http.Path;

/**
 * Created by pietro on 28/01/17.
 */

public interface ServiceWS {
    @GET("ws/main/user/{email}/{senha}")
    Call<Usuario> getUsuario(@Path("email") String email, @Path("senha") String password);

    @PUT("ws/main/user/cadastro")
    Call<Usuario> cadastrar(@Body Usuario body);

    @GET("ws/main/teste")
    Call<CreatedObjects> teste();

    @PUT("ws/main/data/create/{id}")
    Call<CreatedObjects> create(@Body Object body,@Path("id") long idUsuario);
}
