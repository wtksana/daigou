package com.daigou.core.dao

import com.daigou.core.domain.GoodsType
import com.daigou.core.util.Pages

/**
 * Created by wt on 2017/2/17.
 */
interface GoodsTypeMapper : BaseMapper<GoodsType> {

    fun getListByPages(pages: Pages<GoodsType>): List<GoodsType>

    fun getGoodsTypeList(): List<String>

    fun goodsTypeEdit(old: String, new: String): Int

}