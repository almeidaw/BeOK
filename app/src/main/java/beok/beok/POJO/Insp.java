package beok.beok.POJO;

import com.orm.dsl.Table;

/**
 * Created by pietro on 25/02/17.
 */
@Table
public class Insp {
    private Long id;
    private String texto;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }
}
