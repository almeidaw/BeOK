package beok.beok.POJO;

import com.orm.dsl.Table;
import java.util.Date;

/**
 *
 * @author pietro
 */

@Table
public class ConsumoAtual {



    private Long id;

    private int tipo;
    private int freqSemanal;
    private int quantidade;
    private int gasto;
    private int tamMedBaseado; // "tamMedBaseado" = 0 caso "tipo" não for maconha
    private Date dataInicio;


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
     * @return the quantidade
     */
    public float getQuantidade() {
        if(quantidade == 3 || quantidade==4){
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
     * @return the gasto
     */
    public int getGasto() {
        return gasto;
    }

    /**
     * @param gasto the gasto to set
     */
    public void setGasto(int gasto) {
        this.gasto = gasto;
    }

    public void setCerveja(){
        tipo=0;
    }
    public void setVinho() {tipo=1;}
    public void setDestilado (){tipo=2;}
    public void setMaconha(){
        tipo=3;
    }
    public void setCocaina(){
        tipo=4;
    }
    public void setCrack(){
        tipo=5;
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

    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }
}
