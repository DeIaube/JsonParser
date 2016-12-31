package util;

import java.lang.reflect.Method;
import java.util.List;

/**
 * Created by Lu on 2016/12/31.
 */
public class ClassUtil {
    private static String generateNormalGetName(String name){
        String result =  "set" + Character.toUpperCase(name.charAt(0)) + name.substring(1, name.length());
        return result;
    }

//    private static String generateBooleanGetName(String name){
//        return "is" + Character.toUpperCase(name.charAt(0)) + name.substring(1, name.length());
//    }

    private static String generateInnerClassName(String name){
        return "" + Character.toUpperCase(name.charAt(0)) + name.substring(1, name.length()) + "Bean";
    }

    public static Class<?> getInnerClass(Class<?> clazz, String key){
        for (Class<?> aClass : clazz.getDeclaredClasses()) {
            if(aClass.getName().contains(generateInnerClassName(key))){
                return aClass;
            }
        }
        return null;
    }

    public static void setMethod(Class<?> clazz, String key,Object object, Boolean b) throws Exception {
        String methodName = generateNormalGetName(key);
        Method method = clazz.getMethod(methodName, boolean.class);
        method.invoke(object, b);
    }

    public static void setMethod(Class<?> clazz, String key,Object object, String s) throws Exception {
        String methodName = generateNormalGetName(key);
        Method method = clazz.getMethod(methodName, String.class);
        method.invoke(object, s);
    }

    public static void setMethod(Class<?> clazz, String key,Object object, int n) throws Exception {
        String methodName = generateNormalGetName(key);
        Method method = clazz.getMethod(methodName, int.class);
        method.invoke(object, n);
    }

    public static void setMethod(Class<?> clazz, String key,Object object, long n) throws Exception {
        String methodName = generateNormalGetName(key);
        Method method = clazz.getMethod(methodName, long.class);
        method.invoke(object, n);
    }

    public static void setMethod(Class<?> clazz, String key,Object object, double n) throws Exception {
        String methodName = generateNormalGetName(key);
        Method method = clazz.getMethod(methodName, double.class);
        method.invoke(object, n);
    }

    public static void setMethod(Class<?> clazz, String key,Object object, Object n, Class<?> innerClazz) throws Exception {
        String methodName = generateNormalGetName(key);
        Method method = clazz.getMethod(methodName, innerClazz);
        method.invoke(object, n);
    }

    public static void setMethod(Class<?> clazz, String key, Object object, List list) throws Exception {
        String methodName = generateNormalGetName(key);
        Method method = clazz.getMethod(methodName, List.class);
        method.invoke(object, list);
    }
}
