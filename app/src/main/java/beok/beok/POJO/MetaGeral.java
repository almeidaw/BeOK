package beok.beok.POJO;

import com.orm.dsl.Table;
import java.util.Date;

/**
 *
 * @author pietro
 */

@Table
public class MetaGeral {



    private Long id;
    private int idUsuario;
    private int tipo;
    private String outros;
    private int freqSemanal;
    private boolean fimDeSem;
    private int freqDia;
    private boolean manha;
    private boolean tarde;
    private boolean noite;
    private boolean madrugada;
    private int quantidade;


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
     * @return the freqSemanal
     */
    public int getFreqSemanal() {
        return freqSemanal;
    }

    /**
     * @param freqSemanal the freqSemanal to set
     */
    public void setFreqSemanal(int freqSemanal) {
        this.freqSemanal = freqSemanal;
    }

    /**
     * @return the fimDeSem
     */
    public boolean getFimDeSem() {
        return fimDeSem;
    }

    /**
     * @param fimDeSem the fimDeSem to set
     */
    public void setFimDeSem(boolean fimDeSem) {
        this.fimDeSem = fimDeSem;
    }

    /**
     * @return the freqDia
     */
    public int getFreqDia() {
        return freqDia;
    }

    /**
     * @param freqDia the freqDia to set
     */
    public void setFreqDia(int freqDia) {
        this.freqDia = freqDia;
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
     * @return the quantidade
     */
    public int getQuantidade() {
        return quantidade;
    }

    /**
     * @param quantidade the quantidade to set
     */
    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
}
