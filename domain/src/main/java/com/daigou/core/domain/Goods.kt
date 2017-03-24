package com.daigou.core.domain

import java.io.Serializable
import java.util.*

/**
 * Created by wt on 2017/2/15.
 */
data class Goods(var uuid: String = "", //主键
                 var typeUuid: String = "", //商品类型uuid
                 var typeName: String = "", //商品类型
                 var goodsName: String = "", //商品名称
                 var price: Double = 0.00, //售价
                 var counter: Double = 0.00, //专柜价
                 var bid: Double = 0.00, //进价
                 var status: Int = 1, //状态 1：正常 0：删除
                 var remark: String? = "", //备注
                 var createTime: Date? = null, //创建时间
                 var updateTime: Date? = null//更新时间
) : Serializable {
    private val serialVersionUID = 1L
}