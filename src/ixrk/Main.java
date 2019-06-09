package ixrk;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

    private static final String EMOJI_URL = "https://unicode.org/Public/emoji/12.0/emoji-test.txt";

    public static void main(String[] args) {
        String url;
        if (args.length > 0) {
            url = args[0];
        } else {
            url = EMOJI_URL;
        }

        try {
            List<String> emojis = getEmojiList(url);
            emojis.forEach(System.out::println);
        } catch (MalformedURLException e) {
            System.err.println("Malformed URL");
            System.exit(2);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(3);
        }
    }

    private static List<String> getEmojiList(String urlSpec) throws IOException {
        URL url = new URL(urlSpec);
        BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
        return reader.lines()
                .filter(v -> v.contains("; fully-qualified"))
                .filter(v -> !v.contains("skin tone"))
                .map(v -> v.substring(1 + v.lastIndexOf("#")).trim())
                .collect(Collectors.toList());
    }
}
