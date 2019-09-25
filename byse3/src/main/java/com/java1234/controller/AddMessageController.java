package com.java1234.controller;

import com.java1234.entity.ArticleWithBLOBs;
import com.java1234.entity.es.ArticleInfo;
import com.java1234.repository.ArticleTextRepository;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
/**
 * @author fangjian
 */
@RestController
public class AddMessageController {
    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    @Autowired
    private ArticleTextRepository articleTextRepository;

//测试查询接口
    @RequestMapping("/query")
    @ResponseBody
    public String query(){
        BoolQueryBuilder boolQueryBuilder= QueryBuilders.boolQuery()
                .should(QueryBuilders.matchQuery("name","java"))
                .should(QueryBuilders.matchQuery("content","java"));
        NativeSearchQuery nativeSearchQuery=new NativeSearchQueryBuilder()
                .withQuery(boolQueryBuilder)
//                .withPageable(pageRequest)
                .withIndices("test2").build();
        List<ArticleInfo> articleInfos = elasticsearchTemplate.queryForList(nativeSearchQuery, ArticleInfo.class);
        return "success";
        }

//测试创建接口
    @RequestMapping("/create")
    @ResponseBody
    public String create(){
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("id","9");
        map.put("name","我是");
        map.put("content","我是谁");
        IndexResponse indexRequestBuilder = elasticsearchTemplate.getClient().prepareIndex("test2", "my","9999")
                .setSource(map)
                .get();
        return "success";
    }

//测试
    @RequestMapping("/test")
    @ResponseBody
    public String testmapper(){
      //查询所有未同步的字段
        List<ArticleWithBLOBs> articleWithBLOBs1=articleTextRepository.findByType(2);
//      for(ArticleWithBLOBs articleWithBLOBs:articleWithBLOBs1){
//          Map<String, Object> map = new HashMap<String, Object>();
//          map.put("id",articleWithBLOBs.getId());
//          map.put("name",articleWithBLOBs.getTitle());
//          map.put("content",articleWithBLOBs.getAbstracttext());
//          IndexResponse indexRequestBuilder = elasticsearchTemplate.getClient().prepareIndex("testdemo", "my")
//                  .setSource(map)
//                  .get();
//          return "success";
//      }
        return "success";
    }


}
