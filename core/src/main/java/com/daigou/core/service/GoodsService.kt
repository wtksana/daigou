package com.daigou.core.service

import com.daigou.core.domain.Goods
import com.daigou.core.util.Pages

/**
 * Created by wt on 2017/2/15.
 */
interface GoodsService {

    fun save(model: Goods): Boolean

    fun update(model: Goods): Boolean

    fun deleteByUuid(uuid: String): Boolean

    fun getByUuid(uuid: String): Goods?

    fun countByModel(model: Goods): Int

    fun getListByPages(pages: Pages<Goods>): Pages<Goods>
}