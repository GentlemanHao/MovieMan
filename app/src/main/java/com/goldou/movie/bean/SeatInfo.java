package com.goldou.movie.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/9/11 0011.
 */

public class SeatInfo {

    /**
     * sections : [{"rows":11,"sectionId":"01","sectionName":"普通座","columns":18,"seatRows":[{"columns":18,"rowId":"1","rowNum":1,"seats":[{"rowId":"1","rowNum":1,"columnId":"","seatNo":"","columnNum":0,"type":"E"},{"rowId":"1","rowNum":1,"columnId":"","seatNo":"","columnNum":1,"type":"E"},{"rowId":"1","rowNum":1,"columnId":"1","seatNo":"4101590102#01#01","columnNum":2,"type":"N"},{"rowId":"1","rowNum":1,"columnId":"2","seatNo":"4101590102#01#02","columnNum":3,"type":"N"},{"rowId":"1","rowNum":1,"columnId":"3","seatNo":"4101590102#01#03","columnNum":4,"type":"N"},{"rowId":"1","rowNum":1,"columnId":"4","seatNo":"4101590102#01#04","columnNum":5,"type":"N"},{"rowId":"1","rowNum":1,"columnId":"5","seatNo":"4101590102#01#05","columnNum":6,"type":"N"},{"rowId":"1","rowNum":1,"columnId":"6","seatNo":"4101590102#01#06","columnNum":7,"type":"N"},{"rowId":"1","rowNum":1,"columnId":"7","seatNo":"4101590102#01#07","columnNum":8,"type":"N"},{"rowId":"1","rowNum":1,"columnId":"8","seatNo":"4101590102#01#08","columnNum":9,"type":"N"},{"rowId":"1","rowNum":1,"columnId":"9","seatNo":"4101590102#01#09","columnNum":10,"type":"N"},{"rowId":"1","rowNum":1,"columnId":"10","seatNo":"4101590102#01#10","columnNum":11,"type":"N"},{"rowId":"1","rowNum":1,"columnId":"11","seatNo":"4101590102#01#11","columnNum":12,"type":"N"},{"rowId":"1","rowNum":1,"columnId":"12","seatNo":"4101590102#01#12","columnNum":13,"type":"N"},{"rowId":"1","rowNum":1,"columnId":"","seatNo":"","columnNum":14,"type":"E"},{"rowId":"1","rowNum":1,"columnId":"","seatNo":"","columnNum":15,"type":"E"},{"rowId":"1","rowNum":1,"columnId":"","seatNo":"","columnNum":16,"type":"E"},{"rowId":"1","rowNum":1,"columnId":"","seatNo":"","columnNum":17,"type":"E"}]},{},{},{},{},{},{},{},{},{},{}]}]
     * showInfo : {"desc":"","cinemaName":"CGV星聚汇影城(大卫城店)","hallName":"2厅","hallId":"0000000000000002","movieName":"蜘蛛侠：英雄归来","showTime":"今天 9月11日11:00","buyNumLimit":5,"tp":"3D","lang":"英语","price":36,"cinemaId":14497,"seqNo":"201709110094997","movieId":334620,"showId":94997,"showDate":"2017-09-11"}
     * user :
     */

    private ShowInfo showInfo;
    private String user;
    private List<Sections> sections;

    public ShowInfo getShowInfo() {
        return showInfo;
    }

