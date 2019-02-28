package top.holodtorg.controller;

import top.holodtorg.domain.Article;
import top.holodtorg.repos.ArticleRepo;
import top.holodtorg.repos.CategoryRepo;
import top.holodtorg.repos.ProductRepo;
import top.holodtorg.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import top.holodtorg.service.ProductServiceDelete;

import java.util.List;


@Controller
public class ArticleController {
    @Autowired
    private ArticleRepo articleRepo;

    @Autowired
    private ProductRepo productRepo;

    @Autowired
    private CategoryRepo categoryRepo;


    @GetMapping("/articles")
    public String main(@RequestParam(required = false, defaultValue = "", name = "category") String category,
                        @RequestParam(required = false, defaultValue = "", name = "filter") String filter,

                        Model model,
                        @PageableDefault(sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable) {

        Page<Article> page;

        if (filter != null && !filter.isEmpty()) {

            page = articleRepo.findByArticletitle(filter, pageable);
        } else {


            if (category != null && !category.isEmpty()) {

                page = articleRepo.findByArticlecategory(ProductService.getLong(category), pageable);
            } else {
                page = articleRepo.findAll(pageable);
            }


        }

        model.addAttribute("page", page);
        model.addAttribute("url", "/articles");
        model.addAttribute("filter", filter);
        model = new ProductService().ControllerAllPage (model,productRepo,categoryRepo);

        return "articles";
    }


		
    @GetMapping("/article")
    public String article(@RequestParam(required = false, defaultValue = "", name = "category") String category,
                          @RequestParam(required = false, defaultValue = "", name = "filter") String filter,
                          @RequestParam(required = false, defaultValue = "", name = "id") String id,

                        Model model,
                        @PageableDefault(sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable) {


        if (id != null && !id.isEmpty()) {
            List<Article> article;

            article = articleRepo.findById(ProductService.getInt(id));

            article = ProductServiceDelete.DeleteSimbolArticle(article);

            String Articletitle = "";
            String articleseodesc = "";
            String articlekeywords = "";
            Long ArticleCount = 0l;
            for (Article article1 : article) {
                Articletitle = article1.getArticletitle();
                articleseodesc  = article1.getArticleseodesc();
                articlekeywords = article1.getArticlekeywords();
                ArticleCount = article1.getArticlecount() + 1;

            }

            articleRepo.UpdateCount(ProductService.getInt(id),ArticleCount);

            model.addAttribute("article", article);
            model.addAttribute("метаtitle", Articletitle);
            model.addAttribute("метаdesc", articleseodesc);
            model.addAttribute("метаkey", articlekeywords);

        }

        model = new ProductService().ControllerAllPage (model,productRepo,categoryRepo);
        return "article";
    }


}
