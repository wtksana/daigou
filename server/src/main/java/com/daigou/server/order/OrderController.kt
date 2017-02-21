package com.daigou.server.user

import com.daigou.common.util.UrlConstant
import com.daigou.core.domain.Order
import com.daigou.core.service.OrderService
import com.daigou.core.util.Pages
import com.daigou.server.BaseController
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

    @RequestMapping(UrlConstant.order_list)
    @ResponseBody
    fun orderList(pages: Pages<Order>): Any {
        val pages = service!!.getListByPages(pages)
        if (pages.data != null) {
            return success(pages)
        } else {
            return fail()
        }
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

    @RequestMapping(UrlConstant.order_edit)
    @ResponseBody
    fun orderEdit(model: Order): Any {
        Log.info("model.uuid: ${model.uuid}")
        val rs = service!!.update(model)
        if (rs) {
            return success()
        } else {
            return fail()
        }
    }

}