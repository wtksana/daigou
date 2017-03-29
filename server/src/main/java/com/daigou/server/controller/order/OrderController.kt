package com.daigou.server.controller.order

import com.daigou.common.constant.Constant
import com.daigou.common.constant.UrlConstant
import com.daigou.core.domain.Operator
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

    @RequestMapping("/order/listPage")
    fun listPage(): String {
        return "/order/listPage"
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

    @RequestMapping("/order/addPage")
    fun addPage(): String {
        return "/order/addPage"
    }

    @RequestMapping(UrlConstant.order_add)
    @ResponseBody
    fun orderAdd(model: Order): Any {
        val operator = session.getAttribute(Constant.OPERATOR_SESSION) as Operator
        val rs = service!!.save(model, operator)
        if (rs) {
            return success()
        } else {
            return fail()
        }
    }

    @RequestMapping("/order/editPage")
    fun editPage(uuid: String): String {
        val model = service!!.getByUuid(uuid)
        request.setAttribute("model", model)
        return "/order/editPage"
    }


    @RequestMapping(UrlConstant.order_edit)
    @ResponseBody
    fun orderEdit(model: Order): Any {
        val operator = session.getAttribute(Constant.OPERATOR_SESSION) as Operator
        val rs = service!!.update(model, operator)
        if (rs) {
            return success()
        } else {
            return fail()
        }
    }

    @RequestMapping(UrlConstant.order_delete)
    @ResponseBody
    fun orderDelete(uuid: String): Any {
        val operator = session.getAttribute(Constant.OPERATOR_SESSION) as Operator
        val rs = service!!.deleteByUuid(uuid, operator)
        if (rs) {
            return success()
        } else {
            return fail()
        }
    }

    @RequestMapping(UrlConstant.order_done)
    @ResponseBody
    fun orderDone(uuid: String): Any {
        val operator = session.getAttribute(Constant.OPERATOR_SESSION) as Operator
        val rs = service!!.orderDone(uuid, operator)
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

    @RequestMapping(UrlConstant.order_detail_list)
    @ResponseBody
    fun orderDetailList(uuid: String): Any {
        if (uuid.isNullOrEmpty()) {
            return fail()
        }
        val list = service!!.getOrderDetailListByOrderUuid(uuid)
        if (list.isNotEmpty()) {
            return success(list)
        } else {
            return fail()
        }
    }

}