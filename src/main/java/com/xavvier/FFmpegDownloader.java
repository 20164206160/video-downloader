package com.xavvier;

/**
 * @author: lx
 * @since: 2026/06/11/ 18:14
 */

public class FFmpegDownloader {
    public static void download(String m3u8Url, String output) throws Exception {
        ProcessBuilder pb = new ProcessBuilder(
                "ffmpeg",
                "-headers", "Referer: https://target-site.com\r\nUser-Agent: Mozilla/5.0",
                "-i", m3u8Url,
                "-c", "copy",
                "-threads", "8",
                output
        );

        pb.inheritIO();
        pb.start().waitFor();
    }
}
