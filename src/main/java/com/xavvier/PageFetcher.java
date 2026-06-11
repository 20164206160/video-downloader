package com.xavvier;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

/**
 * @author: lx
 * @since: 2026/06/11/ 18:12
 */
public class PageFetcher {

    public static String fetchHtml(String url) throws Exception {
        Document doc = Jsoup.connect(url)
                .userAgent("Mozilla/5.0")
                .timeout(10000)
                .get();

        return doc.html();
    }
}
