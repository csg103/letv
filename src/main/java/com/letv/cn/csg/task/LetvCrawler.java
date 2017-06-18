package com.letv.cn.csg.task;

import com.letv.cn.csg.utils.JsoupUtils;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 乐视TV爬虫 定时任务
 *
 * @author csg
 * @create 2017-06-23:44
 */
@Component
@Log4j2
@AllArgsConstructor
public class LetvCrawler {
    private static final String HOME_PAGE_PC = "http://www.le.com/";
    @Scheduled(fixedRate = 60* 60* 1000)
    public void start(){
        Document pcDocument = JsoupUtils.getDocWithPC(HOME_PAGE_PC);
        saveCarouselsToRedis(pcDocument);

    }
    void saveCarouselsToRedis(Document pcDocument){
        Elements carousels = pcDocument.select("div.chart-info ul.slides li");
        for(Element element : carousels){

            String title  = element.select("div.s-mul a").attr("title");
            String url  = element.select("div.s-mul a").attr("href");
            String img = element.select("div.s-mul img").attr("data-src");
            if("".equals(img)){
                img = element.select("div.s-mul img").attr("img-src");
            };
            Document realDocument = JsoupUtils.getDocWithPC(url);

            Matcher matcher = Pattern.compile("vid:\"(.*?)\"").matcher(realDocument.html());
            if (matcher.find())
                url = String.format("http://www.le.com/ptv/vplay/%s.html", matcher.group(1));
            log.info(title +"-----  "+url+"-----  "+img);
        }

    }
}
