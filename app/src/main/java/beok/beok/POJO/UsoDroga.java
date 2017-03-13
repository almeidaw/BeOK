package beok.beok.POJO;

import com.orm.dsl.Table;
import java.util.Date;

/**
 *
 * @author pietro
 */

@Table
public class UsoDroga {



    private Long id;

    private int idRelatoDiario;
    private int tipo;
    private String outros;
    private Date quando;
    private int quantidade;
    private boolean manha;
    private boolean tarde;
    private boolean noite;
    private boolean madrugada;
    private int onde;
    private String ondeOutros;
    private int comQuem;
    private String comQuemOutros;
    private int motivo;
    private String outroMotivo;

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
     * @return the outros
     */
    public String getOutros() {
        return outros;
    }


    /**
     * @return the quando
     */
    public Date getQuando() {
        return quando;
    }

    /**
     * @param quando the quando to set
     */
    public void setQuando(Date quando) {
        this.quando = quando;
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
    public void setOutros(String outros){
        this.outros=outros;
        tipo=6;
    }

    public int getMotivo() {
        return motivo;
    }

    public void setMotivo(int motivo) {
        this.motivo = motivo;
    }

    public void setOutrosMotivos(String string){
        this.outroMotivo = string;
    }
}
