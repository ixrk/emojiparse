package ixrk;

public enum Provider {
    UNICODE_EMOJI_TEST("https://unicode.org/Public/emoji/latest/emoji-test.txt"),
    WIKIPEDIA("https://en.wikipedia.org/wiki/Emoji");

    private String url;

    Provider(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }
}
