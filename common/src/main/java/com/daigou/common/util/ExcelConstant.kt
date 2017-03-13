package com.daigou.common.util

/**
 * Created by wt on 2017/3/13.
 */
object ExcelConstant {
    val header = hashMapOf<Entity, Array<String>>()
    val fields = hashMapOf<Entity, Array<String>>()
    val title = hashMapOf<Entity, String>()

    init {
        fields.put(Entity.USER, arrayOf("uuid", "userName", "pwd", "realName", "mobile", "address", "status", "remark", "inviteUser", "createTime", "updateTime"))
        header.put(Entity.USER, arrayOf("ID", "用户名", "密码", "真实姓名", "手机号", "地址", "状态", "备注", "邀请人", "创建时间", "更新时间"))
        title.put(Entity.USER, "客户表")
    }

}

enum class Entity {
    USER
}