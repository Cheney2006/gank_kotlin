package com.cheney.gankkotlin.api

import com.cheney.gankkotlin.bean.CategoryType
import com.cheney.gankkotlin.bean.Gank
import com.cheney.gankkotlin.bean.GankBanner
import com.cheney.gankkotlin.bean.Resource
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface GankService {
    /**
     * 首页banner轮播
     *
     * @return
     */
    @GET("banners")
    fun getBanners(): Single<Resource<List<GankBanner>>>

    /**
     * 本周最热 API
     *
     * @param hotType  可接受参数 views（浏览数） | likes（点赞数） | comments（评论数）
     * @param category 可接受参数 Article | GanHuo | Girl
     * @param count    [1, 20]
     * @return
     */
    @GET("hot/{hotType}/category/{category}/count/{count}")
    fun getHot(
        @Path("hotType") hotType: String,
        @Path("category") category: String,
        @Path("count") count: Int
    ): Single<Resource<List<Gank>>>


    /**
     * 获取所有分类具体子分类[types]数据
     *
     * @param category 可接受参数 Article | GanHuo | Girl Article: 专题分类、 GanHuo: 干货分类 、 Girl:妹子图
     */
    @GET("categories/{category_type}")
    fun getCategoryTypes(@Path("category_type") category: String): Single<Resource<List<CategoryType>>>

}