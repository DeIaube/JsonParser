import java.util.List;
import java.util.Map;

/**
 * Created by Lu on 2016/12/31.
 */
public class StackValue {
    static final int TYPE_OBJECT = 0;

    static final int TYPE_OBJECT_KEY = 1;

    static final int TYPE_ARRAY = 2;

    static final int TYPE_SINGLE = 3;

    final int type;
    final Object valuse;

    private StackValue(int type, Object valuse) {
        this.type = type;
        this.valuse = valuse;
    }


    static StackValue newJsonObject(Map<String, Object> map){
        return new StackValue(TYPE_OBJECT, map);
    }

    static StackValue newJsonObjectKey(String key){
        return new StackValue(TYPE_OBJECT_KEY, key);
    }

    static StackValue newJsonArray(List<Object> arr){
        return new StackValue(TYPE_ARRAY, arr);
    }

    static StackValue newJsonSingle(Object obj){
        return new StackValue(TYPE_SINGLE, obj);
    }

    String valueAsKey(){
        return (String) valuse;
    }

    Map<String, Object> valueAsObject(){
        return (Map<String, Object>) valuse;
    }

    List<Object> valueAsArray(){
        return (List<Object>) valuse;
    }


    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        switch (type){
            case TYPE_SINGLE:
                builder.append(valuse.toString());
                break;
            case TYPE_OBJECT:
                builder.append(valueAsObject().toString());
                break;
            case TYPE_ARRAY:
                builder.append(valueAsArray().toString());
                break;
            case TYPE_OBJECT_KEY:
                builder.append(valueAsKey());
                break;
        }
        return builder.toString();
    }
}
