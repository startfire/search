package com.java1234.init;

import com.java1234.entity.HotSearch;
import com.java1234.service.HotSearchService;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.List;

/**
 * 初始化加载数据
 * @author fangjian
 */
@Component
public class InitSystem implements ServletContextListener, ApplicationContextAware {

    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext=applicationContext;
    }

    public void loadData(ServletContext application){
        HotSearchService hotSearchService= (HotSearchService) applicationContext.getBean("hotSearchService");
        List<HotSearch> hotSearchList = hotSearchService.listAll(Sort.Direction.ASC,"sort");
        application.setAttribute("hotSearchList",hotSearchList);
    }

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        loadData(sce.getServletContext());
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
