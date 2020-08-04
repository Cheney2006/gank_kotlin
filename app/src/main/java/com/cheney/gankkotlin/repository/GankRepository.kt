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
            .map {
                if (it.isOk) {
                    it.data
                } else {
                    throw IOException("Get Error From Server")
                }
            }.subscribeOn(Schedulers.io())

    }

    /**
     * 获取所有分类具体子分类[types]数据
     *
     * @param category 可接受参数 Article | GanHuo | Girl Article: 专题分类、 GanHuo: 干货分类 、 Girl:妹子图
     */
    fun getCategoryTypes(category: String): Single<List<CategoryType>?> {
        return gankService.getCategoryTypes(category)
            .map {
                if (it.isOk) it.data else throw IOException("Get Error From Server")
            }
            .subscribeOn(Schedulers.io())
    }

    /**
     * 分类数据 API
     *
     * @param category 可接受参数 All(所有分类) | Article | GanHuo | Girl
     * @param type     可接受参数 All(全部类型) | Android | iOS | Flutter | Girl ...，即分类API返回的类型数据
     * @param count    [10, 50]
     * @param page     >=1
     * @return
     */
    fun getByCategoryType(
        category: String,
        type: String,
        count: Int,
        page: Int
    ): Single<Resource<List<Gank>>> {
        return gankService.getByCategoryType(category, type, count, page)
            .map {
                if (it.isOk) it else throw IOException("Get Error From Server")
            }
            .subscribeOn(Schedulers.io())
    }
}