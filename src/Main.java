import bean.TestBean;

/**
 * Created by Lu on 2016/12/30.
 */
public class Main {

    private static String data = "{\n" +
            "    \"name\": \"BeJson\",\n" +
            "    \"url\": \"http://www.bejson.com\",\n" +
            "    \"page\": 88,\n" +
            "    \"isNonProfit\": true,\n" +
            "    \"address\": {\n" +
            "        \"street\": \"keji.\",\n" +
            "        \"city\": \"suzhou\",\n" +
            "        \"country\": \"china\"\n" +
            "    },\n" +
            "    \"links\": [\n" +
            "        {\n" +
            "            \"name\": \"Google\",\n" +
            "            \"url\": \"http://www.google.com\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"name\": \"Baidu\",\n" +
            "            \"url\": \"http://www.baidu.com\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"name\": \"SoSo\",\n" +
            "            \"url\": \"http://www.SoSo.com\"\n" +
            "        }\n" +
            "    ]\n" +
            "}";


    public static void main(String[] args){
        try {
            TestBean testBean = (TestBean) new FuckJson().toObject(TestBean.class, data);
            System.out.println(testBean);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
