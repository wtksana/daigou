package com.daigou.server.controller.goods

import com.daigou.common.constant.UrlConstant
import com.daigou.core.domain.Goods
import com.daigou.core.service.GoodsService
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
class GoodsController : BaseController() {

    @Autowired
    private val service: GoodsService? = null

    @RequestMapping("/goods/goodsListPage")
    fun goodsListPage(): String {
        return "/goods/goodsListPage"
    }

    @RequestMapping(UrlConstant.goods_list)
    @ResponseBody
    fun goodsList(pages: Pages<Goods>): Any {
        val newPages = service!!.getListByPages(pages)
        if (newPages.data != null) {
            return success(newPages)
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

    @RequestMapping(UrlConstant.goods_delete)
    @ResponseBody
    fun goodsDelete(uuid: String): Any {
        val rs = service!!.deleteByUuid(uuid)
        if (rs) {
            return success()
        } else {
            return fail()
        }
    }

    @RequestMapping(UrlConstant.goods_export)
    @ResponseBody
    fun goodsExport(pages: Pages<Goods>): Any {
        val list = service!!.getAllByPages(pages)
        if (list.isNotEmpty()) {
            pages.data = list
            return success(pages)
        } else {
            return fail()
        }
    }

}