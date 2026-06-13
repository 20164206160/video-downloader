package com.xavvier;

/**
 * @author: lx
 * @since: 2026/06/13/ 15:02
 */
public class FFmpegEngine {
    public static void download(String m3u8, String output) throws Exception {

        ProcessBuilder pb = new ProcessBuilder(
                "ffmpeg",

                "-headers",
                "User-Agent: Mozilla/5.0\r\nReferer: https://vidhub2.top\r\n",

                "-i", m3u8,

                "-c", "copy",
                "-bsf:a", "aac_adtstoasc",

                "-threads", "8",

                output
        );

        pb.inheritIO();
        pb.start().waitFor();
    }
}
