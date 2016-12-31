import exception.JsonParseException;

import java.io.*;

/**
 * Created by Lu on 2016/12/30.
 */
public class TokenReader {
    CharReader reader;

    public TokenReader(Reader in) throws FileNotFoundException {
        reader = new CharReader(in);
    }

    public Token readNextToken() throws IOException {
        char ch = '?';
        for (;;) {
            if (!reader.hasMore()) {
                // EOF:
                return Token.END_DOCUMENT;
            }
            ch = reader.peek();
            if (!isWhiteSpach(ch)) {
                break;
            }
            reader.next(); // skip white space
        }
        switch (ch) {
            case '{':
                reader.next(); // skip
                return Token.BEGIN_OBJECT;
            case '}':
                reader.next(); // skip
                return Token.END_OBJECT;
            case '[':
                reader.next(); // skip
                return Token.BEGIN_ARRAY;
            case ']':
                reader.next(); // skip
                return Token.END_ARRAY;
            case ':':
                reader.next(); // skip
                return Token.SEP_COLON;
            case ',':
                reader.next(); // skip
                return Token.SEP_COMMA;
            case '\"':
                return Token.STRING;
            case 'n':
                return Token.NULL;
            // true / false
            case 't':
            case 'f':
                return Token.BOOLEAN;
            case '-':
                return Token.NUMBER;
        }
        if (ch >= '0' && ch <= '9') {
            return Token.NUMBER;
        }
        throw new JsonParseException("Parse error when try to guess next token.",
                reader.readed);
    }


    private boolean isWhiteSpach(char ch) {
        return ch == ' ' || ch == '\t' || ch == '\n' || ch == '\r';
    }

    public void readNull() throws IOException {
        String expected = "null";
        for (int i = 0; i < expected.length(); i++) {
            char ch = reader.next();
            if (ch != expected.charAt(i)) {
                throw new JsonParseException("读取null失败,请检查后重试");
            }

        }
    }

    public Boolean readBoolean() throws IOException {
        char ch = reader.next();
        String expected = null;
        if (ch == 't') {
            expected = "rue";
        } else if (ch == 'f') {
            expected = "alse";
        } else {
            throw new JsonParseException("读取bool失败,请检查后重试");
        }
        for (int i = 0; i < expected.length(); i++) {
            char c = reader.next();
            if (c != expected.charAt(i)) {
                throw new JsonParseException("读取bool失败,请检查后重试");
            }
        }
        return ch == 't';
    }

    public Number readNumber() throws IOException {
        StringBuilder builder = new StringBuilder();
        for (; ; ) {
            char ch = reader.peek();
            if (('0' <= ch && ch <= '9') || ch == '-' || ch == 'e' || ch == 'E') {
                builder.append(reader.next());
            } else {
                break;
            }
        }
        String rawNumber = builder.toString();
        if ((rawNumber.contains(".")) || (rawNumber.contains("E")) || (rawNumber.contains("e"))) {
            try {
                Double result = Double.parseDouble(rawNumber);
                return result;
            } catch (Exception e) {
                throw new JsonParseException("读取number失败,请检查后重试");
            }
        } else {
            try {
                Integer result = Integer.parseInt(rawNumber);
                return result;
            } catch (Exception e) {
                throw new JsonParseException("读取number失败,请检查后重试");
            }
        }


    }


    public String readString() throws IOException {
        StringBuilder sb = new StringBuilder(50);
        // first char must be ":
        char ch = reader.next();
        if (ch != '\"') {
            throw new JsonParseException("Expected \" but actual is: " + ch,
                    reader.readed);
        }
        for (;;) {
            ch = reader.next();
            if (ch == '\\') {
                // escape: \" \\ \/ \b \f \n \r \t
                char ech = reader.next();
                switch (ech) {
                    case '\"':
                        sb.append('\"');
                        break;
                    case '\\':
                        sb.append('\\');
                        break;
                    case '/':
                        sb.append('/');
                        break;
                    case 'b':
                        sb.append('\b');
                        break;
                    case 'f':
                        sb.append('\f');
                        break;
                    case 'n':
                        sb.append('\n');
                        break;
                    case 'r':
                        sb.append('\r');
                        break;
                    case 't':
                        sb.append('\t');
                        break;
                    case 'u':
                        // read an unicode uXXXX:
                        int u = 0;
                        for (int i = 0; i < 4; i++) {
                            char uch = reader.next();
                            if (uch >= '0' && uch <= '9') {
                                u = (u << 4) + (uch - '0');
                            } else if (uch >= 'a' && uch <= 'f') {
                                u = (u << 4) + (uch - 'a') + 10;
                            } else if (uch >= 'A' && uch <= 'F') {
                                u = (u << 4) + (uch - 'A') + 10;
                            } else {
                                throw new JsonParseException("Unexpected char: " + uch,
                                        reader.readed);
                            }
                        }
                        sb.append((char) u);
                        break;
                    default:
                        throw new JsonParseException("Unexpected char: " + ch,
                                reader.readed);
                }
            } else if (ch == '\"') {
                // end of string:
                break;
            } else if (ch == '\r' || ch == '\n') {
                throw new JsonParseException("Unexpected char: " + ch,
                        reader.readed);
            } else {
                sb.append(ch);
            }
        }
        return sb.toString();
    }
}
