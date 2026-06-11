package com.xavvier;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import java.util.*;

/**
 * @author: lx
 * @since: 2026/06/11/ 18:14
 */
public class IframeCrawler {

    public static List<String> extract(String html, String baseUrl) {

        List<String> urls = new ArrayList<>();
        Document doc = Jsoup.parse(html);

        for (Element e : doc.select("iframe, a, script")) {

            String src = e.hasAttr("src") ? e.attr("src") : e.attr("href");

            if (src == null || src.isEmpty()) continue;

            String full = UrlUtil.resolve(baseUrl, src);
            urls.add(full);
        }

        return urls;
    }
}
