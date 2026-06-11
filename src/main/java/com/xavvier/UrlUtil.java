package com.xavvier;

import java.net.URI;

/**
 * @author: lx
 * @since: 2026/06/11/ 18:44
 */
public class UrlUtil {
    public static String resolve(String base, String url) {
        try {
            return URI.create(base).resolve(url).toString();
        } catch (Exception e) {
            return url;
        }
    }
}
