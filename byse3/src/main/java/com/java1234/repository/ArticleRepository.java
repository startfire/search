package com.java1234.repository;

import com.java1234.entity.Article;
import com.java1234.entity.ArticleWithBLOBs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


public interface ArticleRepository extends JpaRepository<Article,Integer>, JpaSpecificationExecutor<Article> {
    @Query("select st from Article st where st.upload=?1")
    public List<Article> findByType(int type);


    @Modifying
    @Transactional
    @Query("update Article st SET  st.upload= :type where st.id= :id")
    public void updateStatus(@Param("id") int id, @Param("type") int type);
}
