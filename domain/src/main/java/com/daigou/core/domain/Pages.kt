package com.daigou.core.domain

import java.io.Serializable

/**
 * Created by wt on 2017/2/10.
 */
data class Pages(var page: Int = 0,
                 var row: Int = 20,
                 var option: String = "",
                 var total: Int = 0,
                 var data: Any? = null
) : Serializable {
    private val serialVersionUID = 1L
    var offset = 0
        get():Int {
            val page = if (page > 0) page - 1 else page
            return page * row
        }
}