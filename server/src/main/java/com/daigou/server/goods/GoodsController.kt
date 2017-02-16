package com.daigou.server.user

import com.daigou.common.util.UrlConstant
import com.daigou.core.domain.Goods
import com.daigou.core.service.GoodsService
import com.daigou.core.util.Pages
import com.daigou.server.BaseController
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody
import java.util.*

/**
 * Created by wt on 2017/2/10.
 */
@Controller
class GoodsController : BaseController() {
    val Log = LoggerFactory.getLogger(javaClass)!!

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
        model.uuid = UUID.randomUUID().toString().replace("-", "")
        Log.info(model.uuid)
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

    @RequestMapping(UrlConstant.goods_type_list)
    @ResponseBody
    fun goodsTypeList(): Any {
        val rs = service!!.getGoodsTypeList()
        if (rs.isNotEmpty()) {
            return success(rs)
        } else {
            return fail()
        }
    }

    @RequestMapping(UrlConstant.goods_type_edit)
    @ResponseBody
    fun goodsTypeEdit(old: String, new: String): Any {
        val rs = service!!.goodsTypeEdit(old, new)
        if (rs > 0) {
            return success()
        } else {
            return fail()
        }
    }


}