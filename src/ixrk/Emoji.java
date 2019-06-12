package ixrk;

public class Emoji {
    private String value;
    private String description;

    public Emoji(String value, String description) {
        this.value = value;
        this.description = description;
    }

    public String getValue() {
        return value;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return String.join("\t", value, description);
    }
}
