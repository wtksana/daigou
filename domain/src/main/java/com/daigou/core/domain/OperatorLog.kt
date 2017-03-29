package com.daigou.core.domain

import java.io.Serializable
import java.util.*

/**
 * Created by wt on 2017/3/29.
 */
data class OperatorLog(var uuid: String = "",
                       var operator: String = "", //操作员
                       var service: String = "", //对象
                       var method: String = "", //方法
                       var params: String = "", //操作数据
                       var result: String = "", //操作结果
                       var status: Int = 1, //状态 1：正常 0：删除
                       var createTime: Date? = null //创建时间
) : Serializable {
    private val serialVersionUID = 1L
}