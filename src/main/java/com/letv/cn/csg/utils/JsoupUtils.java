package com.letv.cn.csg.utils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

/**
 * 工具类
 *
 * @author csg
 * @create 2017-06-23:50
 */
@lombok.extern.log4j.Log4j2
public class JsoupUtils {
    private static final String UA_PHONE = "Mozilla/5.0 (Linux; Android 4.3; Nexus 10 Build/JSS15Q) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/48.0.2564.23 Safari/537.36";
    private static final String UA_PC = "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2661.102 Safari/537.36";
    private static final int TIME_OUT = 10 * 1000;

    public static Document getDocWithPC(String url){
        Document document = new Document("http://ictgu.cn");
        try {
            document = Jsoup.connect(url).userAgent(UA_PC).timeout(TIME_OUT).ignoreContentType(true).get();
        } catch (IOException e) {
            log.info("网址请求失败：" + url);
        }
        return document;
    }

}
