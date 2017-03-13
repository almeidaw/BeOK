package beok.beok.POJO;

import com.orm.dsl.Table;

/**
 * Created by pietro on 11/03/17.
 */
@Table
public class TerapiaAssistida {
    private Long id;
    private int numeroTerapia;
    private int idUsuario;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getNumeroTerapia() {
        return numeroTerapia;
    }

    public void setNumeroTerapia(int numeroTerapia) {
        this.numeroTerapia = numeroTerapia;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }
}
