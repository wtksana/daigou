package com.daigou.core.dao

import com.daigou.core.domain.Goods
import com.daigou.core.util.Pages

/**
 * Created by wt on 2017/2/15.
 */
interface GoodsMapper : BaseMapper<Goods> {

    fun getListByPages(pages: Pages<Goods>): List<Goods>

    fun getGoodsTypeList(): List<String>

    fun goodsTypeEdit(old: String, new: String): Int

}