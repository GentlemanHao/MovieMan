package com.goldou.movie.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

/**
 * Created by Administrator on 2017/10/16 0016.
 */

@Entity
public class MovieWant {
    @Id
    private long id;
    private String nm;
    private String src;
    private String cat;
    private String rt;
    private String img;
    private String sc;

    @Generated(hash = 287219996)
    public MovieWant() {
    }

    @Generated(hash = 995054494)
    public MovieWant(long id, String nm, String src, String cat, String rt, String img, String sc) {
        this.id = id;
        this.nm = nm;
        this.src = src;
        this.cat = cat;
        this.rt = rt;
        this.img = img;
        this.sc = sc;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNm() {
        return nm;
    }

    public void setNm(String nm) {
        this.nm = nm;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public String getCat() {
        return cat;
    }

    public void setCat(String cat) {
        this.cat = cat;
    }

    public String getRt() {
        return rt;
    }

    public void setRt(String rt) {
        this.rt = rt;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getSc() {
        return sc;
    }

    public void setSc(String sc) {
        this.sc = sc;
    }
}
