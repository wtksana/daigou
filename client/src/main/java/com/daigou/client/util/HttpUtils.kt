package com.daigou.client.util

import com.alibaba.fastjson.JSONArray
import com.daigou.common.constant.UrlConstant
import com.daigou.core.domain.Results
import org.apache.commons.codec.binary.Base64
import org.apache.http.NameValuePair
import org.apache.http.client.entity.UrlEncodedFormEntity
import org.apache.http.client.methods.HttpPost
import org.apache.http.impl.client.HttpClients
import org.apache.http.message.BasicNameValuePair
import org.apache.http.util.EntityUtils

/**
 * Created by wt on 2017/2/14.
 */
fun mapToParams(map: Map<String, Any?>): String {
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
    return ".html" + result.toString()
}


fun doPost(url: String, params: Map<String, Any?>): Results {
    val charset = "UTF-8"
    var result = ""
    var results = Results()
    try {
        val httpClient = HttpClients.createDefault()
        val httpPost = HttpPost(UrlConstant.server_url + url + ".html")
//        httpClient = HttpClients.createDefault()
//        httpPost = HttpPost(UrlConstant.server_url + url)
        //设置参数
        var list = arrayListOf<NameValuePair>()
        val iterator = params.iterator()
        while (iterator.hasNext()) {
            val elem = iterator.next()
            if (elem.value == null) {
                continue
            }
            list.add(BasicNameValuePair(elem.key, elem.value.toString()))
        }
        if (list.size > 0) {
            val entity = UrlEncodedFormEntity(list, charset)
            httpPost.entity = entity
        }
        val response = httpClient.execute(httpPost)
        if (response != null) {
            val resEntity = response.entity
            if (resEntity != null) {
                result = EntityUtils.toString(resEntity, charset)
            }
        }
    } catch(ex: Exception) {
        ex.printStackTrace()
    }
    if (result.isNotEmpty()) {
        results = JSONArray.parseObject(result, Results::class.java)
    }
    return results
}