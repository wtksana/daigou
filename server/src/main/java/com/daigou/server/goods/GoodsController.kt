package com.daigou.server.user

import com.daigou.common.util.UrlConstant
import com.daigou.core.domain.Goods
import com.daigou.core.service.GoodsService
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
class GoodsController : BaseController() {

    @Autowired
    private val service: GoodsService? = null

    @RequestMapping(UrlConstant.goods_list)
    @ResponseBody
    fun goodsList(pages: Pages<Goods>): Any {
        val pages = service!!.getListByPages(pages)
        if (pages.data != null) {
            return success(pages)
        } else {
            return fail()
        }
    }

    @RequestMapping(UrlConstant.goods_add)
    @ResponseBody
    fun goodsAdd(model: Goods): Any {
        val rs = service!!.save(model)
        if (rs) {
            return success()
        } else {
            return fail()
        }
    }

    @RequestMapping(UrlConstant.goods_edit)
    @ResponseBody
    fun goodsEdit(model: Goods): Any {
        val rs = service!!.update(model)
        if (rs) {
            return success()
        } else {
            return fail()
        }
    }

}