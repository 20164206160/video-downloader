package com.xavvier;

import java.util.List;

/**
 * @author lixiang
 */
public class Main {
    public static void main(String[] args) throws Exception {

        String url = "https://vidhub2.top/vodplay/295169-1-1.html";

        // 1. 抓页面
        String html = PageFetcher.fetchHtml(url);

        // 2. 找 m3u8
        var m3u8List = M3U8Finder.find(html);

        // 2. 如果没找到，去 iframe
        List<String> iframeUrls = IframeCrawler.extract(html, "");
        if (m3u8List.isEmpty()) {
            for (String iframe : iframeUrls) {
                String iframeHtml = PageFetcher.fetchHtml(iframe);
                m3u8List = M3U8Finder.find(iframeHtml);
                if (!m3u8List.isEmpty()) break;
            }
        }

        if (!m3u8List.isEmpty()) {
            String m3u8 = m3u8List.get(0);

            // 3. 调 ffmpeg 下载
            FFmpegDownloader.download(m3u8, "output.mp4");
        } else {
            System.out.println("未找到 m3u8，可能在 iframe 或 JS 中");
        }
    }
}