package com.daigou.core.domain

import java.io.Serializable

/**
 * Created by wt on 2017/2/10.
 */
data class Pages(var page: Int = 0,
                 var row: Int = 20,
                 var option: String = ""
) : Serializable {
    private val serialVersionUID = 1L
    var offset = page * row
}