package beok.beok.POJO;

import com.orm.dsl.Table;
import java.util.Date;

/**
 *
 * @author pietro
 */

@Table
public class Usuario {


    private Long id;
    private String nome;
    private int idade;
    private String genero;
    private String senha;
    private String email;
    private int motivacao;
    private String motivOutros;
    private Date notifDiario;
    private Date notifSem;
    private Date notifTerapia;

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
     * @return the idade
     */
    public int getIdade() {
        return idade;
    }

    /**
     * @param idade the idade to set
     */
    public void setIdade(int idade) {
        this.idade = idade;
    }

    /**
     * @return the genero
     */
    public String getGenero() {
        return genero;
    }

    /**
     * @param genero the genero to set
     */
    public void setGenero(String genero) {
        this.genero = genero;
    }

    /**
     * @return the senha
     */
    public String getSenha() {
        return senha;
    }

    /**
     * @param senha the senha to set
     */
    public void setSenha(String senha) {
        this.senha = senha;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the motivacao
     */
    public int getMotivacao() {
        return motivacao;
    }

    /**
     * @param motivacao the motivacao to set
     */
    public void setMotivacao(int motivacao) {
        this.motivacao = motivacao;
    }

    /**
     * @return the motivOutros
     */
    public String getMotivOutros() {
        return motivOutros;
    }

    /**
     * @param motivOutros the motivOutros to set
     */
    public void setMotivOutros(String motivOutros) {
        this.motivOutros = motivOutros;
    }

    /**
     * @return the notifDiario
     */
    public Date getNotifDiario() {
        return notifDiario;
    }

    /**
     * @param notifDiario the notifDiario to set
     */
    public void setNotifDiario(Date notifDiario) {
        this.notifDiario = notifDiario;
    }

    /**
     * @return the notifSem
     */
    public Date getNotifSem() {
        return notifSem;
    }

    /**
     * @param notifSem the notifSem to set
     */
    public void setNotifSem(Date notifSem) {
        this.notifSem = notifSem;
    }

    /**
     * @return the notifTerapia
     */
    public Date getNotifTerapia() {
        return notifTerapia;
    }

    /**
     * @param notifTerapia the notifTerapia to set
     */
    public void setNotifTerapia(Date notifTerapia) {
        this.notifTerapia = notifTerapia;
    }
}
