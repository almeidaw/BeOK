package beok.beok.POJO;

import com.orm.dsl.Table;
import java.util.Date;

/**
 *
 * @author pietro
 */

@Table
public class BotaoAtivo {



    private Long id;
    private Date dataAtivo;
    private boolean motivo;
    private int oQueFez;


    /**
     * @return the id
     */
    public Long getId() {         return id;     }

    /**
     * @param id the id to set
     */
    public void setId(Long id) { this.id = id; }

    /**
     * @return the dataAtivo
     */
    public Date getDataAtivo() {
        return dataAtivo;
    }

    /**
     * @param dataAtivo the dataAtivo to set
     */
    public void setDataAtivo(Date dataAtivo) {
        this.dataAtivo = dataAtivo;
    }

    /**
     * @return the motivo
     */
    public boolean getMotivo() {
        return motivo;
    }

    /**
     * @param motivo the motivo to set
     */
    public void setMotivo(boolean motivo) {
        this.motivo = motivo;
    }

    /**
     * @return the oQueFez
     */
    public int getOQueFez() {
        return oQueFez;
    }

    /**
     * @param oQueFez the oQueFez to set
     */
    public void setOQueFez(int oQueFez) {
        this.oQueFez = oQueFez;
    }
}
