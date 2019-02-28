package top.holodtorg.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class Category {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String categorytitle;
    private String categorydesc;
    private String categorymetadesc;
    private String categorymetakey;
    private Long categoryparent;

    public Category() {
    }

    public Category(String categorytitle) {
        this.categorytitle = categorytitle;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategorytitle() {
        return categorytitle;
    }

    public void setCategorytitle(String categorytitle) {
        this.categorytitle = categorytitle;
    }

    public String getCategorydesc() {
        return categorydesc;
    }

    public void setCategorydesc(String categorydesc) {
        this.categorydesc = categorydesc;
    }

    public String getCategorymetadesc() {
        return categorymetadesc;
    }

    public void setCategorymetadesc(String categorymetadesc) {
        this.categorymetadesc = categorymetadesc;
    }

    public String getCategorymetakey() {
        return categorymetakey;
    }

    public void setCategorymetakey(String categorymetakey) {
        this.categorymetakey = categorymetakey;
    }

    public Long getCategoryparent() {
        return categoryparent;
    }

    public void setCategoryparent(Long categoryparent) {
        this.categoryparent = categoryparent;
    }
}
