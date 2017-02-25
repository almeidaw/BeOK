package beok.beok.POJO;

import com.orm.dsl.Table;
import java.util.Date;

/**
 *
 * @author pietro
 */

@Table
public class MetaGeral {

    //Tipo 0=cerveja, 1=vinho, 2=destilado, 3=maconha, 4=cocaina 5=crack
    //FreqSemanal 0=abstinencia 1=1 dia por semana, 2=2 dias por semana 3=3 a 5 dias por semana 4=todos os dias 5=finais de semana
    //

    private Long id;

    private int tipo;
    private int freqSemanal;
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
        }else{

            return quantidade;
        }
    }

    /**
     * @param quantidade the quantidade to set
     */
    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
}
