package com.daigou.core.domain

import java.io.Serializable
import java.util.*

/**
 * Created by wt on 2017/2/8.
 */
data class Operator(val uuid: String = "",
                    val name: String = "",
                    val pwd: String = "",
                    val realName: String = "",
                    val mobile: String = "",
                    val createTime: Date = Date(),
                    val status: Int = 0,
                    val remark: String = ""
) : Serializable {
    private val serialVersionUID = 1L
}