package com.goldou.movie.utils;

import com.goldou.movie.bean.CinemaInfo;
import com.goldou.movie.bean.MovieInfo;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Administrator on 2017/12/23 0023.
 */

public interface ApiService {
    /*
   * 猫眼电影影院
   * http://m.maoyan.com/cinemas.json
   */
    @GET("cinemas.json")
    Observable<CinemaInfo> getCinemas();

    /*
    * 猫眼电影列表
    * http://m.maoyan.com/movie/list.json?type=hot&offset=0&limit=1000
    */
    @GET("movie/list.json")
    Observable<MovieInfo> getMovie(@Query("type") String type, @Query("offset") int offset, @Query("limit") int limit);
}
