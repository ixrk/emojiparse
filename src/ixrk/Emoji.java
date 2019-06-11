package ixrk;

public class Emoji {
    private String value;
    private String description;

    public Emoji(String value, String description) {
        this.value = value;
        this.description = description;
    }

    @Override
    public String toString() {
        return String.join("\t", value, description);
    }
}
