package com.daigou.core.domain

import java.io.Serializable

/**
 * Created by wt on 2017/2/10.
 */
data class Pages(var page: Int = 1,
                 var row: Int = 20,
                 var option: String = "",
                 var total: Long = 0L,
                 var data: Any? = null
) : Serializable {
    private val serialVersionUID = 1L
    var totalPage = 0L
        get():Long {
            var t = total / row
            if (total % row > 0) {
                t++
            }
            return t
        }
}