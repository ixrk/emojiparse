package ixrk;

public class Emoji {
    private String code;
    private String description;
    private String value;

    public Emoji(String code, String description, String value) {
        this.code = code;
        this.description = description;
        this.value = value;
    }

    public Emoji(String description, String value) {
        this(null, description, value);
    }

    @Override
    public String toString() {
        if (code != null) {
            return String.join("\t", value, description, code);
        } else {
            return String.join("\t", value, description);
        }
    }
}
