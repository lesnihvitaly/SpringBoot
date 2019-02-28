package top.holodtorg.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Article {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String articletitle;
    private String articletdesc;
    private String articleminidesc;

    private String articlekeywords;
    private String articleseodesc;

    private Long articlecategory;
    private Long articlevisible;
    private Long articlecount;
    private Long articlelike;
    private String filename;

    public Article() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getArticletitle() {
        return articletitle;
    }

    public void setArticletitle(String articletitle) {
        this.articletitle = articletitle;
    }

    public String getArticletdesc() {
        return articletdesc;
    }

    public void setArticletdesc(String articletdesc) {
        this.articletdesc = articletdesc;
    }

    public String getArticleminidesc() {
        return articleminidesc;
    }

    public void setArticleminidesc(String articleminidesc) {
        this.articleminidesc = articleminidesc;
    }

    public String getArticlekeywords() {
        return articlekeywords;
    }

    public void setArticlekeywords(String articlekeywords) {
        this.articlekeywords = articlekeywords;
    }

    public String getArticleseodesc() {
        return articleseodesc;
    }

    public void setArticleseodesc(String articleseodesc) {
        this.articleseodesc = articleseodesc;
    }

    public Long getArticlecategory() {
        return articlecategory;
    }

    public void setArticlecategory(Long articlecategory) {
        this.articlecategory = articlecategory;
    }

    public Long getArticlevisible() {
        return articlevisible;
    }

    public void setArticlevisible(Long articlevisible) {
        this.articlevisible = articlevisible;
    }

    public Long getArticlecount() {
        return articlecount;
    }

    public void setArticlecount(Long articlecount) {
        this.articlecount = articlecount;
    }

    public Long getArticlelike() {
        return articlelike;
    }

    public void setArticlelike(Long articlelike) {
        this.articlelike = articlelike;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }
}

