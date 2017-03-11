package beok.beok.POJO;

import com.orm.dsl.Table;

import java.util.Date;

/**
 * Created by pietro on 11/03/17.
 */
@Table
public class TerapiaFeita {
        private Long id;
        private int numeroTerapia;
        private String resultado;
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

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }
}
