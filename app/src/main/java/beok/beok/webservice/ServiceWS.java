package beok.beok.webservice;

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
    Call<Object> cadastrar(@Body Usuario body);
}
