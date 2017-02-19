package com.daigou.core.service

import com.daigou.core.domain.GoodsType
import com.daigou.core.util.Pages

/**
 * Created by wt on 2017/2/17.
 */
interface GoodsTypeService {

    fun save(model: GoodsType): Boolean

    fun update(model: GoodsType): Boolean

    fun deleteByUuid(uuid: String): Boolean

    fun getByUuid(uuid: String): GoodsType?

    fun countByModel(model: GoodsType): Int

    fun getListByPages(pages: Pages<GoodsType>): Pages<GoodsType>

    fun getAll(): List<GoodsType>

}