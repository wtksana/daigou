package com.daigou.core.service

import com.daigou.core.domain.GoodsType
import com.daigou.core.domain.Operator
import com.daigou.core.util.Pages

/**
 * Created by wt on 2017/2/17.
 */
interface GoodsTypeService {

    fun save(model: GoodsType, operator: Operator): Boolean

    fun update(model: GoodsType, operator: Operator): Boolean

    fun deleteByUuid(uuid: String, operator: Operator): Boolean

    fun getByUuid(uuid: String): GoodsType?

    fun countByModel(model: GoodsType): Int

    fun getListByPages(pages: Pages<GoodsType>): Pages<GoodsType>

    fun getAll(): List<GoodsType>

}