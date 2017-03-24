package com.daigou.core.domain

import java.io.Serializable
import java.util.*

/**
 * Created by wt on 2017/2/8.
 */
data class Operator(var uuid: String = "",
                    var userName: String = "",//用户名
                    var pwd: String = "",//密码
                    var realName: String = "",//真实姓名
                    var mobile: String = "",//手机号
                    var remark: String = "",//备注
                    var status: Int = 1,//状态 1：正常 2：删除
                    var createTime: Date? = null,//创建时间
                    var updateTime: Date? = null
) : Serializable {
    private val serialVersionUID = 1L
}