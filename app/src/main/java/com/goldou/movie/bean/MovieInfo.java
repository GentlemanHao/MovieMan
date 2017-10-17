package com.goldou.movie.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/7/28 0028.
 */

public class MovieInfo {

    /**
     * control : {"expires":1800}
     * status : 0
     * data : {"hasNext":true,"movies":[{"late":false,"sn":0,"showInfo":"今天72家影院放映1268场","cnms":0,"pn":496,"vd":"","img":"http://p0.meituan.net/165.220/movie/02ac72c0e8ee2987f7662ad921a2acc7999433.jpg","sc":9.7,"ver":"2D/3D/中国巨幕/全景声","rt":"本周四上映","imax":false,"snum":100960,"dir":"吴京","star":"吴京,弗兰克·格里罗,吴刚","cat":"动作,战争","wish":400928,"3d":true,"nm":"战狼2","dur":123,"src":"","showDate":"","scm":"非洲战强敌，坦克玩漂移","preSale":0,"time":"","id":344264}]}
     */

    private Control control;
    private int status;
    private Data data;

    public Control getControl() {
        return control;
    }

    public void setControl(Control control) {
        this.control = control;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public static class Control {
        /**
         * expires : 1800
         */

        private int expires;

        public int getExpires() {
            return expires;
        }

        public void setExpires(int expires) {
            this.expires = expires;
        }
    }

    public static class Data {
        /**
         * hasNext : true
         * movies : [{"late":false,"sn":0,"showInfo":"今天72家影院放映1268场","cnms":0,"pn":496,"vd":"","img":"http://p0.meituan.net/165.220/movie/02ac72c0e8ee2987f7662ad921a2acc7999433.jpg","sc":9.7,"ver":"2D/3D/中国巨幕/全景声","rt":"本周四上映","imax":false,"snum":100960,"dir":"吴京","star":"吴京,弗兰克·格里罗,吴刚","cat":"动作,战争","wish":400928,"3d":true,"nm":"战狼2","dur":123,"src":"","showDate":"","scm":"非洲战强敌，坦克玩漂移","preSale":0,"time":"","id":344264}]
         */

        private boolean hasNext;
        private List<Movies> movies;

        public boolean isHasNext() {
            return hasNext;
        }

        public void setHasNext(boolean hasNext) {
            this.hasNext = hasNext;
        }

        public List<Movies> getMovies() {
            return movies;
        }

        public void setMovies(List<Movies> movies) {
            this.movies = movies;
        }

        public static class Movies {
            /**
             * late : false
             * sn : 0
             * showInfo : 今天72家影院放映1268场
             * cnms : 0
             * pn : 496
             * vd :
             * img : http://p0.meituan.net/165.220/movie/02ac72c0e8ee2987f7662ad921a2acc7999433.jpg
             * sc : 9.7
             * ver : 2D/3D/中国巨幕/全景声
             * rt : 本周四上映
             * imax : false
             * snum : 100960
             * dir : 吴京
             * star : 吴京,弗兰克·格里罗,吴刚
             * cat : 动作,战争
             * wish : 400928
             * 3d : true
             * nm : 战狼2
             * dur : 123
             * src :
             * showDate :
             * scm : 非洲战强敌，坦克玩漂移
             * preSale : 0
             * time :
             * id : 344264
             */

            private boolean late;
            private int sn;
            private String showInfo;
            private int cnms;
            private int pn;
            private String vd;
            private String img;
            private double sc;
            private String ver;
            private String rt;
            private boolean imax;
            private int snum;
            private String dir;
            private String star;
            private String cat;
            private int wish;
            private boolean _3d;
            private String nm;
            private int dur;
            private String src;
            private String showDate;
            private String scm;
            private int preSale;
            private String time;
            private int id;

            public boolean isLate() {
                return late;
            }

            public void setLate(boolean late) {
                this.late = late;
            }

            public int getSn() {
                return sn;
            }

            public void setSn(int sn) {
                this.sn = sn;
            }

            public String getShowInfo() {
                return showInfo;
            }

            public void setShowInfo(String showInfo) {
                this.showInfo = showInfo;
            }

            public int getCnms() {
                return cnms;
            }

            public void setCnms(int cnms) {
                this.cnms = cnms;
            }

            public int getPn() {
                return pn;
            }

            public void setPn(int pn) {
                this.pn = pn;
            }

            public String getVd() {
                return vd;
            }

            public void setVd(String vd) {
                this.vd = vd;
            }

            public String getImg() {
                return img;
            }

            public void setImg(String img) {
                this.img = img;
            }

            public double getSc() {
                return sc;
            }

            public void setSc(double sc) {
                this.sc = sc;
            }

            public String getVer() {
                return ver;
            }

            public void setVer(String ver) {
                this.ver = ver;
            }

            public String getRt() {
                return rt;
            }

            public void setRt(String rt) {
                this.rt = rt;
            }

            public boolean isImax() {
                return imax;
            }

            public void setImax(boolean imax) {
                this.imax = imax;
            }

            public int getSnum() {
                return snum;
            }

            public void setSnum(int snum) {
                this.snum = snum;
            }

            public String getDir() {
                return dir;
            }

            public void setDir(String dir) {
                this.dir = dir;
            }

            public String getStar() {
                return star;
            }

            public void setStar(String star) {
                this.star = star;
            }

            public String getCat() {
                return cat;
            }

            public void setCat(String cat) {
                this.cat = cat;
            }

            public int getWish() {
                return wish;
            }

            public void setWish(int wish) {
                this.wish = wish;
            }

            public boolean is_3d() {
                return _3d;
            }

            public void set_3d(boolean _3d) {
                this._3d = _3d;
            }

            public String getNm() {
                return nm;
            }

            public void setNm(String nm) {
                this.nm = nm;
            }

            public int getDur() {
                return dur;
            }

            public void setDur(int dur) {
                this.dur = dur;
            }

            public String getSrc() {
                return src;
            }

            public void setSrc(String src) {
                this.src = src;
            }

            public String getShowDate() {
                return showDate;
            }

            public void setShowDate(String showDate) {
                this.showDate = showDate;
            }

            public String getScm() {
                return scm;
            }

            public void setScm(String scm) {
                this.scm = scm;
            }

            public int getPreSale() {
                return preSale;
            }

            public void setPreSale(int preSale) {
                this.preSale = preSale;
            }

            public String getTime() {
                return time;
            }

            public void setTime(String time) {
                this.time = time;
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
