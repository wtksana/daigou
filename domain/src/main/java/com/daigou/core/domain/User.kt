package com.daigou.core.domain

import java.io.Serializable
import java.util.*

/**
 * Created by wt on 2017/2/10.
 */
data class User(var uuid: String = "",
                var wechat: String = "",
                var realName: String = "",
                var mobile: String = "",
                var address: String = "",
                var status: Int = 0,
                var remark: String = "",
                var createTime: Date? = null
) : Serializable {
    private val serialVersionUID = 1L
}