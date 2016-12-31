import util.ClassUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Lu on 2016/12/31.
 */
public class FuckJson {
    private Class<?> clazz;
    private String data;
    private JsonReader reader;

    public Object toObject(Class<?> clazz, String data) throws Exception {
        this.clazz = clazz;
        this.data = data;
        this.reader = new JsonReader(data);
        Object result = parseObject(clazz,  (HashMap<String, Object>)reader.parse());
        return result;
    }

    private Object parseObject(Class<?> clazz, HashMap<String, Object> map) throws Exception{
        Object result = clazz.newInstance();
        for(String key : map.keySet()){
            Object value = map.get(key);
            if(value instanceof Boolean){
                Boolean v = (Boolean) value;
                ClassUtil.setMethod(clazz, key, result, v);
                continue;
            }
            if(value instanceof Integer){
                int n = (int) value;
                ClassUtil.setMethod(clazz, key, result, n);
                continue;
            }
            if(value instanceof Long){
                long n = (long) value;
                ClassUtil.setMethod(clazz, key, result, n);
                continue;
            }
            if(value instanceof Double){
                double n = (double) value;
                ClassUtil.setMethod(clazz, key, result, n);
                continue;
            }
            if(value instanceof Map){
                HashMap<String, Object> m = (HashMap<String, Object>) value;
                Class<?> innerClass = ClassUtil.getInnerClass(clazz, key);
                Object o = parseObject(innerClass, m);
                ClassUtil.setMethod(clazz, key, result, o, innerClass);
                continue;
            }
            if(value instanceof List){
                List l = (List) value;
                Class<?> innerClass = ClassUtil.getInnerClass(clazz, key);
                List<Object> fuck = new ArrayList();
                for (Object o : l) {
                    HashMap<String, Object> m = (HashMap<String, Object>) o;
                    Object object = parseObject(innerClass, m);
                    fuck.add(object);
                }
                ClassUtil.setMethod(clazz, key, result, fuck);
                continue;
            }
            if(value instanceof String){
                String s = (String) value;
                ClassUtil.setMethod(clazz, key, result, s);
                continue;
            }
        }
        return result;
    }

}
