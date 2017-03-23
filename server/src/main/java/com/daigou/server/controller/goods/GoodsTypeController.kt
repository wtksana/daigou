package com.daigou.server.controller.goods

import com.daigou.common.constant.UrlConstant
import com.daigou.core.domain.GoodsType
import com.daigou.core.service.GoodsTypeService
import com.daigou.server.controller.BaseController
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
        val rs = service!!.getAll()
        if (rs.isNotEmpty()) {
            return success(rs)
        } else {
            return fail()
        }
    }

    @RequestMapping(UrlConstant.goods_type_edit)
    @ResponseBody
    fun goodsTypeEdit(goodsType: GoodsType): Any {
        val rs = service!!.update(goodsType)
        if (rs) {
            return success()
        } else {
            return fail()
        }
    }

    @RequestMapping(UrlConstant.goods_type_add)
    @ResponseBody
    fun goodsTypeAdd(goodsType: GoodsType): Any {
        val rs = service!!.save(goodsType)
        if (rs) {
            return success()
        } else {
            return fail()
        }
    }

}