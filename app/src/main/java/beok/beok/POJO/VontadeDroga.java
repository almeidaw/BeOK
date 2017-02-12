package beok.beok.POJO;

import com.orm.dsl.Table;
import java.util.Date;

/**
 *
 * @author pietro
 */

@Table
public class VontadeDroga {



    private Long id;

    private int idRelatoDiario;
    private int tipo;
    private boolean manha;
    private boolean tarde;
    private boolean noite;
    private boolean madrugada;
    private int quantoVontade;
    private int fezLugar;
    private String fezLugarOutros;
    private int motivo;
    private String motivoOutros;



    /**
     * @return the id
     */
    public Long getId() {         return id;     }

    /**
     * @param id the id to set
     */
    public void setId(Long id) { this.id = id; }


    /**
     * @return the idRelatoDiario
     */
    public int getIdRelatoDiario() {
        return idRelatoDiario;
    }

    /**
     * @param idRelatoDiario the idRelatoDiario to set
     */
    public void setidRelatoDiario(int idRelatoDiario) {
        this.idRelatoDiario = idRelatoDiario;
    }

    /**
     * @return the tipo
     */
    public int getTipo() {
        return tipo;
    }

    /**
     * @param tipo the tipo to set
     */
    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    /**
     * @return the manha
     */
    public boolean getManha() {
        return manha;
    }

    /**
     * @param manha the manha to set
     */
    public void setManha(boolean manha) {
        this.manha = manha;
    }

    /**
     * @return the tarde
     */
    public boolean getTarde() {
        return tarde;
    }

    /**
     * @param tarde the tarde to set
     */
    public void setTarde(boolean tarde) {
        this.tarde = tarde;
    }

    /**
     * @return the noite
     */
    public boolean getNoite() {
        return noite;
    }

    /**
     * @param noite the noite to set
     */
    public void setNoite(boolean noite) {
        this.noite = noite;
    }

    /**
     * @return the madrugada
     */
    public boolean getMadrugada() {
        return madrugada;
    }

    /**
     * @param madrugada the madrugada to set
     */
    public void setMadrugada(boolean madrugada) {
        this.madrugada = madrugada;
    }


    /**
     * @return the quantoVontade
     */
    public int getQuantoVontade() {
        return quantoVontade;
    }

    /**
     * @param quantoVontade the quantoVontade to set
     */
    public void setQuantoVontade(int quantoVontade) {
        this.quantoVontade = quantoVontade;
    }

    /**
     * @return the fezLugar
     */
    public int getFezLugar() {
        return fezLugar;
    }

    /**
     * @param fezLugar the fezLugar to set
     */
    public void setFezLugar(int fezLugar) {
        this.fezLugar = fezLugar;
    }

    public String getFezLugarOutros() {
        return fezLugarOutros;
    }

    public void setFezLugarOutros(String fezLugarOutros) {
        this.fezLugarOutros = fezLugarOutros;
    }

    public int getMotivo() {
        return motivo;
    }

    public void setMotivo(int motivo) {
        this.motivo = motivo;
    }

    public String getMotivoOutros() {
        return motivoOutros;
    }

    public void setMotivoOutros(String motivoOutros) {
        this.motivoOutros = motivoOutros;
    }
}
