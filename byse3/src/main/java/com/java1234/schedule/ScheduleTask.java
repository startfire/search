package com.java1234.schedule;


import com.java1234.entity.Article;
import com.java1234.entity.ArticleWithBLOBs;
import com.java1234.repository.ArticleRepository;
import com.java1234.repository.ArticleTextRepository;
import org.elasticsearch.action.index.IndexResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import java.util.*;
import java.util.concurrent.Executor;

/**
 * Created by fangjian
 */
@Component
public class ScheduleTask {
    private static final Logger LOG = LoggerFactory.getLogger(ScheduleTask.class);

    @Autowired
    private ArticleTextRepository articleTextRepository;

    @Autowired
    private ArticleRepository articleRepository;
    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    @Autowired
    @Qualifier("Executor")
    private Executor executor;

    /**
     * 同步数据定时任务
     */

    @Scheduled(cron = "0 0/2 * * * ?")
    public void task() {
     List<ArticleWithBLOBs> articleWithBLOBs1 = articleTextRepository.findByType(0);
//        List<Article> articleWithBLOBs1 = articleRepository.findByType(0);

        for (ArticleWithBLOBs articleWithBLOBs : articleWithBLOBs1) {
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    synchronousData(articleWithBLOBs);
                }
            });
        }
    }

    public void synchronousData(ArticleWithBLOBs articleWithBLOBs){
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("id", articleWithBLOBs.getId());
        map.put("name", articleWithBLOBs.getTitle());
        map.put("keywords",articleWithBLOBs.getKeywords());
        map.put("content", articleWithBLOBs.getAbstracttext());

//        map.put("id", articleWithBLOBs.getId());
//        map.put("name", articleWithBLOBs.getName());
//        map.put("keywords",articleWithBLOBs.getContent());
//        map.put("content", articleWithBLOBs.getAbstracttext());

        IndexResponse indexRequestBuilder = elasticsearchTemplate.getClient().prepareIndex("testdemo", "my")
                .setSource(map)
                .get();
        articleRepository.updateStatus(articleWithBLOBs.getId(),1);
    }
}
