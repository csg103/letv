package com.letv.cn.csg.task;

import com.letv.cn.csg.utils.JsoupUtils;
import com.sun.deploy.net.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/*
* 描述 ： 
* 作者 ： csg
* 时间 ： 2018-06-28
*/
public class zhuaqu {


    public static void main(String[] args) throws IOException {
        HttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet("http://www.dianping.com/search/keyword/2/0_%E6%97%85%E8%A1%8C%E7%A4%BE/r16");
        httpGet.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:60.0) Gecko/20100101 Firefox/60.0");
        org.apache.http.HttpResponse response = httpClient.execute(httpGet);
        String contents = EntityUtils.toString(response.getEntity(), "gbk");//utf-8
         Document document = Jsoup.parse(contents);



//
//        String HOME_PAGE_PC = "http://www.dianping.com/search/keyword/2/0_%E6%97%85%E8%A1%8C%E7%A4%BE/r16";
//        Document pcDocument = JsoupUtils.getDocWithPC(HOME_PAGE_PC);
        //抓取分区List
        List fenquList = new ArrayList();
        Elements fenquelements = document.select("div.nc-items").get(2).select("a");

        for (Element element : fenquelements) {
            fenquVo vo = new fenquVo();
            vo.setFenquUrl(element.attr("abs:href"));
            vo.setFenquName(element.attr("data-click-title"));
            fenquList.add(vo);

        }


        Elements carousels = document.select("div.shop-all-list");
        Elements carousels1 = carousels.select("div.pic");

        List urlList = new ArrayList();
        for (Element element : carousels1) {

            Elements a = element.select("a");
            urlList.add(a.attr("abs:href"));

        }


        System.out.print(urlList);
    }
}


class fenquVo {
    private String fenquName;

    public String getFenquName() {
        return fenquName;
    }

    public void setFenquName(String fenquName) {
        this.fenquName = fenquName;
    }

    public String getFenquUrl() {
        return fenquUrl;
    }

    public void setFenquUrl(String fenquUrl) {
        this.fenquUrl = fenquUrl;
    }

    private String fenquUrl;

}