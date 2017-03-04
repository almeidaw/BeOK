package beok.beok.webservice;

import java.util.List;

import beok.beok.POJO.ContatoProf;
import beok.beok.POJO.CreatedObjects;
import beok.beok.POJO.DataTeste;
import beok.beok.POJO.GrupoA;
import beok.beok.POJO.InspServ;
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
    @GET("ws/ws/main/user/{email}/{senha}")
    Call<Usuario> getUsuario(@Path("email") String email, @Path("senha") String password);

    @PUT("ws/ws/main/user/cadastro")
    Call<Usuario> cadastrar(@Body Usuario body);

    @GET("ws/ws/main/teste")
    Call<CreatedObjects> teste();

    @PUT("ws/ws/main/data/create/{id}")
    Call<CreatedObjects> create(@Body Object body,@Path("id") long idUsuario);

    @PUT("ws/ws/main/data/inspiracao")
    Call<List<InspServ>> checkInsp(@Body Object body);

    @GET("ws/ws/main/data/contato")
    Call<List<ContatoProf>> getContato();

    @GET("ws/ws/main/data/gruposa/{tipo}/{zona}")
    Call<List<GrupoA>> getGrupos(@Path("tipo") String tipo, @Path("zona") String zona);
}
