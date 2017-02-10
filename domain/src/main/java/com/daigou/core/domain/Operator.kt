package com.daigou.core.domain

import java.io.Serializable
import java.util.*

/**
 * Created by wt on 2017/2/8.
 */
data class Operator(var uuid: String = "",
                    var name: String = "",
                    var pwd: String = "",
                    var realName: String = "",
                    var mobile: String = "",
                    var createTime: Date = Date(),
                    var status: Int = 0,
                    var remark: String = ""
) : Serializable {
    private val serialVersionUID = 1L
}