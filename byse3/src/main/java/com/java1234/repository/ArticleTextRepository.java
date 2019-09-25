package com.java1234.repository;


import com.java1234.entity.ArticleWithBLOBs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 文章Repository接口
 * @author fangjian
 */
public interface ArticleTextRepository extends JpaRepository<ArticleWithBLOBs,Integer>, JpaSpecificationExecutor<ArticleWithBLOBs> {

    @Query("select st from ArticleWithBLOBs st where st.uploadtype=?1")
    public List<ArticleWithBLOBs> findByType(int type);


    @Modifying
    @Transactional
    @Query("update ArticleWithBLOBs st SET  st.uploadtype= :type where st.id= :id")
    public void updateStatus(@Param("id") int id,@Param("type") int type);


}
