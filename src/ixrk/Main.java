package ixrk;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        try {
            List<Emoji> emojis = getEmojis(parseProvider(args));
            emojis.forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(2);
        }
    }

    public static List<Emoji> getEmojis(Provider provider) throws IOException {
        if (provider == null) {
            return getUnicodeTestEmojis();
        }
        switch (provider) {
            case WIKIPEDIA:
                return getWikipediaEmojis();
            case UNICODE_EMOJI_TEST:
            default:
                return getUnicodeTestEmojis();
        }
    }

    private static Provider parseProvider(String[] args) {
        if (args.length > 0) {
            switch (args[0]) {
                case "wikipedia":
                    return Provider.WIKIPEDIA;
                case "unicode-test":
                    return Provider.UNICODE_EMOJI_TEST;
                default:
                    return null;
            }
        } else {
            return null;
        }
    }

    private static List<Emoji> getUnicodeTestEmojis() throws IOException {
        URL url = new URL(Provider.UNICODE_EMOJI_TEST.getUrl());
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

    private static List<Emoji> getWikipediaEmojis() throws IOException {
        Document doc = Jsoup.connect(Provider.WIKIPEDIA.getUrl()).get();
        Element tableBody = doc.getElementsByClass("wikitable nounderlines")
                .last()
                .getElementsByTag("tbody")
                .first();

        List<Emoji> emojis = new ArrayList<>();
        for (Element td : tableBody.getElementsByTag("td")) {
            if (td.hasAttr("title") && td.attr("title").matches("^U\\+.*: .+")) {
                String emojiInfo = td.attr("title");
                String emojiValue = td.getElementsByTag("a").text();
                String emojiDescription = emojiInfo.substring(2 + emojiInfo.indexOf(":"));
                emojis.add(new Emoji(emojiValue, emojiDescription));
            }
        }
        return emojis;
    }
}

