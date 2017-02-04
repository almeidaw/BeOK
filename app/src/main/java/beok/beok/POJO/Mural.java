package beok.beok.POJO;

import com.orm.dsl.Table;
import java.util.Date;

/**
 *
 * @author pietro
 */

@Table
public class Mural {



    private Long id;
    private int idUsuario;
    private Date dataMural;
    private String texto;
    private String imagem;
    private String video;
    private boolean publico;
    private int idReelatoDiario;


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
     * @return the dataMural
     */
    public Date getDataMural() {
        return dataMural;
    }

    /**
     * @param dataMural the dataMural to set
     */
    public void setDataMural(Date dataMural) {
        this.dataMural = dataMural;
    }

    /**
     * @return the texto
     */
    public String getTexto() {
        return texto;
    }

    /**
     * @param texto the texto to set
     */
    public void setTexto(String texto) {
        this.texto = texto;
    }

    /**
     * @return the imagem
     */
    public String getImagem() {
        return imagem;
    }

    /**
     * @param imagem the imagem to set
     */
    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    /**
     * @return the video
     */
    public String getVideo() {
        return video;
    }

    /**
     * @param video the video to set
     */
    public void setVideo(String video) {
        this.video = video;
    }

    /**
     * @return the publico
     */
    public boolean getPublico() {
        return publico;
    }

    /**
     * @param publico the publico to set
     */
    public void setPublico(boolean publico) {
        this.publico = publico;
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
}
