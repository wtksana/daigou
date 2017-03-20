package com.daigou.core.domain

import java.io.Serializable

/**
 * Created by wt on 2017/3/18.
 */
data class Pages(var page: Int = 1, //当前第几页
                 var row: Int = 20, //系着页最大条件
                 var data: String = "", //分页列表
                 var total: Long = 0L, //记录总数
                 var totalPage: Long = 0L, //总页数
                 var option: String = "",
                 var startTime: String? = null,
                 var endTime: String? = null
) : Serializable {
    private val serialVersionUID = 1L
}