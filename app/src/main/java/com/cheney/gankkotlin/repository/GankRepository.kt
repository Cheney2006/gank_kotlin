package com.cheney.gankkotlin.repository

import com.cheney.gankkotlin.api.GankService
import com.cheney.gankkotlin.bean.CategoryType
import com.cheney.gankkotlin.bean.Gank
import com.cheney.gankkotlin.bean.GankBanner
import com.cheney.gankkotlin.bean.Resource
import com.cheney.gankkotlin.constants.CATEGORY_ARTICLE
import com.cheney.gankkotlin.constants.HOT_VIEWS
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GankRepository @Inject constructor(private val gankService: GankService) {
    /**
     * 取得banner
     *
     * @return
     */
    fun getBanner(): Single<List<GankBanner>?> {
        return gankService.getBanners().map { result: Resource<List<GankBanner>> ->
            if (result.isOk) {
                return@map result.data
            } else {
                throw IOException("Get Error From Server")
            }
        }.subscribeOn(Schedulers.io())
    }

    /**
     * 获取推荐
     *
     * @return
     */
    fun getHot(): Single<List<Gank>?> {
        return gankService.getHot(HOT_VIEWS, CATEGORY_ARTICLE, 20)
            .map { result: Resource<List<Gank>> ->
                if (result.isOk) {
                    return@map result.data
                } else {
                    throw IOException("Get Error From Server")
                }
            }.subscribeOn(Schedulers.io())

    }

    fun getCategoryTypes(category: String): Single<List<CategoryType>?> {
        return gankService.getCategoryTypes(category)
            .map { if (it.isOk) it.data else throw IOException("Get Error From Server") }
            .subscribeOn(Schedulers.io())
    }
}