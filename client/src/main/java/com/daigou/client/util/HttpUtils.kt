package com.daigou.client.util

/**
 * Created by wt on 2017/2/14.
 */
fun mapToParams(map: Map<String, Any>): String {
    val result = StringBuilder().append("?")
    for ((key, value) in map) {
        if (value == null) {
            continue
        }
        result.append(key).append("=").append(value.toString()).append("&")
    }
    if (result.length > 0) {
        result.deleteCharAt(result.length - 1)
    }
    return result.toString()
}