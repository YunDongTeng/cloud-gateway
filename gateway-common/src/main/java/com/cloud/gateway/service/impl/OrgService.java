package com.cloud.gateway.service.impl;

import com.cloud.gateway.entity.Org;
import com.cloud.gateway.mapper.SysOrgDao;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class OrgService {

    @Autowired
    private SysOrgDao sysOrgDao;

    final static String baseUrl = "http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/";

    public String getUrlContent(String url) throws IOException {
        // 创建http请求
        CloseableHttpClient createDefalt = HttpClients.createDefault();
        HttpGet get = new HttpGet(url);
        String resulthtml = "";
        CloseableHttpResponse response = null;
        try {
            response = createDefalt.execute(get);
            if (response.getStatusLine().getStatusCode() == 200) {
                resulthtml = EntityUtils.toString(response.getEntity(), "gb2312");
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (response == null) {
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            createDefalt.close();
        }
        return resulthtml;
    }

    public void getOrg() throws Exception {

        // String htmlContent = getHtmlContent("");


        //Document document = Jsoup.connect(baseUrl).timeout(50 * 1000).get();

        String htmlContent = getUrlContent(baseUrl);
        Document document = Jsoup.parse(htmlContent);

        Elements elements = document.getElementsByClass("provincetr");
        for (Element e : elements) {

            String id = UUID.randomUUID().toString().replace("-", "");
            Elements hrefElements = e.getElementsByTag("a");
            for (Element hrefElement : hrefElements) {
                if (hrefElement.text().equals("山东省")) {
                    System.out.println("------------------------------");
                    System.out.println(hrefElement.text());
                    System.out.println("------------------------------");
                    Org org = new Org(id, hrefElement.text(), "000000", null);

                    System.out.println(org.getName() + "：" + org.getCode());
                     sysOrgDao.save(org);
                    getCity(baseUrl + hrefElement.attr("href"), id);
                }
            }

        }

    }

    public List<Org> getCity(String url, String pid) throws Exception {

        List<Org> cityList = new ArrayList<>();


      //  Document document = Jsoup.connect(url).timeout(500 * 1000).get();
        String htmlContent = getUrlContent(url);
        Document document = Jsoup.parse(htmlContent);
        Elements elements = document.getElementsByClass("citytr");
        for (Element e : elements) {


            Elements hrefElements = e.getElementsByTag("a");

            if (hrefElements.get(1).text().equals("济南市")) {
                String id = UUID.randomUUID().toString().replace("-", "");
                Org org = new Org(id, hrefElements.get(1).text(), hrefElements.get(0).text(), pid);

                 sysOrgDao.save(org);
                System.out.println(org.getName() + "：" + org.getCode());
                getCounty(baseUrl + hrefElements.get(0).attr("href"), id);
            }


        }

        return cityList;
    }

    public List<Org> getCounty(String url, String pid) throws IOException {
        List<Org> countyList = new ArrayList<>();


       // Document document = Jsoup.connect(url).timeout(500 * 1000).get();

        String htmlContent = getUrlContent(url);
        Document document = Jsoup.parse(htmlContent);

        Elements elements = document.getElementsByClass("countytr");
        for (Element e : elements) {

            Elements hrefElements = e.getElementsByTag("a");
            if (hrefElements != null && hrefElements.size() > 0) {
                String id = UUID.randomUUID().toString().replace("-", "");
                Org org = new Org(id, hrefElements.get(1).text(), hrefElements.get(0).text(), pid);

                  sysOrgDao.save(org);
                System.out.println(org.getName() + "：" + org.getCode() + "--------------------------------------------------------");
                getTown(baseUrl + "37/" + hrefElements.get(0).attr("href"), id);

            }


        }

        return countyList;
    }

    public List<Org> getTown(String url, String pid) throws IOException {
        List<Org> townList = new ArrayList<>();


       // Document document = Jsoup.connect(url).timeout(500 * 1000).get();

        String htmlContent = getUrlContent(url);
        Document document = Jsoup.parse(htmlContent);

        Elements elements = document.getElementsByClass("towntr");
        for (Element e : elements) {


            Elements hrefElements = e.getElementsByTag("a");
            if (hrefElements != null && hrefElements.size() > 0) {

                String id = UUID.randomUUID().toString().replace("-", "");
                Org org = new Org(id, hrefElements.get(1).text(), hrefElements.get(0).text(), pid);

                 sysOrgDao.save(org);

                System.out.println(org.getName() + "：" + org.getCode());
                String townUrl = hrefElements.get(0).attr("href");
                System.out.println(baseUrl + "37/01/" + townUrl);
                getVillage(baseUrl + "37/01/" + townUrl, id);

            }


        }

        return townList;
    }

    public List<Org> getVillage(String url, String pid) throws IOException {
        List<Org> townList = new ArrayList<>();


      //  Document document = Jsoup.connect(url).timeout(5000 * 1000).get();

        String htmlContent = getUrlContent(url);
        Document document = Jsoup.parse(htmlContent);

        Elements elements = document.getElementsByClass("villagetr");

        for (Element element : elements) {
            Elements villageElement = element.getElementsByTag("td");
            String id = UUID.randomUUID().toString().replace("-", "");
            Org org = new Org(id, villageElement.get(2).text(), villageElement.get(0).text(), pid);
            System.out.println(org.getName() + "：" + org.getCode());

            sysOrgDao.save(org);
        }

        return townList;
    }
}
