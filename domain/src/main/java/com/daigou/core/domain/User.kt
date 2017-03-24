package com.daigou.core.domain

import java.io.Serializable
import java.util.*

/**
 * Created by wt on 2017/2/10.
 */
data class User(var uuid: String? = "",
                var userName: String? = "",//用户名
                var pwd: String? = "",//密码
                var realName: String? = "",//真实姓名
                var mobile: String? = "",//手机号
                var address: String? = "",//地址
                var status: Int = 1,//状态 1：正常 0：删除
                var remark: String? = "",//备注
                var inviteUser: String? = "",//邀请人
                var createTime: Date? = null,
                var updateTime: Date? = null
) : Serializable {
    private val serialVersionUID = 1L
}