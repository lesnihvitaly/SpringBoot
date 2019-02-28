package top.holodtorg.repos;


import top.holodtorg.domain.Shopcart;
//import com.example.sweater.domain.dto.ShopcartDto;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface ShopcartRepo extends CrudRepository<Shopcart, Integer> {

    List<Shopcart> findByShopcartuser(String shopcartuser);

    //check from user product in shopcart
    @Query(value = "SELECT * FROM Shopcart u WHERE u.shopcartuser = :shopcartuser ORDER by u.shopcartproduct",
            nativeQuery = true)
    List<Shopcart> findByShopcartuser2(@Param("shopcartuser") String shopcartuser);


    List<Shopcart> findAll();

    //check from user product in shopcart
    @Query(value = "SELECT * FROM Shopcart u WHERE u.shopcartuser = :shopcartuser",
            nativeQuery = true)
    List<Shopcart> findByProdUser(@Param("shopcartuser") String shopcartuser);

//select data for user
    @Query(value = "SELECT * FROM Shopcart u WHERE u.shopcartproduct = :shopcartproduct and u.shopcartuser = :shopcartuser",
            nativeQuery = true)
    List<Shopcart> findByUniqProdUser(@Param("shopcartproduct") long shopcartproduct,
                                      @Param("shopcartuser") String shopcartuser);



// update count of product in shopcart
    @Modifying
    @Transactional
    @Query("UPDATE Shopcart u SET u.shopcartprodcount = :shopcartprodcount " +


            "WHERE u.shopcartproduct = :shopcartproduct and u.shopcartuser = :shopcartuser")
    void UpdateProdCount(@Param("shopcartproduct") Long shopcartproduct,
                       @Param("shopcartuser") String shopcartuser,
                       @Param("shopcartprodcount") Long shopcartprodcount
    );

// delete product in shopcart
    @Modifying
    @Transactional
    @Query("delete from Shopcart  u WHERE u.shopcartproduct = :shopcartproduct")
    void DeleteProdCart( @Param("shopcartproduct") Long shopcartproduct
    );

    // Update data for new order
    @Modifying
    @Transactional
    @Query("UPDATE Shopcart u SET u.shopcartorder = :shopcartorder " +


            "WHERE u.id = :id")
    void UpdateShopcartOrder(@Param("id") Long id,
                         @Param("shopcartorder") int shopcartorder
    );






}