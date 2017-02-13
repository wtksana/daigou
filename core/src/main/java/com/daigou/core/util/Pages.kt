package com.daigou.core.util

import com.github.pagehelper.Page

/**
 * Created by wt on 2017/2/13.
 */
class Pages<T> : java.io.Serializable {

    val serialVersionUID = 1L

    var page: Int = 0 //当前第几页

    var row: Int = 0//系着页最大条件

    var data: List<T>? = null//分页列表

    var total: Long = 0//记录总数

    var totalPage: Long = 0 //总页数

    var option: String = ""

    constructor(list: List<T>, total: Long, page: Int, row: Int) {
        this.data = list
        this.total = total
        this.page = page
        this.row = row

        this.totalPage = total / row
        if (total % row > 0) {
            this.totalPage++
        }

    }

    constructor(page: Page<T>) {
        this.data = page.result
        this.total = page.total
        this.page = page.pageNum
        this.row = page.pageSize

        this.totalPage = total / row
        if (total % row > 0) {
            this.totalPage++
        }

    }

    constructor()

}