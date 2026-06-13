package com.xavvier;

/**
 * @author: lx
 * @since: 2026/06/13/ 15:03
 */
public class Main {
    public static void main(String[] args) throws Exception {

        String url = "https://vidhub2.top/vodplay/295169-1-1.html";

        System.out.println("🚀 开始解析页面...");

        String m3u8 = PlaywrightM3U8Engine.extract(url);

        if (m3u8 == null) {
            System.out.println("❌ 未捕获到 m3u8");
            return;
        }

        System.out.println("✅ 最终 m3u8: " + m3u8);

        System.out.println("📥 开始下载...");

        FFmpegEngine.download(m3u8, "output.mp4");

        System.out.println("🎉 下载完成");
    }
}
