package com.daigou.core.service.impl

import com.daigou.core.dao.GoodsMapper
import com.daigou.core.domain.Goods
import com.daigou.core.service.GoodsService
import com.daigou.core.util.Pages
import com.github.pagehelper.Page
import com.github.pagehelper.PageHelper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

/**
 * Created by wt on 2017/2/15.
 */
@Service
open class GoodsServiceImpl : GoodsService {
    @Autowired
    private val mapper: GoodsMapper? = null

    override fun save(model: Goods): Boolean {
        if (model.goodsName.isNullOrBlank()) {
            return false
        }
        if (model.uuid.isNullOrBlank()) {
            model.uuid = UUID.randomUUID().toString().replace("-", "")
        }
        mapper!!.save(model)
        return true
    }

    override fun update(model: Goods): Boolean {
        if (model.uuid.isNullOrBlank() || model.goodsName.isNullOrBlank()) {
            return false
        }
        mapper!!.update(model)
        return true
    }

    override fun deleteByUuid(uuid: String): Boolean {
        if (uuid.isBlank()) {
            return false
        }
        mapper!!.deleteByUuid(uuid)
        return true
    }

    override fun getByUuid(uuid: String): Goods? {
        if (uuid.isBlank()) {
            return null
        }
        return mapper!!.getByUuid(uuid)
    }

    override fun countByModel(model: Goods): Int {
        return mapper!!.countByModel(model)
    }

    override fun getListByPages(pages: Pages<Goods>): Pages<Goods> {
        PageHelper.startPage<Goods>(pages.pageNum, pages.pageSize, pages.sortOrder)
        val list = mapper!!.getListByPages(pages)
        val newPages = Pages(list as Page<Goods>)
        return newPages
    }

    override fun getAllByPages(pages: Pages<Goods>): List<Goods> {
        val list = mapper!!.getListByPages(pages)
        return list
    }
}