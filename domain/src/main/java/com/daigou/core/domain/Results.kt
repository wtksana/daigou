package com.daigou.core.domain

import java.io.Serializable

/**
 * Created by wt on 2017/3/18.
 */
data class Results(var result: Boolean = false,
                   var msg: String = "",
                   var data: String = "") : Serializable {
    private val serialVersionUID = 1L
}