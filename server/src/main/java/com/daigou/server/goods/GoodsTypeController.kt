package com.daigou.server.goods

import com.daigou.common.util.UrlConstant
import com.daigou.core.service.GoodsTypeService
import com.daigou.server.BaseController
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody

/**
 * Created by wt on 2017/2/17.
 */
@Controller
class GoodsTypeController : BaseController() {

    @Autowired
    private val service: GoodsTypeService? = null

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