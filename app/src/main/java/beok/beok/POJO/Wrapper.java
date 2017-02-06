package beok.beok.POJO;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.orm.dsl.Table;

/**
 * Created by pietro on 04/02/17.
 */
@Table
public class Wrapper {
    private String type;
    private String content;
    private long idObject;
    private Long id;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

   public void setContent(Object content) {
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
        this.content=gson.toJson(content);
    }

    public void setContent(String content){
        this.content=content;
    }

    public long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public long getIdObject() {
        return idObject;
    }

    public void setIdObject(Long idObject) {
        this.idObject = idObject;
    }
}