    public void setShowInfo(ShowInfo showInfo) {
        this.showInfo = showInfo;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public List<Sections> getSections() {
        return sections;
    }

    public void setSections(List<Sections> sections) {
        this.sections = sections;
    }

    public static class ShowInfo {
        /**
         * desc :
         * cinemaName : CGV星聚汇影城(大卫城店)
         * hallName : 2厅
         * hallId : 0000000000000002
         * movieName : 蜘蛛侠：英雄归来
         * showTime : 今天 9月11日11:00
         * buyNumLimit : 5
         * tp : 3D
         * lang : 英语
         * price : 36
         * cinemaId : 14497
         * seqNo : 201709110094997
         * movieId : 334620
         * showId : 94997
         * showDate : 2017-09-11
         */

        private String desc;
        private String cinemaName;
        private String hallName;
        private String hallId;
        private String movieName;
        private String showTime;
        private int buyNumLimit;
        private String tp;
        private String lang;
        private int price;
        private int cinemaId;
        private String seqNo;
        private int movieId;
        private int showId;
        private String showDate;

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getCinemaName() {
            return cinemaName;
        }

        public void setCinemaName(String cinemaName) {
            this.cinemaName = cinemaName;
        }

        public String getHallName() {
            return hallName;
        }

        public void setHallName(String hallName) {
            this.hallName = hallName;
        }

        public String getHallId() {
            return hallId;
        }

        public void setHallId(String hallId) {
            this.hallId = hallId;
        }

        public String getMovieName() {
            return movieName;
        }

        public void setMovieName(String movieName) {
            this.movieName = movieName;
        }

        public String getShowTime() {
            return showTime;
        }

        public void setShowTime(String showTime) {
            this.showTime = showTime;
        }

        public int getBuyNumLimit() {
            return buyNumLimit;
        }

        public void setBuyNumLimit(int buyNumLimit) {
            this.buyNumLimit = buyNumLimit;
        }

        public String getTp() {
            return tp;
        }

        public void setTp(String tp) {
            this.tp = tp;
        }

        public String getLang() {
            return lang;
        }

        public void setLang(String lang) {
            this.lang = lang;
        }

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }

        public int getCinemaId() {
            return cinemaId;
        }

        public void setCinemaId(int cinemaId) {
            this.cinemaId = cinemaId;
        }

        public String getSeqNo() {
            return seqNo;
        }

        public void setSeqNo(String seqNo) {
            this.seqNo = seqNo;
        }

        public int getMovieId() {
            return movieId;
        }

        public void setMovieId(int movieId) {
            this.movieId = movieId;
        }

        public int getShowId() {
            return showId;
        }

        public void setShowId(int showId) {
            this.showId = showId;
        }

        public String getShowDate() {
            return showDate;
        }

        public void setShowDate(String showDate) {
            this.showDate = showDate;
        }
    }

    public static class Sections {
        /**
         * rows : 11
         * sectionId : 01
         * sectionName : 普通座
         * columns : 18
         * seatRows : [{"columns":18,"rowId":"1","rowNum":1,"seats":[{"rowId":"1","rowNum":1,"columnId":"","seatNo":"","columnNum":0,"type":"E"},{"rowId":"1","rowNum":1,"columnId":"","seatNo":"","columnNum":1,"type":"E"},{"rowId":"1","rowNum":1,"columnId":"1","seatNo":"4101590102#01#01","columnNum":2,"type":"N"},{"rowId":"1","rowNum":1,"columnId":"2","seatNo":"4101590102#01#02","columnNum":3,"type":"N"},{"rowId":"1","rowNum":1,"columnId":"3","seatNo":"4101590102#01#03","columnNum":4,"type":"N"},{"rowId":"1","rowNum":1,"columnId":"4","seatNo":"4101590102#01#04","columnNum":5,"type":"N"},{"rowId":"1","rowNum":1,"columnId":"5","seatNo":"4101590102#01#05","columnNum":6,"type":"N"},{"rowId":"1","rowNum":1,"columnId":"6","seatNo":"4101590102#01#06","columnNum":7,"type":"N"},{"rowId":"1","rowNum":1,"columnId":"7","seatNo":"4101590102#01#07","columnNum":8,"type":"N"},{"rowId":"1","rowNum":1,"columnId":"8","seatNo":"4101590102#01#08","columnNum":9,"type":"N"},{"rowId":"1","rowNum":1,"columnId":"9","seatNo":"4101590102#01#09","columnNum":10,"type":"N"},{"rowId":"1","rowNum":1,"columnId":"10","seatNo":"4101590102#01#10","columnNum":11,"type":"N"},{"rowId":"1","rowNum":1,"columnId":"11","seatNo":"4101590102#01#11","columnNum":12,"type":"N"},{"rowId":"1","rowNum":1,"columnId":"12","seatNo":"4101590102#01#12","columnNum":13,"type":"N"},{"rowId":"1","rowNum":1,"columnId":"","seatNo":"","columnNum":14,"type":"E"},{"rowId":"1","rowNum":1,"columnId":"","seatNo":"","columnNum":15,"type":"E"},{"rowId":"1","rowNum":1,"columnId":"","seatNo":"","columnNum":16,"type":"E"},{"rowId":"1","rowNum":1,"columnId":"","seatNo":"","columnNum":17,"type":"E"}]},{},{},{},{},{},{},{},{},{},{}]
         */

        private int rows;
        private String sectionId;
        private String sectionName;
        private int columns;
        private List<SeatRows> seatRows;

        public int getRows() {
            return rows;
        }

        public void setRows(int rows) {
            this.rows = rows;
        }

        public String getSectionId() {
            return sectionId;
        }

        public void setSectionId(String sectionId) {
            this.sectionId = sectionId;
        }

        public String getSectionName() {
            return sectionName;
        }

