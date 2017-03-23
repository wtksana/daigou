package com.daigou.common.constant

/**
 * Created by wt on 2017/2/14.
 */
object UrlConstant {
    var server_url = "http://127.0.0.1:8080"

    const val sys_login = "/sys/login"
    const val sys_logout = "/sys/logout"

    const val user_list = "/user/userList"
    const val user_add = "/user/userAdd"
    const val user_edit = "/user/userEdit"
    const val user_delete = "/user/userDelete"
    const val user_export = "/user/userExport"

    const val goods_list = "/goods/goodsList"
    const val goods_add = "/goods/goodsAdd"
    const val goods_edit = "/goods/goodsEdit"
    const val goods_delete = "/goods/goodsDelete"
    const val goods_export = "/goods/goodsExport"

    const val order_list = "/order/orderList"
    const val order_add = "/order/orderAdd"
    const val order_edit = "/order/orderEdit"
    const val order_delete = "/order/orderDelete"
    const val order_export = "/order/orderExport"

    const val goods_type_list = "/goods/goodsTypeList"
    const val goods_type_add = "/goods/goodsTypeAdd"
    const val goods_type_edit = "/goods/goodsTypeEdit"
    const val goods_type_delete = "/goods/goodsTypeDelete"
}
