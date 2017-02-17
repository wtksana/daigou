package com.daigou.core.service.impl

import com.daigou.core.dao.GoodsTypeMapper
import com.daigou.core.domain.GoodsType
import com.daigou.core.service.GoodsTypeService
import com.daigou.core.util.Pages
import com.github.pagehelper.Page
import com.github.pagehelper.PageHelper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

/**
 * Created by wt on 2017/2/17.
 */
@Service
open class GoodsTypeServiceImpl : GoodsTypeService {
    @Autowired
    private val mapper: GoodsTypeMapper? = null

    override fun save(model: GoodsType): Boolean {
        if (model.type.isNullOrBlank()) {
            return false
        }
        model.uuid = UUID.randomUUID().toString().replace("-", "")
        mapper!!.save(model)
        return true
    }

    override fun update(model: GoodsType): Boolean {
        if (model.uuid.isNullOrBlank() || model.type.isNullOrBlank()) {
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

    override fun getByUuid(uuid: String): GoodsType? {
        if (uuid.isBlank()) {
            return null
        }
        return mapper!!.getByUuid(uuid)
    }

    override fun countByModel(model: GoodsType): Int {
        return mapper!!.countByModel(model)
    }

    override fun getListByPages(pages: Pages<GoodsType>): Pages<GoodsType> {
        PageHelper.startPage<GoodsType>(pages.page, pages.row, pages.order)
        val list = mapper!!.getListByPages(pages)
        val pages = Pages(list as Page<GoodsType>)
        return pages
    }

    override fun getGoodsTypeList(): List<String> {
        return mapper!!.getGoodsTypeList()
    }

    override fun goodsTypeEdit(old: String, new: String): Int {
        return mapper!!.goodsTypeEdit(old, new)
    }
}