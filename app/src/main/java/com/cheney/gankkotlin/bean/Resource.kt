package com.cheney.gankkotlin.bean

/**
 * page : 1
 * page_count : 245
 * status : 100
 * data : [] 集合
 * data : {} 单个
 * total_counts : 2445
 */

data class Resource<out T>(
    val page: Int?,
    val pager_count: Int?,
    val status: Int,
    val data: T?,
    val total_counts: Int?
) {

    val isOk: Boolean
        get() = status == STATUS_SUCCESS

    companion object {
        const val STATUS_SUCCESS = 100
    }

}