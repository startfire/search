package com.java1234.controller;

import com.java1234.entity.es.ArticleInfo;
import com.java1234.service.ArticleService;
import com.java1234.util.PageUtil;
import com.java1234.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * 根目录Controller
 * @author fangjian
 */
@Controller
public class IndexController {

    @Autowired
    private ArticleService articleService;

    /**
     * 根目录请求
     * @return
     */
    @RequestMapping("/")
    public ModelAndView root(){
        ModelAndView mav=new ModelAndView();
        mav.setViewName("index");
        mav.addObject("title","首页");
        return mav;
    }

    /**
     * 登录请求
     * @return
     */
    @RequestMapping("/login")
    public String login(){
        return "/login";
    }

    /**
     * 进入后台管理请求
     * @return
     */
    @RequestMapping("/admin")
    public String toAdmin(){
        return "/admin/main";
    }

    /**
     * 分词查询
     * @param q
     * @param page
     * @return
     * @throws Exception
     */
    @RequestMapping("/search")
    public ModelAndView search(@RequestParam(value="q",required=false) String q, @RequestParam(value="page",required=false) String page)throws Exception{
        ModelAndView mav=new ModelAndView();
        if(StringUtil.isEmpty(q)){
            mav.setViewName("index");
            mav.addObject("title","首页");
            return mav;
        }
        int pageSize=10;
        if(StringUtil.isEmpty(page)){
            page="1";
        }
        mav.addObject("q", q);
        List<ArticleInfo> articleInfoList = articleService.search(Integer.parseInt(page), pageSize, q);

        Long total=articleService.searchCount(q);
        mav.addObject("articleList", articleInfoList);
        mav.addObject("modeName","' "+q+" ' - 搜索结果");
//        mav.addObject("resultTotal",total);
        if(articleInfoList.size()>0){
        mav.addObject("resultTotal",articleInfoList.size());}
        else{
            mav.addObject("resultTotal",0);
        }
        mav.addObject("pageCode", PageUtil.genSearchPagination("./search", total, Integer.parseInt(page), pageSize,q));
        mav.addObject("title",q);
        mav.setViewName("result");
        return mav;
    }
}
