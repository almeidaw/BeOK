package beok.beok.POJO;

import com.orm.dsl.Table;
import java.util.Date;

/**
 *
 * @author pietro
 */

@Table
public class MetaSemanal {



    private Long id;

    private Date semana;
    private int tipo;
    private int freqSemanal;
    private boolean manha;
    private boolean tarde;
    private boolean noite;
    private boolean madrugada;
    private int quantidade;
    private int tamMedBaseado; // "tamMedBaseado" = 0 caso "tipo" não for maconha


    /**
     * @return the id
     */
    public Long getId() {         return id;     }

    /**
     * @param id the id to set
     */
    public void setId(Long id) { this.id = id; }


    /**
     * @return the semana
     */
    public Date getSemana() {
        return semana;
    }

    /**
     * @param semana the semana to set
     */
    public void setSemana(Date semana) {
        this.semana = semana;
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
    public float getQuantidade() {
        if(tipo==3 || tipo==4){
            return ((float)(quantidade))/2;
        }else {
            return quantidade;
        }
    }

    /**
     * @param quantidade the quantidade to set
     */
    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    /**
     * @return the tamMedBaseado
     */
    public int getTamMedBaseado() {
        return tamMedBaseado;
    }

    /**
     * @param tamMedBaseado the tamMedBaseado to set
     */
    public void setTamMedBaseado(int tamMedBaseado) {
        this.tamMedBaseado = tamMedBaseado;
    }

}
