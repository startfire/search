package com.java1234.repository;

import com.java1234.entity.HotSearch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * 热门搜索Repository接口
 * @author fangjian
 */
public interface HotSearchRepository extends JpaRepository<HotSearch,Integer>, JpaSpecificationExecutor<HotSearch> {

}
