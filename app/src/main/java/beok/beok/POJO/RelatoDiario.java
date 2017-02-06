package beok.beok.POJO;

import com.orm.dsl.Table;
import java.util.Date;

/**
 *
 * @author pietro
 */

@Table
public class RelatoDiario {



    private Long id;

    private Date dataDiario;
    private boolean vontade;
    private boolean uso;


    /**
     * @return the id
     */
    public Long getId() {         return id;     }

    /**
     * @param id the id to set
     */
    public void setId(Long id) { this.id = id; }


    /**
     * @return the dataDiario
     */
    public Date getDataDiario() {
        return dataDiario;
    }

    /**
     * @param dataDiario the dataDiario to set
     */
    public void setDataDiario(Date dataDiario) {
        this.dataDiario = dataDiario;
    }

    /**
     * @return the vontade
     */
    public boolean getVontade() {
        return vontade;
    }

    /**
     * @param vontade the vontade to set
     */
    public void setVontade(boolean vontade) {
        this.vontade = vontade;
    }

    /**
     * @return the uso
     */
    public boolean getUso() {
        return uso;
    }

    /**
     * @param uso the uso to set
     */
    public void setUso(boolean uso) {
        this.uso = uso;
    }
}
