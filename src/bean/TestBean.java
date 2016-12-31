package bean;

import java.util.List;

/**
 * Created by Lu on 2016/12/31.
 */
public class TestBean {
    /**
     * name : BeJson
     * url : http://www.bejson.com
     * page : 88
     * isNonProfit : true
     * address : {"street":"keji.","city":"suzhou","country":"china"}
     * links : [{"name":"Google","url":"http://www.google.com"},{"name":"Baidu","url":"http://www.baidu.com"},{"name":"SoSo","url":"http://www.SoSo.com"}]
     */

    private String name;
    private String url;
    private int page;
    private boolean isNonProfit;
    /**
     * street : keji.
     * city : suzhou
     * country : china
     */

    private AddressBean address;

    @Override
    public String toString() {
        return "TestBean{" +
                "name='" + name + '\'' +
                ", url='" + url + '\'' +
                ", page=" + page +
                ", isNonProfit=" + isNonProfit +
                ", address=" + address +
                ", links=" + links +
                '}';
    }

    /**
     * name : Google
     * url : http://www.google.com
     */

    private List<LinksBean> links;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public boolean isIsNonProfit() {
        return isNonProfit;
    }

    public void setIsNonProfit(boolean isNonProfit) {
        this.isNonProfit = isNonProfit;
    }

    public AddressBean getAddress() {
        return address;
    }

    public void setAddress(AddressBean address) {
        this.address = address;
    }

    public List<LinksBean> getLinks() {
        return links;
    }

    public void setLinks(List<LinksBean> links) {
        this.links = links;
    }

    public static class AddressBean {
        private String street;
        private String city;
        private String country;

        @Override
        public String toString() {
            return "AddressBean{" +
                    "street='" + street + '\'' +
                    ", city='" + city + '\'' +
                    ", country='" + country + '\'' +
                    '}';
        }

        public String getStreet() {
            return street;
        }

        public void setStreet(String street) {
            this.street = street;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }
    }

    public static class LinksBean {
        private String name;
        private String url;

        @Override
        public String toString() {
            return "LinksBean{" +
                    "name='" + name + '\'' +
                    ", url='" + url + '\'' +
                    '}';
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
