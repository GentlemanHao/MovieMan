package com.goldou.movie.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Administrator on 2017/8/8 0008.
 */

public class CinemaDetailInfo {

    private ControlBean control;
    private int status;
    private DataBean data;

    public ControlBean getControl() {
        return control;
    }

    public void setControl(ControlBean control) {
        this.control = control;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class ControlBean {
        /**
         * expires : 120
         */

        private int expires;

        public int getExpires() {
            return expires;
        }

        public void setExpires(int expires) {
            this.expires = expires;
        }
    }

    public static class DataBean {

        private CurrentMovieBean currentMovie;
        private CinemaDetailModelBean cinemaDetailModel;
        private String cssLink;
        private List<DatesBean> Dates;
        private List<MoviesBean> movies;

        public CurrentMovieBean getCurrentMovie() {
            return currentMovie;
        }

        public void setCurrentMovie(CurrentMovieBean currentMovie) {
            this.currentMovie = currentMovie;
        }

        public CinemaDetailModelBean getCinemaDetailModel() {
            return cinemaDetailModel;
        }

        public void setCinemaDetailModel(CinemaDetailModelBean cinemaDetailModel) {
            this.cinemaDetailModel = cinemaDetailModel;
        }

        public String getCssLink() {
            return cssLink;
        }

        public void setCssLink(String cssLink) {
            this.cssLink = cssLink;
        }

        public List<DatesBean> getDates() {
            return Dates;
        }

        public void setDates(List<DatesBean> Dates) {
            this.Dates = Dates;
        }

        public List<MoviesBean> getMovies() {
            return movies;
        }

        public void setMovies(List<MoviesBean> movies) {
            this.movies = movies;
        }

        public static class CurrentMovieBean {
            /**
             * sc : 9.7
             * preferential : 0
             * ver : 2D/3D/中国巨幕/全景声
             * isShowing : true
             * img : http://p0.meituan.net/165.220/movie/02ac72c0e8ee2987f7662ad921a2acc7999433.jpg
             * nm : 战狼2
             * id : 344264
             */

            private double sc;
            private int preferential;
            private String ver;
            private boolean isShowing;
            private String img;
            private String nm;
            private int id;

            public double getSc() {
                return sc;
            }

            public void setSc(double sc) {
                this.sc = sc;
            }

            public int getPreferential() {
                return preferential;
            }

            public void setPreferential(int preferential) {
                this.preferential = preferential;
            }

            public String getVer() {
                return ver;
            }

            public void setVer(String ver) {
                this.ver = ver;
            }

            public boolean isIsShowing() {
                return isShowing;
            }

            public void setIsShowing(boolean isShowing) {
                this.isShowing = isShowing;
            }

            public String getImg() {
                return img;
            }

            public void setImg(String img) {
                this.img = img;
            }

            public String getNm() {
                return nm;
            }

            public void setNm(String nm) {
                this.nm = nm;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }
        }

        public static class CinemaDetailModelBean {
            /**
             * addr : 金水区太康路二七路交叉口丹尼斯大卫城6楼
             * price : 0
             * lat : 34.756752
             * lng : 113.66641
             * ct : 郑州
             * area : 金水区
             * preferential : 0
             * callboard :
             * brd : CGV影城
             * dis :
             * tel : ["0371-53361866"]
             * suw :
             * dri :
             * note : 周二为半价日！
             * dealtp : 0
             * bus :
             * park : 丹尼斯地下停车场24小时开放
             * imax : 1
             * snum : 22
             * s : 7.1363635
             * s1 : 8.181818
             * s2 : 8.181818
             * s3 : 8.545455
             * featureTags : [{"desc":"4号全新帕萨特ScreenX厅，130个座位","type":19,"tag":"60帧厅"},{"desc":"IMAX厅，396个座位","type":3,"tag":"IMAX厅"},{"desc":"1.3M以下儿童普通厅免费观影无座(IMAX厅/Screen X厅/见面会/首映礼等特殊场次除外，需另行购票)","type":7,"tag":"儿童优惠"},{"desc":"丹尼斯地下停车场24小时开放","type":5,"tag":"可停车"},{"desc":"免费提供3D眼睛，无需押金","type":4,"tag":"3D眼镜"},{"desc":"影院有免费WIFI","type":9,"tag":"WiFi"}]
             * deals :
             * sellmin : 15
             * sell : true
             * nm : CGV星聚汇影城(大卫城店)
             * id : 14497
             */

            private String addr;
            private int price;
            private double lat;
            private double lng;
            private String ct;
            private String area;
            private int preferential;
            private String callboard;
            private String brd;
            private String dis;
            private String suw;
            private String dri;
            private String note;
            private int dealtp;
            private String bus;
            private String park;
            private int imax;
            private int snum;
            private double s;
            private double s1;
            private double s2;
            private double s3;
            private String deals;
            private int sellmin;
            private boolean sell;
            private String nm;
            private int id;
            private List<String> tel;
            private List<FeatureTagsBean> featureTags;

            public String getAddr() {
                return addr;
            }

            public void setAddr(String addr) {
                this.addr = addr;
            }

            public int getPrice() {
                return price;
            }

            public void setPrice(int price) {
                this.price = price;
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

            public String getArea() {
                return area;
            }

            public void setArea(String area) {
                this.area = area;
            }

            public int getPreferential() {
                return preferential;
            }

            public void setPreferential(int preferential) {
                this.preferential = preferential;
            }

            public String getCallboard() {
                return callboard;
            }

            public void setCallboard(String callboard) {
                this.callboard = callboard;
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

            public String getSuw() {
                return suw;
            }

            public void setSuw(String suw) {
                this.suw = suw;
            }

            public String getDri() {
                return dri;
            }

            public void setDri(String dri) {
                this.dri = dri;
            }

            public String getNote() {
                return note;
            }

            public void setNote(String note) {
                this.note = note;
            }

            public int getDealtp() {
                return dealtp;
            }

            public void setDealtp(int dealtp) {
                this.dealtp = dealtp;
            }

            public String getBus() {
                return bus;
            }

            public void setBus(String bus) {
                this.bus = bus;
            }

            public String getPark() {
                return park;
            }

            public void setPark(String park) {
                this.park = park;
            }

            public int getImax() {
                return imax;
            }

            public void setImax(int imax) {
                this.imax = imax;
            }

            public int getSnum() {
                return snum;
            }

            public void setSnum(int snum) {
                this.snum = snum;
            }

            public double getS() {
                return s;
            }

            public void setS(double s) {
                this.s = s;
            }

            public double getS1() {
                return s1;
            }

            public void setS1(double s1) {
                this.s1 = s1;
            }

            public double getS2() {
                return s2;
            }

            public void setS2(double s2) {
                this.s2 = s2;
            }

            public double getS3() {
                return s3;
            }

            public void setS3(double s3) {
                this.s3 = s3;
            }

            public String getDeals() {
                return deals;
            }

            public void setDeals(String deals) {
                this.deals = deals;
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

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public List<String> getTel() {
                return tel;
            }

            public void setTel(List<String> tel) {
                this.tel = tel;
            }

            public List<FeatureTagsBean> getFeatureTags() {
                return featureTags;
            }

            public void setFeatureTags(List<FeatureTagsBean> featureTags) {
                this.featureTags = featureTags;
            }

            public static class FeatureTagsBean {
                /**
                 * desc : 4号全新帕萨特ScreenX厅，130个座位
                 * type : 19
                 * tag : 60帧厅
                 */

                private String desc;
                private int type;
                private String tag;

                public String getDesc() {
                    return desc;
                }

                public void setDesc(String desc) {
                    this.desc = desc;
                }

                public int getType() {
                    return type;
                }

                public void setType(int type) {
                    this.type = type;
                }

                public String getTag() {
                    return tag;
                }

                public void setTag(String tag) {
                    this.tag = tag;
                }
            }
        }

        public static class DatesBean {
            /**
             * text : 今天 8月12日
             * slug : 2017-08-12
             */

            private String text;
            private String slug;

            public String getText() {
                return text;
            }

            public void setText(String text) {
                this.text = text;
            }

            public String getSlug() {
                return slug;
            }

            public void setSlug(String slug) {
                this.slug = slug;
            }
        }

        public static class MoviesBean {
            /**
             * sc : 9.7
             * preferential : 0
             * ver : 2D/3D/中国巨幕/全景声
             * isShowing : true
             * img : http://p0.meituan.net/165.220/movie/02ac72c0e8ee2987f7662ad921a2acc7999433.jpg
             * nm : 战狼2
             * id : 344264
             */

            private double sc;
            private int preferential;
            private String ver;
            private boolean isShowing;
            private String img;
            private String nm;
            private int id;

            public double getSc() {
                return sc;
            }

            public void setSc(double sc) {
                this.sc = sc;
            }

            public int getPreferential() {
                return preferential;
            }

            public void setPreferential(int preferential) {
                this.preferential = preferential;
            }

            public String getVer() {
                return ver;
            }

            public void setVer(String ver) {
                this.ver = ver;
            }

            public boolean isIsShowing() {
                return isShowing;
            }

            public void setIsShowing(boolean isShowing) {
                this.isShowing = isShowing;
            }

            public String getImg() {
                return img;
            }

            public void setImg(String img) {
                this.img = img;
            }

            public String getNm() {
                return nm;
            }

            public void setNm(String nm) {
                this.nm = nm;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }
        }
    }
}
