package com.java1234.config;

/**
 * @author ：fangjian
 * @date ：Created in 2019/9/23 14:43
 */


import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * 线程池配置
 * @author zhh
 *
 */
@Configuration
@EnableAsync
public class ThreadPoolTaskConfig {

    /**
     * 默认情况下，在创建了线程池后，线程池中的线程数为0，当有任务来之后，就会创建一个线程去执行任务，
     * 当线程池中的线程数目达到corePoolSize后，就会把到达的任务放到缓存队列当中；
     * 当队列满了，就继续创建线程，当线程数量大于等于maxPoolSize后，开始使用拒绝策略拒绝
     */

    private static final int corePoolSize = 20;            // 核心线程数（默认线程数）
    private static final int maxPoolSize = 100;                // 最大线程数
    private static final int keepAliveTime = 10;            // 允许线程空闲时间（单位：默认为秒）
    private static final int queueCapacity = 200;            // 缓冲队列数
    private static final String threadNamePrefix = "Async-Service-"; // 线程池名前缀

    @Bean("Executor")
    public Executor asyncServiceExecutor() {
//        logger.info("start asyncServiceExecutor");
        //使用VisiableThreadPoolTaskExecutor
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        //配置核心线程数
        executor.setCorePoolSize(5);
        //配置最大线程数
        executor.setMaxPoolSize(5);
        //配置队列大小
        executor.setQueueCapacity(99999);
        //配置线程池中的线程的名称前缀
        executor.setThreadNamePrefix("async-service-");

        // rejection-policy：当pool已经达到max size的时候，如何处理新任务
        // CALLER_RUNS：不在新线程中执行任务，而是有调用者所在的线程来执行
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        //执行初始化
        executor.initialize();
        return executor;
    }


}
