package com.letv.cn.csg.task;

import com.letv.cn.csg.RedisSourceManager;
import com.letv.cn.csg.utils.JsoupUtils;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.jsoup.nodes.Document;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 头条
 *
 * @author csg
 * @create 2017-06-23:01
 */
@Component
@Log4j2
@AllArgsConstructor
public class toutiao {
    private static final String HOME_PAGE_PC = "http://www.toutiaogirls.com/atlas?id=539";


    private final RedisSourceManager redisSourceManager;
    @Scheduled(fixedRate = 60* 60* 1000)
    public void start(){
        Document pcDocument = JsoupUtils.getDocWithPC(HOME_PAGE_PC);
        saveCarouselsToRedis(pcDocument);
    }
    void saveCarouselsToRedis(Document pcDocument){
        log.info(pcDocument);
    }
}
