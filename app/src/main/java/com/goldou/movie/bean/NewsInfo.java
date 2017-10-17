package com.goldou.movie.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/9/29 0029.
 */

public class NewsInfo {

    private List<Data> data;

    public List<Data> getData() {
        return data;
    }

    public void setData(List<Data> data) {
        this.data = data;
    }

    public static class Data {
        /**
         * article_id : 324
         * article_title : 《深夜食堂》扑街, 黄磊现危机, 吴昕深夜发文自省, 杨紫表示不服
         * article_image_type : 2
         * article_image : /upload_down/201706/18/thumb_201706181330250966.jpg,/upload_down/201706/18/thumb_201706181330252880.jpg,/upload_down/201706/18/thumb_201706181330256855.jpg,
         * article_from : admin
         * article_time : 06/18
         * article_comment_times : 0
         */

        private int article_id;
        private String article_title;
        private String article_image_type;
        private String article_image;
        private String article_from;
        private String article_time;
        private int article_comment_times;

        public int getArticle_id() {
            return article_id;
        }

        public void setArticle_id(int article_id) {
            this.article_id = article_id;
        }

        public String getArticle_title() {
            return article_title;
        }

        public void setArticle_title(String article_title) {
            this.article_title = article_title;
        }

        public String getArticle_image_type() {
            return article_image_type;
        }

        public void setArticle_image_type(String article_image_type) {
            this.article_image_type = article_image_type;
        }

        public String getArticle_image() {
            return article_image;
        }

        public void setArticle_image(String article_image) {
            this.article_image = article_image;
        }

        public String getArticle_from() {
            return article_from;
        }

        public void setArticle_from(String article_from) {
            this.article_from = article_from;
        }

        public String getArticle_time() {
            return article_time;
        }

        public void setArticle_time(String article_time) {
            this.article_time = article_time;
        }

        public int getArticle_comment_times() {
            return article_comment_times;
        }

        public void setArticle_comment_times(int article_comment_times) {
            this.article_comment_times = article_comment_times;
        }
    }
}
