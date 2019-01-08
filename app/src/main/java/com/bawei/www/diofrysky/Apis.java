package com.bawei.www.diofrysky;

public class Apis {

   public static String BASE_URL="http://mobile.bwstudent.com/small/";
 //public static String BASE_URL="http://172.17.8.100/small/";


    public static String LOGIN_URL="user/v1/login";

    public static String SIGNUP_URL="user/v1/register";

    public static String HOME_GOODS_THINGS="commodity/v1/commodityList";

    public static String HOME_GOODS_SEARCH="commodity/v1/findCommodityByKeyword?keyword=%s&page=1&count=7";

    public static String HOME_GOODS_BANNER="commodity/v1/bannerShow";

    public static String HOME_GOODS_DETAILS="commodity/v1/findCommodityDetailsById?commodityId=%d";

    public static String PEOPLE_CIRLE_URL="circle/v1/findCircleList?page=%d&count=10";

    public static String ADD_SHOPCAR="order/verify/v1/syncShoppingCart";

    public static String SEARCH_SHOPCAR="order/verify/v1/findShoppingCart";

}
