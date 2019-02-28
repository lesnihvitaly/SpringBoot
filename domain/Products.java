package top.holodtorg.domain;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Products {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String producttitle;

    private String productdesc;
    private String productminidesc;

    private String productkeywords;
    private String productseodesc;
    private String productsku;

    private Long productcategory;
    private Long productvisible;
    private Long productcount;
    private Long productbrand;
    private Long productlike;

    private Double productprice;

    private String filename;

    private String productbrand2;

    public Products() {
    }

    public Products(String producttitle) {
        this.producttitle = producttitle;
    }

    public Products(String producttitle,
                    String productdesc,
                    String productminidesc,
                    String productkeywords,
                    String productseodesc,
                    String productsku,
                    Long productvisible,
                    Long productbrand,
                    Double productprice)
    {
        this.producttitle = producttitle;
        this.productdesc = productdesc;
        this.productminidesc = productminidesc;
        this.productkeywords = productkeywords;
        this.productseodesc = productseodesc;
        this.productsku = productsku;
        this.productvisible = productvisible;
        this.productbrand = productbrand;
        this.productprice = productprice;

    }

    public Long getProductcategory() {
        return productcategory;
    }

    public void setProductcategory(Long productcategory) {
        this.productcategory = productcategory;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProducttitle() {
        return producttitle;
    }

    public void setProducttitle(String producttitle) {
        this.producttitle = producttitle;
    }

    public String getProductdesc() {
        return productdesc;
    }

    public void setProductdesc(String productdesc) {
        this.productdesc = productdesc;
    }

    public String getProductminidesc() {
        return productminidesc;
    }

    public void setProductminidesc(String productminidesc) {
        this.productminidesc = productminidesc;
    }

    public String getProductkeywords() {
        return productkeywords;
    }

    public void setProductkeywords(String productkeywords) {
        this.productkeywords = productkeywords;
    }

    public String getProductseodesc() {
        return productseodesc;
    }

    public void setProductseodesc(String productseodesc) {
        this.productseodesc = productseodesc;
    }

    public String getProductsku() {
        return productsku;
    }

    public void setProductsku(String productsku) {
        this.productsku = productsku;
    }

    public Long getProductvisible() {
        return productvisible;
    }

    public void setProductvisible(Long productvisible) {
        this.productvisible = productvisible;
    }

    public Long getProductcount() {
        return productcount;
    }

    public void setProductcount(Long productcount) {
        this.productcount = productcount;
    }

    public Long getProductbrand() {
        return productbrand;
    }

    public void setProductbrand(Long productbrand) {
        this.productbrand = productbrand;
    }

    public Long getProductlike() {
        return productlike;
    }

    public void setProductlike(Long productlike) {
        this.productlike = productlike;
    }

    public Double getProductprice() {
        return productprice;
    }

    public void setProductprice(Double productprice) {
        this.productprice = productprice;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getProductbrand2() {
        return productbrand2;
    }

    public void setProductbrand2(String productbrand2) {
        this.productbrand2 = productbrand2;
    }
}

