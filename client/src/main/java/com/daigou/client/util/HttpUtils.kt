package com.daigou.client.util

/**
 * Created by wt on 2017/2/14.
 */
fun mapToParams(map: Map<String, String>): String {
    val result = StringBuilder().append("?")
    for ((key, value) in map) {
        result.append(key).append("=").append(value).append("&")
    }
    if (result.length > 0) {
        result.deleteCharAt(result.length - 1)
    }
    return result.toString()
}