        public void setSectionName(String sectionName) {
            this.sectionName = sectionName;
        }

        public int getColumns() {
            return columns;
        }

        public void setColumns(int columns) {
            this.columns = columns;
        }

        public List<SeatRows> getSeatRows() {
            return seatRows;
        }

        public void setSeatRows(List<SeatRows> seatRows) {
            this.seatRows = seatRows;
        }

        public static class SeatRows {
            /**
             * columns : 18
             * rowId : 1
             * rowNum : 1
             * seats : [{"rowId":"1","rowNum":1,"columnId":"","seatNo":"","columnNum":0,"type":"E"},{"rowId":"1","rowNum":1,"columnId":"","seatNo":"","columnNum":1,"type":"E"},{"rowId":"1","rowNum":1,"columnId":"1","seatNo":"4101590102#01#01","columnNum":2,"type":"N"},{"rowId":"1","rowNum":1,"columnId":"2","seatNo":"4101590102#01#02","columnNum":3,"type":"N"},{"rowId":"1","rowNum":1,"columnId":"3","seatNo":"4101590102#01#03","columnNum":4,"type":"N"},{"rowId":"1","rowNum":1,"columnId":"4","seatNo":"4101590102#01#04","columnNum":5,"type":"N"},{"rowId":"1","rowNum":1,"columnId":"5","seatNo":"4101590102#01#05","columnNum":6,"type":"N"},{"rowId":"1","rowNum":1,"columnId":"6","seatNo":"4101590102#01#06","columnNum":7,"type":"N"},{"rowId":"1","rowNum":1,"columnId":"7","seatNo":"4101590102#01#07","columnNum":8,"type":"N"},{"rowId":"1","rowNum":1,"columnId":"8","seatNo":"4101590102#01#08","columnNum":9,"type":"N"},{"rowId":"1","rowNum":1,"columnId":"9","seatNo":"4101590102#01#09","columnNum":10,"type":"N"},{"rowId":"1","rowNum":1,"columnId":"10","seatNo":"4101590102#01#10","columnNum":11,"type":"N"},{"rowId":"1","rowNum":1,"columnId":"11","seatNo":"4101590102#01#11","columnNum":12,"type":"N"},{"rowId":"1","rowNum":1,"columnId":"12","seatNo":"4101590102#01#12","columnNum":13,"type":"N"},{"rowId":"1","rowNum":1,"columnId":"","seatNo":"","columnNum":14,"type":"E"},{"rowId":"1","rowNum":1,"columnId":"","seatNo":"","columnNum":15,"type":"E"},{"rowId":"1","rowNum":1,"columnId":"","seatNo":"","columnNum":16,"type":"E"},{"rowId":"1","rowNum":1,"columnId":"","seatNo":"","columnNum":17,"type":"E"}]
             */

            private int columns;
            private String rowId;
            private int rowNum;
            private List<Seats> seats;

            public int getColumns() {
                return columns;
            }

            public void setColumns(int columns) {
                this.columns = columns;
            }

            public String getRowId() {
                return rowId;
            }

            public void setRowId(String rowId) {
                this.rowId = rowId;
            }

            public int getRowNum() {
                return rowNum;
            }

            public void setRowNum(int rowNum) {
                this.rowNum = rowNum;
            }

            public List<Seats> getSeats() {
                return seats;
            }

            public void setSeats(List<Seats> seats) {
                this.seats = seats;
            }

            public static class Seats {
                /**
                 * rowId : 1
                 * rowNum : 1
                 * columnId :
                 * seatNo :
                 * columnNum : 0
                 * type : E
                 */

                private String rowId;
                private int rowNum;
                private String columnId;
                private String seatNo;
                private int columnNum;
                private String type;

                public String getRowId() {
                    return rowId;
                }

                public void setRowId(String rowId) {
                    this.rowId = rowId;
                }

                public int getRowNum() {
                    return rowNum;
                }

                public void setRowNum(int rowNum) {
                    this.rowNum = rowNum;
                }

                public String getColumnId() {
                    return columnId;
                }

                public void setColumnId(String columnId) {
                    this.columnId = columnId;
                }

                public String getSeatNo() {
                    return seatNo;
                }

                public void setSeatNo(String seatNo) {
                    this.seatNo = seatNo;
                }

                public int getColumnNum() {
                    return columnNum;
                }

                public void setColumnNum(int columnNum) {
                    this.columnNum = columnNum;
                }

                public String getType() {
                    return type;
                }

                public void setType(String type) {
                    this.type = type;
                }
            }
        }
    }
}
