package top.holodtorg.domain;

import javax.persistence.*;

@Entity
public class Shopcart {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long shopcartproduct;
    private Double shopcartprice;
    private String shopcartuser;
    private String shopcartproducttitle;
    private String shopcartfilename;
    private Long shopcartprodcount;
    private Integer shopcartorder;



    public Shopcart() {
    }

    public Shopcart(Long shopcartproduct, String shopcartuser) {
        this.shopcartproduct = shopcartproduct;
        this.shopcartuser = shopcartuser;
    }

    public Shopcart(Long shopcartproduct, Double shopcartprice, String shopcartuser) {
        this.shopcartproduct = shopcartproduct;
        this.shopcartprice = shopcartprice;
        this.shopcartuser = shopcartuser;
    }

    public Shopcart(Long shopcartproduct,
                    Double shopcartprice,
                    String shopcartuser,
                    String shopcartproducttitle,
                    String shopcartfilename,
                    Long shopcartprodcount
    ) {
        this.shopcartproduct = shopcartproduct;
        this.shopcartprice = shopcartprice;
        this.shopcartuser = shopcartuser;
        this.shopcartproducttitle = shopcartproducttitle;
        this.shopcartfilename = shopcartfilename;
        this.shopcartprodcount = shopcartprodcount;
    }

    public Shopcart(Long shopcartproduct,
                    Double shopcartprice,
                    String shopcartuser,
                    String shopcartproducttitle,
                    String shopcartfilename,
                    Long shopcartprodcount,
                    Integer shopcartorder

    ) {
        this.shopcartproduct = shopcartproduct;
        this.shopcartprice = shopcartprice;
        this.shopcartuser = shopcartuser;
        this.shopcartproducttitle = shopcartproducttitle;
        this.shopcartfilename = shopcartfilename;
        this.shopcartprodcount = shopcartprodcount;
        this.shopcartorder = shopcartorder;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getShopcartproduct() {
        return shopcartproduct;
    }

    public void setShopcartproduct(Long shopcartproduct) {
        this.shopcartproduct = shopcartproduct;
    }

    public Double getShopcartprice() {
        return shopcartprice;
    }

    public void setShopcartprice(Double shopcartprice) {
        this.shopcartprice = shopcartprice;
    }

    public String getShopcartuser() {
        return shopcartuser;
    }

    public void setShopcartuser(String shopcartuser) {
        this.shopcartuser = shopcartuser;
    }

    public String getShopcartproducttitle() {
        return shopcartproducttitle;
    }

    public void setShopcartproducttitle(String shopcartproducttitle) {
        this.shopcartproducttitle = shopcartproducttitle;
    }

    public String getShopcartfilename() {
        return shopcartfilename;
    }

    public void setShopcartfilename(String shopcartfilename) {
        this.shopcartfilename = shopcartfilename;
    }

    public Long getShopcartprodcount() {
        return shopcartprodcount;
    }

    public void setShopcartprodcount(Long shopcartprodcount) {
        this.shopcartprodcount = shopcartprodcount;
    }

    public Integer getShopcartorder() {
        return shopcartorder;
    }

    public void setShopcartorder(Integer shopcartorder) {
        this.shopcartorder = shopcartorder;
    }
}

