/**
 * Created by Lu on 2016/12/30.
 */
public enum  Token {
    END_DOCUMENT,
    BEGIN_OBJECT,
    END_OBJECT,
    BEGIN_ARRAY,
    END_ARRAY,
    SEP_COLON,//读取一个冒号
    SEP_COMMA,//读取一个逗号
    STRING,
    BOOLEAN,
    NUMBER,NULL
}
