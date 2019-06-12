# emojiparse
Parses [Unicode's](https://unicode.org/Public/emoji/latest/emoji-test.txt) or [Wikipedia's](https://en.wikipedia.org/wiki/Emoji) list of emojis and prints emoji-description pairs to standard output.

## How to use

### Command line interface

```
java -jar emojiparse.jar [unicode-test | wikipedia]
```

### Java interface

Use `Emojiparse.getEmojis(Provider)` method to get a List of Emoji objects.

#### Available providers:

* Provider.UNICODE_EMOJI_TEST
* Provider.WIKIPEDIA

## Build dependecies

* [jsoup](https://jsoup.org/): Java HTML Parser (version 1.12.1)