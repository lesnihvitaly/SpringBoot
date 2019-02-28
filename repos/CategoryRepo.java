package top.holodtorg.repos;


import top.holodtorg.domain.Category;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;


public interface CategoryRepo extends CrudRepository<Category, Integer> {

    @Query("from Category c where c.categoryparent = :param1 ORDER BY c.id")
    List<Category> GetByParentId (@Param("param1") Long param1);

    @Modifying
    @Transactional
    @Query("UPDATE Category u SET u.categorytitle = :qwer2 " +
            "WHERE u.id = :qwer1")
    void UpdateCategory(@Param("qwer1")int qwer1,
                        @Param("qwer2") String qwer2);

    List<Category> findById(int id2);

    @Modifying
    @Transactional
    @Query("delete from Category  u WHERE u.id = :param1")
    void DeleteCat( @Param("param1") int param1);



}