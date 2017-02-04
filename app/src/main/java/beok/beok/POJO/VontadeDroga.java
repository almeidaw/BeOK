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
    private int idUsuario;
    private int idReelatoDiario;
    private int tipo;
    private String outros;
    private boolean manha;
    private boolean tarde;
    private boolean noite;
    private boolean madrugada;
    private int onde;
    private String ondeOutros;
    private int comQuem;
    private String comQuemOutros;
    private int quantoVontade;
    private int fezLugar;


    /**
     * @return the id
     */
    public Long getId() {         return id;     }

    /**
     * @param id the id to set
     */
    public void setId(Long id) { this.id = id; }

    /**
     * @return the idUsuario
     */
    public int getIdUsuario() {
        return idUsuario;
    }

    /**
     * @param idUsuario the idUsuario to set
     */
    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    /**
     * @return the idReelatoDiario
     */
    public int getIdRelatoDiario() {
        return idReelatoDiario;
    }

    /**
     * @param idReelatoDiario the idReelatoDiario to set
     */
    public void setidRelatoDiario(int idReelatoDiario) {
        this.idReelatoDiario = idReelatoDiario;
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
     * @return the outros
     */
    public String getOutros() {
        return outros;
    }

    /**
     * @param outros the outros to set
     */
    public void setOutros(String outros) {
        this.outros = outros;
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
     * @return the onde
     */
    public int getOnde() {
        return onde;
    }

    /**
     * @param onde the onde to set
     */
    public void setOnde(int onde) {
        this.onde = onde;
    }

    /**
     * @return the ondeOutros
     */
    public String getOndeOutros() {
        return ondeOutros;
    }

    /**
     * @param ondeOutros the ondeOutros to set
     */
    public void setOndeOutros(String ondeOutros) {
        this.ondeOutros = ondeOutros;
    }

    /**
     * @return the comQuem
     */
    public int getComQuem() {
        return comQuem;
    }

    /**
     * @param comQuem the comQuem to set
     */
    public void setComQuem(int comQuem) {
        this.comQuem = comQuem;
    }

    /**
     * @return the comQuemOutros
     */
    public String getComQuemOutros() {
        return comQuemOutros;
    }

    /**
     * @param comQuemOutros the comQuemOutros to set
     */
    public void setComQuemOutros(String comQuemOutros) {
        this.comQuemOutros = comQuemOutros;
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
}
