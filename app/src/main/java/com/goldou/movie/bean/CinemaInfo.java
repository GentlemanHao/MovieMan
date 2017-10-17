package com.goldou.movie.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/8/1 0001.
 */

public class CinemaInfo {

    private List<Data> data;

    public List<Data> getData() {
        return data;
    }

    public void setData(List<Data> data) {
        this.data = data;
    }

    public static class Data {
        /**
         * addr : 中牟县郑州航空港区郑港七路与郑港六街交叉口（山顶时代广场四楼）
         * lat : 34.562
         * lng : 113.86781
         * ct :
         * sellPrice : 23
         * area : 中牟县
         * poiId : 41670475
         * preferential : 0
         * brd : 其它
         * dis :
         * imax : 0
         * sellmin : 0
         * sell : true
         * nm : 奥斯卡爱家影城
         * deal : 0
         * distance : 0
         * dealPrice : 0
         * referencePrice : 0
         * showCount : 0
         * brdId : 0
         * id : 12886
         * follow : 0
         */

        private String addr;
        private double lat;
        private double lng;
        private String ct;
        private double sellPrice;
        private String area;
        private int poiId;
        private int preferential;
        private String brd;
        private String dis;
        private int imax;
        private int sellmin;
        private boolean sell;
        private String nm;
        private int deal;
        private int distance;
        private int dealPrice;
        private int referencePrice;
        private int showCount;
        private int brdId;
        private int id;
        private int follow;

        public String getAddr() {
            return addr;
        }

        public void setAddr(String addr) {
            this.addr = addr;
        }

        public double getLat() {
            return lat;
        }

        public void setLat(double lat) {
            this.lat = lat;
        }

        public double getLng() {
            return lng;
        }

        public void setLng(double lng) {
            this.lng = lng;
        }

        public String getCt() {
            return ct;
        }

        public void setCt(String ct) {
            this.ct = ct;
        }

        public double getSellPrice() {
            return sellPrice;
        }

        public void setSellPrice(double sellPrice) {
            this.sellPrice = sellPrice;
        }

        public String getArea() {
            return area;
        }

        public void setArea(String area) {
            this.area = area;
        }

        public int getPoiId() {
            return poiId;
        }

        public void setPoiId(int poiId) {
            this.poiId = poiId;
        }

        public int getPreferential() {
            return preferential;
        }

        public void setPreferential(int preferential) {
            this.preferential = preferential;
        }

        public String getBrd() {
            return brd;
        }

        public void setBrd(String brd) {
            this.brd = brd;
        }

        public String getDis() {
            return dis;
        }

        public void setDis(String dis) {
            this.dis = dis;
        }

        public int getImax() {
            return imax;
        }

        public void setImax(int imax) {
            this.imax = imax;
        }

        public int getSellmin() {
            return sellmin;
        }

        public void setSellmin(int sellmin) {
            this.sellmin = sellmin;
        }

        public boolean isSell() {
            return sell;
        }

        public void setSell(boolean sell) {
            this.sell = sell;
        }

        public String getNm() {
            return nm;
        }

        public void setNm(String nm) {
            this.nm = nm;
        }

        public int getDeal() {
            return deal;
        }

        public void setDeal(int deal) {
            this.deal = deal;
        }

        public int getDistance() {
            return distance;
        }

        public void setDistance(int distance) {
            this.distance = distance;
        }

        public int getDealPrice() {
            return dealPrice;
        }

        public void setDealPrice(int dealPrice) {
            this.dealPrice = dealPrice;
        }

        public int getReferencePrice() {
            return referencePrice;
        }

        public void setReferencePrice(int referencePrice) {
            this.referencePrice = referencePrice;
        }

        public int getShowCount() {
            return showCount;
        }

        public void setShowCount(int showCount) {
            this.showCount = showCount;
        }

        public int getBrdId() {
            return brdId;
        }

        public void setBrdId(int brdId) {
            this.brdId = brdId;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getFollow() {
            return follow;
        }

        public void setFollow(int follow) {
            this.follow = follow;
        }
    }
}
