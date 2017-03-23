package com.daigou.core.util

import com.github.pagehelper.Page
import java.io.Serializable

/**
 * Created by wt on 2017/2/13.
 */
class Pages<T> : Serializable {

    val serialVersionUID = 1L

    var pageNum: Int = 1 //当前第几页

    var pageSize: Int = 20//系着页最大条件

    var data: List<T>? = null//分页列表

    var total: Long = 0L//记录总数

    var totalPage: Long = 0L //总页数

    var search: String = ""
    var startTime: String? = null
        get() {
            if (field != null) {
                return field + " 00:00:00"
            }
            return field
        }
    var endTime: String? = null
        get() {
            if (field != null) {
                return field + " 23:59:59"
            }
            return field
        }

    val order = "desc"
    val sort = "create_time"
    val sortOrder = "create_time desc"

    constructor(list: List<T>, total: Long, pageNum: Int, pageSize: Int) {
        this.data = list
        this.total = total
        this.pageNum = pageNum
        this.pageSize = pageSize

        this.totalPage = total / pageSize
        if (total % pageSize > 0) {
            this.totalPage++
        }

    }

    constructor(page: Page<T>) {
        this.data = page.result
        this.total = page.total
        this.pageNum = page.pageNum
        this.pageSize = page.pageSize

        this.totalPage = total / pageSize
        if (total % pageSize > 0) {
            this.totalPage++
        }

    }

    constructor()

}