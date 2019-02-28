package top.holodtorg.repos;


import top.holodtorg.domain.Products;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface ProductRepo extends CrudRepository<Products, Integer> {

   Page<Products> findAll (Pageable pageable);

   Page<Products> findByProducttitle(String producttitle, Pageable pageable);

   Page<Products> findByProductcategory(long productcategory, Pageable pageable);

   List<Products> findById(long id2);

    List<Products> findByProductcategory(long id2);



    @Query(value = "SELECT * FROM Products u WHERE u.producttitle LIKE CONCAT('%',:producttitle,'%') OR " +
           "u.productdesc LIKE CONCAT('%',:producttitle,'%')",
            nativeQuery = true)
   Page<Products> findByProducttitle2(@Param("producttitle") String producttitle,Pageable pageable);



    @Query(value = "SELECT * FROM Products u" +
            " WHERE u.producttitle LIKE CONCAT('%',:producttitle,'%') and u.productprice >= :productprice1 and u.productprice < :productprice2 OR"+
            " u.productdesc LIKE CONCAT('%',:producttitle,'%') and u.productprice >= :param1 and u.productprice < :param2",
            nativeQuery = true)
    List<Products> FilterBrandLike(
            @Param("productprice1") Double productprice1,
            @Param("productprice2") Double productprice2,
            @Param("producttitle") String producttitle);


    @Query("FROM Products u order by function('RAND')")
    List<Products> findByProductrandom();


    @Query("from Products u WHERE u.productprice > :productprice1 AND u.productprice < :productprice2")
    Page<Products> FilterProduct(@Param("productprice1") Double productprice1,
                       @Param("productprice2") Double productprice2,
                                 Pageable pageable);

    @Query("from Products u WHERE u.productprice > :productprice1 AND u.productprice < :productprice2 AND u.productbrand = :productbrand")
    List<Products> FilterProduct(@Param("productprice1") Double productprice1,
                                 @Param("productprice2") Double productprice2,
                                 @Param("productbrand") Long productbrand

    );





    @Modifying
    @Transactional
    @Query("UPDATE Products u SET u.producttitle = :producttitle," +
            "u.productdesc = :productdesc, " +
            "u.productminidesc = :productminidesc, " +
            "u.productkeywords = :productkeywords, " +
            "u.productseodesc = :productseodesc, " +
            "u.productsku = :productsku, " +
            "u.productvisible = :productvisible, " +
            "u.productbrand = :productbrand, " +
            "u.productprice = :productprice, " +
            "u.productcategory = :productcategory " +
            "WHERE u.id = :id")
    void UpdateProduct(@Param("id")Long id,
                       @Param("producttitle") String producttitle,
                       @Param("productdesc") String productdesc,
                       @Param("productminidesc") String productminidesc,
                       @Param("productkeywords") String productkeywords,
                       @Param("productseodesc") String productseodesc,
                       @Param("productsku") String productsku,
                       @Param("productvisible") Long productvisible,
                       @Param("productbrand") Long productbrand,
                       @Param("productprice") Double productprice,
                       @Param("productcategory") Long productcategory
    );



    @Modifying
    @Transactional
    @Query("delete from Products u WHERE u.id = :id")
    void DeleteProd( @Param("id") Long id);


}