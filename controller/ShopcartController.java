package top.holodtorg.controller;

import top.holodtorg.domain.Shopcart;
import top.holodtorg.repos.CategoryRepo;
import top.holodtorg.repos.ProductRepo;
import top.holodtorg.repos.ShopcartRepo;
import top.holodtorg.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.util.List;


@Controller
public class ShopcartController {
    @Autowired
    private ProductRepo productRepo;

    @Autowired
    private CategoryRepo categoryRepo;

    @Autowired
    private ShopcartRepo shopcartRepo;


    @GetMapping("/shopcartajax")
    public String shopcartajax(
            HttpServletResponse response,
            @CookieValue(value = "m1", defaultValue = "") String Cookie,
            Model model) {


        String checkCookie = ProductService.checkCookie(Cookie,response);
        if (checkCookie != "") {

            List<Shopcart> UniqProdUser = shopcartRepo.findByProdUser(Cookie);
            String shopcartajax = "";

            if (UniqProdUser.size() > 0) {

                shopcartajax = "<span style='font-size: medium'> В корзине " + UniqProdUser.size() + " тов. </span>";

            }

            model.addAttribute("shopcartajax", shopcartajax);
        }

        return "shopcartajax";
    }


    @GetMapping("/shopcart")
    public String shopcart1 (@RequestParam(required = false, defaultValue = "", name = "shopcartproduct") String shopcartproduct,
                             @RequestParam(required = false, defaultValue = "", name = "shopcartprice") String shopcartprice,
                             @RequestParam(required = false, defaultValue = "", name = "shopcartproducttitle") String shopcartproducttitle,
                             @RequestParam(required = false, defaultValue = "", name = "shopcartfilename") String shopcartfilename,
                             @RequestParam(required = false, defaultValue = "", name = "productdelete") String productdelete,
                             @RequestParam(required = false, defaultValue = "", name = "productminus") String productminus,
                             @RequestParam(required = false, defaultValue = "", name = "productplus") String productplus,
                             @RequestParam(required = false, defaultValue = "", name = "productorder") String productorder,

                             @CookieValue(value = "m1", defaultValue = "") String Cookie,
                             HttpServletResponse response,
                             Model model
    ){
        ProductService.checkCookie(Cookie,response);
        Iterable<Shopcart> shopcart;


        if  (!shopcartproduct.isEmpty()) {

            // Check for coincidence of goods
            List<Shopcart> UniqProdUser = shopcartRepo.findByUniqProdUser(ProductService.getLong(shopcartproduct),Cookie);
            System.out.println(UniqProdUser.size());

            // get Shopcartprodcount in shopping cart
            long ProdCount = 0 ;
            if (UniqProdUser.size() > 0) {
                for (Shopcart shopcartm : UniqProdUser) {
                    System.out.println(shopcartm.getShopcartprodcount());
                    ProdCount = shopcartm.getShopcartprodcount() + 1;
                }


                shopcartRepo.UpdateProdCount(
                        ProductService.getLong(shopcartproduct),
                        Cookie,
                        ProdCount);

            }
            else {


                Shopcart AddShopCart = new Shopcart(
                        ProductService.getLong(shopcartproduct),
                        ProductService.getDouble(shopcartprice),
                        ProductService.checkCookie(Cookie, response),
                        shopcartproducttitle,
                        shopcartfilename,
                        1l,
                        0
                );


                shopcartRepo.save(AddShopCart);
            }
        }
        int order1 = 0;

        if  (!Cookie.isEmpty()) {

            shopcart = shopcartRepo.findByShopcartuser2(Cookie);
            Double GeneralPrice = 0.00;

            for (Shopcart shopcart1 : shopcart) {
                GeneralPrice = GeneralPrice + shopcart1.getShopcartprice()*shopcart1.getShopcartprodcount();
                order1 = shopcart1.getShopcartorder();
            }

            shopcart = shopcartRepo.findByShopcartuser2(Cookie);
            model.addAttribute("shopcart",shopcart);

            model.addAttribute("order",order1);

            model.addAttribute("GeneralPrice",GeneralPrice);


        }



        if (!productdelete.isEmpty()) {

            List<Shopcart> UniqProdUser = shopcartRepo.findByUniqProdUser(ProductService.getLong(productdelete),Cookie);


            if (UniqProdUser.size() > 0) {

                shopcartRepo.DeleteProdCart(ProductService.getLong(productdelete));

            }

            shopcart = shopcartRepo.findByShopcartuser2(Cookie);
            model.addAttribute("shopcart",shopcart);

        }
        else if (!productminus.isEmpty()) {

            // Check for coincidence of goods
            List<Shopcart> UniqProdUser = shopcartRepo.findByUniqProdUser(ProductService.getLong(productminus),Cookie);

            // get Shopcartprodcount in shopping cart
            long ProdCount = 0 ;
            if (UniqProdUser.size() > 0) {
                for (Shopcart shopcartm : UniqProdUser) {

                    ProdCount = shopcartm.getShopcartprodcount() - 1;
                }
                if (ProdCount > 0) {
                    shopcartRepo.UpdateProdCount(
                            ProductService.getLong(productminus),
                            Cookie,
                            ProdCount);
                }
                else shopcartRepo.DeleteProdCart(ProductService.getLong(productminus));
            }

            shopcart = shopcartRepo.findByShopcartuser2(Cookie);
            model.addAttribute("shopcart",shopcart);

        }


        else if (!productplus.isEmpty()) {

            // Check for coincidence of goods
            List<Shopcart> UniqProdUser = shopcartRepo.findByUniqProdUser(ProductService.getLong(productplus),Cookie);

            // get Shopcartprodcount in shopping cart
            long ProdCount = 0 ;
            if (UniqProdUser.size() > 0) {
                for (Shopcart shopcartm : UniqProdUser) {

                    ProdCount = shopcartm.getShopcartprodcount() + 1;
                }
                if (ProdCount > 0) {
                    shopcartRepo.UpdateProdCount(
                            ProductService.getLong(productplus),
                            Cookie,
                            ProdCount);
                }

            }

            shopcart = shopcartRepo.findByShopcartuser2(Cookie);
            model.addAttribute("shopcart",shopcart);

        }



        if (!productorder.isEmpty()) {

            if (ProductService.getInt(productorder) == 1) {
                // Y

                Iterable<Shopcart> productorder1 = shopcartRepo.findByShopcartuser(Cookie);
                for (Shopcart s : productorder1) {
                    shopcartRepo.UpdateShopcartOrder(s.getId(),1);
                }
            }
            else if (ProductService.getInt(productorder) == 2) {
                // Y

                Iterable<Shopcart> productorder1 = shopcartRepo.findByShopcartuser(Cookie);
                for (Shopcart s : productorder1) {
                    shopcartRepo.UpdateShopcartOrder(s.getId(),2);
                }
            }

        }

        model = new ProductService().ControllerAllPage (model,productRepo,categoryRepo);


        return "shopcart";
    }





}
