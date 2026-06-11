package com.xavvier;

import java.util.regex.*;
import java.util.*;
/**
 * @author: lx
 * @since: 2026/06/11/ 18:13
 */
public class M3U8Finder {

    private static final Pattern M3U8_PATTERN =
            Pattern.compile("https?://[^\"'\\s]+\\.m3u8[^\"'\\s]*");

    public static List<String> find(String html) {
        List<String> result = new ArrayList<>();

        Matcher matcher = M3U8_PATTERN.matcher(html);

        while (matcher.find()) {
            result.add(matcher.group());
        }

        return result;
    }
}
