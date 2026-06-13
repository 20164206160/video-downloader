package com.xavvier;

import com.microsoft.playwright.*;

import java.util.*;
import java.util.regex.*;

/**
 * @author: lx
 * @since: 2026/06/13/ 15:02
 */
public class PlaywrightM3U8Engine {
    private static final Pattern M3U8_PATTERN =
            Pattern.compile("https?://.*?\\.m3u8[^\"']*");

    public static String extract(String pageUrl) {

        List<String> m3u8List = new ArrayList<>();

        try (Playwright playwright = Playwright.create()) {

            Browser browser = playwright.chromium().launch(
                    new BrowserType.LaunchOptions()
                            .setHeadless(false) // 调试建议 false
            );

            BrowserContext context = browser.newContext();
            Page page = context.newPage();

            // ⭐ 监听 response（工业关键点）
            page.onResponse(response -> {

                String url = response.url();

                if (url.contains(".m3u8")) {
                    System.out.println("🎯 捕获流: " + url);
                    m3u8List.add(url);
                }

                // 有些站放在 JS 返回里
                try {
                    String body = response.text();
                    Matcher m = M3U8_PATTERN.matcher(body);

                    while (m.find()) {
                        String found = m.group();
                        System.out.println("🎯 正文提取: " + found);
                        m3u8List.add(found);
                    }
                } catch (Exception ignored) {}
            });

            // 打开页面
            page.navigate(pageUrl);

            // ⭐ 触发播放（很多站必须点击）
            try {
                page.click("body");
            } catch (Exception ignored) {}

            // 等待网络加载
            page.waitForTimeout(12000);

            browser.close();
        }

        // ⭐ 工业逻辑：选最长/最完整 m3u8
        return selectBest(m3u8List);
    }

    private static String selectBest(List<String> list) {
        if (list.isEmpty()) return null;

        return list.stream()
                .filter(u -> u.contains("http"))
                .max(Comparator.comparingInt(String::length))
                .orElse(list.get(0));
    }
}
