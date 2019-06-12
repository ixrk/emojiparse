package ixrk;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

    private static final String EMOJI_URL = "https://unicode.org/Public/emoji/latest/emoji-test.txt";

    public static void main(String[] args) {
        try {
            List<Emoji> emojis = getEmojiList();
            emojis.forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(3);
        }
    }

    private static List<Emoji> getEmojiList() throws IOException {
        URL url = new URL(EMOJI_URL);
        BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
        List<String> lines = reader.lines()
                .filter(v -> v.contains("; fully-qualified"))
                .filter(v -> !v.contains("skin tone"))
                .map(v -> v.substring(1 + v.indexOf("#")).trim())
                .collect(Collectors.toList());

        List<Emoji> emojis = new ArrayList<>(lines.size());
        for (String line : lines) {
            int firstSpace = line.indexOf(" ");
            String value = line.substring(0, line.indexOf(" "));
            String description = line.substring(1 + line.indexOf(" "));
            emojis.add(new Emoji(value, description));
        }
        return emojis;
    }
}
