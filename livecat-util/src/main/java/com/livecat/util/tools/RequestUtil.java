package com.livecat.util.tools;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class RequestUtil {
    public static String[] extractAndDecodeHeader(String header) throws IOException{
        byte[] base64Token = header.trim().substring(6).getBytes(StandardCharsets.UTF_8);

        byte[] decoded;
        try {
            decoded = Base64.getDecoder().decode(base64Token);
        } catch (IllegalArgumentException var8){
            throw new RuntimeException("Request header parsing failed: " + header);
        }

        String token = new String(decoded, StandardCharsets.UTF_8);
        int delim = token.indexOf(":");
        if(delim == -1){
            throw new RuntimeException("Request header not valid: " + header);
        } else {
            return new String[]{token.substring(0, delim), token.substring(delim + 1)};
        }
    }
}
