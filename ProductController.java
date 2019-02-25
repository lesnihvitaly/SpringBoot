package top.holodtorg.controller;

import top.holodtorg.config.GlobalSettings;
import top.holodtorg.domain.Category;
import top.holodtorg.domain.Products;
import top.holodtorg.repos.CategoryRepo;
import top.holodtorg.repos.ProductRepo;
import top.holodtorg.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

@Controller
public class ProductControllerMail {
    @Autowired
    private ProductRepo productRepo;

    @Autowired
    private CategoryRepo categoryRepo;

    @Value("${upload.path}")
    private String uploadPath;

/////////////////////////////////////////// Map products  ///////////////////////////////////////////

    @GetMapping("/products")
    public String ProductsSite(@RequestParam(required = false, defaultValue = "",name = "category") String category,
                        @RequestParam(required = false, defaultValue = "",name = "search") String search,
                        @RequestParam(required = false, defaultValue = "",name = "start_price") String start_price,
                        @RequestParam(required = false, defaultValue = "",name = "end_price") String end_price,
                        @RequestParam(required = false, defaultValue = "",name = "brand") String[] brand,
                        HttpServletResponse response,
                        @CookieValue(value = "m1", defaultValue = "") String Cookie,
                        Model model,
                        @PageableDefault(sort = {"id"},direction = Sort.Direction.DESC) Pageable pageable) {
        ProductService.checkCookie(Cookie,response);

        Page<Products> page;

        String metatitle = "";
        String seodesc = "";
        String keywords = "";

        ////  Check parameter GET request
        if (search != null && !search.isEmpty()) {
            page = productRepo.findByProducttitle2(search.trim(),pageable);
        }

        else if (start_price != null && !start_price.isEmpty()  && brand.length == 0) {

                page = productRepo.FilterProduct(
                        ProductService.getDouble(start_price),
                        ProductService.getDouble(end_price),
                        pageable
                );

        }
        else {
            if (category != null && !category.isEmpty()) {
                page = productRepo.findByProductcategory   (ProductService.getLong(category),pageable);
            } else {
                page = productRepo.findAll(pageable);
            }
        }

        ///////////// Product output - search by parameters: Price,Brands ///////////////////
        List<Products> FilterBrand = new ArrayList<>();
        Boolean FilterBrandPrint = null;

          if (brand != null && brand.length >0) {
              FilterBrandPrint = true;
              page = productRepo.findByProducttitle("",pageable);

            for (String brandId: brand) // Get all brands
                {
                    List<Products> BrandForEach = productRepo.FilterBrandLike(
                            ProductService.getDouble(start_price),
                            ProductService.getDouble(end_price),
                            ProductService.GetBrandTitleById(brandId));

                            FilterBrand.addAll(BrandForEach); // Connect multiple collections into one by brand
            }

        }

        /// Print Category and Removing elements from an array in which there are no products and categories

        List<Category> categoryRight;
        if (!category.isEmpty()) {
            categoryRight = categoryRepo.GetByParentId(ProductService.getLong(category));

            Iterator<Category> catIterator = categoryRight.iterator();
            while(catIterator.hasNext()) {

                Category nextCat = catIterator.next();
                List<Category> categoryRight2 = categoryRepo.GetByParentId((long)nextCat.getId());
                List<Products> NoProductsNoCategory = productRepo.findByProductcategory(nextCat.getId());
                if (categoryRight2.size() == 0 && NoProductsNoCategory.size() == 0) {
                    catIterator.remove(); // Remove categories in which there are no products.
                }
            }
        }
        else {
            categoryRight = categoryRepo.GetByParentId(0l); // Displays top level categories
        }


        page =  ProductService.DeleteSimbolProducts(page);


        /////////////// Get meta data from id category ////////////////
        if (!category.isEmpty()){

            List<Category> CategoryMeta = categoryRepo.findById(ProductService.getInt(category));
            for (Category cat : CategoryMeta) {
                metatitle = cat.getCategorytitle();
                seodesc  = cat.getCategorymetadesc();
                keywords = cat.getCategorymetakey();
            }
        }


        ////// Change title - first capital letter, other small letters ///
        metatitle = ProductService.firstUpperCase(metatitle);

        //// Search by parameters (Get request)  /////
        if (!start_price.isEmpty()) {
            model.addAttribute("start_price", start_price);
        }
        if (!end_price.isEmpty()) {
            model.addAttribute("end_price", end_price);
        }
        ///////////////////////////////////////////////

        model.addAttribute("categoryRight", categoryRight); /// Display categories on the right side of the screen
        model.addAttribute("метаtitle", metatitle);
        model.addAttribute("метаdesc", seodesc);
        model.addAttribute("метаkey", keywords);
        model.addAttribute("page", page);
        model.addAttribute("FilterBrandPrint", FilterBrandPrint);
        model.addAttribute("FilterBrand", FilterBrand);
        model.addAttribute("brand", brand);
        model.addAttribute("url", "/products");
        model.addAttribute("search", search);
        model.addAttribute("GlobalParametr", GlobalSettings.GetGlobalSettings());

        /////////// Add common modules //////////////
        model = new ProductService().ControllerAllPage (model,productRepo,categoryRepo);

        return "products";
    }


/////////////////////////////////////////// Map product  ///////////////////////////////////////////


    @GetMapping("/product")
    public String product(
            @RequestParam(required = false, defaultValue = "",name = "id") String ProductId,
            Model model) {
        Iterable<Products> ProductOnSite;

        ProductOnSite = productRepo.findById(ProductService.getLong(ProductId));

        ProductOnSite = ProductService.DeleteSimbol(ProductOnSite);

        /////////// Get title Category //////////////////////
        String TitleCategory = "";
        for (Products productsItem : ProductOnSite) {
                        long CategoryId = productsItem.getProductcategory();
                        List<Category> NameCategory = categoryRepo.findById((int)CategoryId);
                        for (Category cat : NameCategory) {
                            TitleCategory = cat.getCategorytitle();
                        }
                    }


        String metatitle = "";
        String seodesc = "";
        String keywords = "";
        for (Products prod : ProductOnSite) {
            metatitle = prod.getProducttitle();
            seodesc  = prod.getProductseodesc();
            keywords = prod.getProductkeywords();
        }

        model.addAttribute("метаtitle", metatitle);
        model.addAttribute("метаdesc", seodesc);
        model.addAttribute("метаkey", keywords);
        model.addAttribute("TitleCategory", TitleCategory);
        model.addAttribute("ProductOnSite", ProductOnSite);
        model.addAttribute("GlobalParametr", GlobalSettings.GetGlobalSettings());

        /////////// Add common modules //////////////
        model = new ProductService().ControllerAllPage (model,productRepo,categoryRepo);

        return "product";
    }

}