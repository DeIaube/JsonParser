package exception;

import java.io.IOException;

/**
 * Created by Lu on 2016/12/30.
 */
public class JsonParseException extends IOException{
    public JsonParseException(String message) {
        super(message);
    }

    public JsonParseException(String message, int index) {
        super(message + "错误定位" + index);
    }
}
