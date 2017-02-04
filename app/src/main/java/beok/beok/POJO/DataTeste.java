/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beok.beok.POJO;

import java.util.Date;
import com.orm.dsl.Table;

/**
 *
 * @author pietro
 */

@Table
public class DataTeste {
    

    private Long id;
    
    private String nameTeste;
    
    private boolean bTeste;

    private Date dataHora;

    public Long getId() {
       return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return nameTeste;
    }

    public void setName(String name) {
        this.nameTeste = name;
    }

    public Date getDate() {
        return dataHora;
    }

    public void setDate(Date date) {
        this.dataHora = date;
    }

    /**
     * @return the bTeste
     */
    public boolean isbTeste() {
        return bTeste;
    }

    /**
     * @param bTeste the bTeste to set
     */
    public void setbTeste(boolean bTeste) {
        this.bTeste = bTeste;
    }

}

