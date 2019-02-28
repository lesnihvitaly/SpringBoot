package top.holodtorg.service;


import top.holodtorg.domain.Category;
import top.holodtorg.domain.Products;
import top.holodtorg.repos.CategoryRepo;
import top.holodtorg.repos.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ProductService {

    @Autowired
    private ProductRepo productRepo;

    public static Long getLong (String productprice3) {


        Long productprice2;
        String productprice = productprice3.replaceAll("[\\s|\\u00A0]+", "");

        if (productprice != "") {
            productprice2 = Long.parseLong(productprice);
        } else productprice2 = new Long(0);
        return productprice2;
    }
    public static Double getDouble (String productprice3) {
        Double productprice2;

        String productprice = productprice3.replaceAll("[\\s|\\u00A0]+", "");

        if (productprice != "") {
            productprice2 = Double.parseDouble(productprice);
        } else productprice2 = new Double(0);

        return productprice2;
    }
    public static Integer getInteger (String productid) {
        Integer productprice2;
        if (productid != "") {
            productprice2 =  new Integer(productid);
        } else productprice2 = new Integer(0);
        return productprice2;
    }

    public static int getInt (String productid) {
        int productid2;
        if (productid != "") {
            productid2 =  Integer.parseInt(productid);
        } else productid2 = Integer.parseInt("0");
        return productid2;
    }

    public static Long getCheckbox(String productvisible) {
        Long productvisible2 = new Long(0);
        try {
            if (productvisible.equals("on")) productvisible2 = new Long(1);
        }
        catch (Exception e) {productvisible2 = new Long(0);}
        return productvisible2;
    }



    public static  Model ControllerAllPage(Model model, ProductRepo productRepo, CategoryRepo categoryRepo) {

        Iterable<Category> categoryLeft;
        categoryLeft = categoryRepo.GetByParentId(0l);// TOP



        List<Products> productrandomAll;
        productrandomAll = productRepo.findByProductrandom();

        List<Products> productrandom = new ArrayList<>();

        for (Products p : productrandomAll) {
            if (p.getFilename().length() == 0) { continue;}
            productrandom.add(p);

            if (productrandom.size() == 3) { break;}
        }


        ProductServiceDelete.DeleteSimbol(productrandom);

        model.addAttribute("productrandom", productrandom);
        model.addAttribute("categoryLeft", categoryLeft);

        return model;
    }

    public static String checkCookie(String Cookie, HttpServletResponse response) {


        int Year;
        Calendar calendar = Calendar.getInstance();
        Year = calendar.get(Calendar.YEAR);

        int numRand = (int) (Math.random()*11111111);
        if (Cookie.isEmpty()) {
            response.addCookie(new Cookie("m1", "Mnsh" + Year + numRand  ));
        }
        else return Cookie;


        return   "hello";
    }




   public static String  GetBrandTitleById (String brandId) {


       int i = ProductService.getInt(brandId);

       String Brandtitle = "";
       if (i == 1) {  Brandtitle = "Ариада"; }
       else if (i == 2) {  Brandtitle = "Марихолодмаш"; }
       else if (i == 3) {  Brandtitle = "Полюс"; }
       else if (i == 4) {  Brandtitle = "Премьер"; }
       else if (i == 5) {  Brandtitle = "Скандинавия"; }
       else if (i == 6) {  Brandtitle = "Paracels"; }
       else if (i == 7) {  Brandtitle = "Frostor"; }
       else if (i == 8) {  Brandtitle = "Снеж"; }
       else if (i == 9) {  Brandtitle = "Derby"; }
       else if (i == 10) {  Brandtitle = "Бирюса"; }
       else if (i == 12) {  Brandtitle = "Атеси"; }
       else if (i == 16) {  Brandtitle = "Чувашторгтехника"; }
       else if (i == 23) {  Brandtitle = "Polair"; }

        return Brandtitle;
   }


    public static String firstUpperCase(String word){
        if(word == null || word.isEmpty()) return null;// return word;
        return word.substring(0, 1).toUpperCase() + word.substring(1).toLowerCase();
    }


}
