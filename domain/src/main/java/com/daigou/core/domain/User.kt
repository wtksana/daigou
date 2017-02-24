package com.daigou.core.domain

import java.io.Serializable
import java.util.*

/**
 * Created by wt on 2017/2/10.
 */
data class User(var uuid: String? = "",
                var userName: String? = "",
                var pwd: String? = "",
                var realName: String? = "",
                var mobile: String? = "",
                var address: String? = "",
                var status: Int = 1,
                var remark: String? = "",
                var inviteUser: String? = "",
                var createTime: Date? = null,
                var updateTime: Date? = null
) : Serializable {
    private val serialVersionUID = 1L
}