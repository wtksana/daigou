package com.daigou.core.domain

/**
 * Created by wt on 2017/3/13.
 */
object ExcelConstant {
    val header = hashMapOf<Entity, Array<String>>()
    val fields = hashMapOf<Entity, Array<String>>()
    val title = hashMapOf<Entity, String>()

    init {
        fields.put(Entity.User, arrayOf("uuid", "userName", "pwd", "realName", "mobile", "address", "status", "remark", "inviteUser", "createTime", "updateTime"))
        header.put(Entity.User, arrayOf("ID", "用户名", "密码", "真实姓名", "手机号", "地址", "状态", "备注", "邀请人", "创建时间", "更新时间"))
        title.put(Entity.User, "客户表")
        fields.put(Entity.Order, arrayOf("uuid", "userUuid", "userName", "account", "detail", "opUuid", "status", "remark", "doneTime", "createTime", "updateTime"))
        header.put(Entity.Order, arrayOf("ID", "用户id", "用户名", "总价", "订单详情", "操作员id", "状态", "备注", "订单完成时间", "创建时间", "更新时间"))
        title.put(Entity.Order, "订单表")
        fields.put(Entity.Goods, arrayOf("uuid", "typeUuid", "type", "name", "price", "counter", "bid", "status", "remark", "createTime", "updateTime"))
        header.put(Entity.Goods, arrayOf("ID", "类型id", "类型", "商品名", "售价", "专柜价", "进价", "状态", "备注", "创建时间", "更新时间"))
        title.put(Entity.Goods, "商品表")

    }

}

enum class Entity {
    User,
    Order,
    Goods
}