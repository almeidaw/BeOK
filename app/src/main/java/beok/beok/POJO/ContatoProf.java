package beok.beok.POJO;

import com.orm.dsl.Table;
import java.util.Date;

/**
 *
 * @author pietro
 */

@Table
public class ContatoProf {



    private Long id;
    private String nome;
    private String telefone;
    private String descricao;


    /**
     * @return the id
     */
    public Long getId() {         return id;     }

    /**
     * @param id the id to set
     */
    public void setId(Long id) { this.id = id; }

    /**
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @return the telefone
     */
    public String getTelefone() {
        return telefone;
    }

    /**
     * @param telefone the telefone to set
     */
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    /**
     * @return the descricao
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * @param descricao the descricao to set
     */
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
