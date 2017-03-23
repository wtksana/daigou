package com.daigou.server.controller.order

import com.daigou.common.constant.UrlConstant
import com.daigou.core.domain.Order
import com.daigou.core.service.OrderService
import com.daigou.core.util.Pages
import com.daigou.server.controller.BaseController
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody

/**
 * Created by wt on 2017/2/10.
 */
@Controller
class OrderController : BaseController() {

    @Autowired
    private val service: OrderService? = null

    @RequestMapping("/order/orderListPage")
    fun orderListPage(): String {
        return "/order/orderListPage"
    }

    @RequestMapping(UrlConstant.order_list)
    @ResponseBody
    fun orderList(pages: Pages<Order>): Any {
        val newPages = service!!.getListByPages(pages)
        if (newPages.data != null) {
            return success(newPages)
        } else {
            return fail()
        }
    }

    @RequestMapping("/order/orderAddPage")
    fun orderAddPage(): String {
        return "/order/orderAddPage"
    }

    @RequestMapping(UrlConstant.order_add)
    @ResponseBody
    fun orderAdd(model: Order): Any {
        val rs = service!!.save(model)
        if (rs) {
            return success()
        } else {
            return fail()
        }
    }

    @RequestMapping("/order/orderEditPage")
    fun orderEditPage(uuid: String): String {
        val order = service!!.getByUuid(uuid)
        request.setAttribute("model", order)
        return "/order/orderEditPage"
    }


    @RequestMapping(UrlConstant.order_edit)
    @ResponseBody
    fun orderEdit(model: Order): Any {
        val rs = service!!.update(model)
        if (rs) {
            return success()
        } else {
            return fail()
        }
    }

    @RequestMapping(UrlConstant.order_delete)
    @ResponseBody
    fun orderDelete(uuid: String): Any {
        val rs = service!!.deleteByUuid(uuid)
        if (rs) {
            return success()
        } else {
            return fail()
        }
    }

    @RequestMapping(UrlConstant.order_export)
    @ResponseBody
    fun orderExport(pages: Pages<Order>): Any {
        val list = service!!.getAllByPages(pages)
        if (list.isNotEmpty()) {
            pages.data = list
            return success(pages)
        } else {
            return fail()
        }
    }

}