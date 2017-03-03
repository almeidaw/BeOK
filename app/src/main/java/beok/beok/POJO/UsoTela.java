package beok.beok.POJO;

import com.orm.dsl.Table;

import java.util.Date;

/**
 * Created by pietro on 01/03/17.
 */
@Table
public class UsoTela {
    private Long id;
    private Date lastClicked;
    private int tela;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getLastClicked() {
        return lastClicked;
    }

    public void setLastClicked(Date lastClicked) {
        this.lastClicked = lastClicked;
    }

    public int getTela() {
        return tela;
    }

    public void setTela(int tela) {
        this.tela = tela;
    }
}
