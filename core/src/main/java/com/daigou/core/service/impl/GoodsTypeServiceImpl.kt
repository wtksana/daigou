package com.daigou.core.service.impl

import com.daigou.core.dao.GoodsTypeMapper
import com.daigou.core.domain.GoodsType
import com.daigou.core.domain.Operator
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

    override fun save(model: GoodsType, operator: Operator): Boolean {
        if (model.typeName.isNullOrBlank()) {
            return false
        }
        if (model.uuid.isNullOrBlank()) {
            model.uuid = UUID.randomUUID().toString().replace("-", "")
        }
        mapper!!.save(model)
        return true
    }

    override fun update(model: GoodsType, operator: Operator): Boolean {
        if (model.uuid.isNullOrBlank() || model.typeName.isNullOrBlank()) {
            return false
        }
        mapper!!.update(model)
        return true
    }

    override fun deleteByUuid(uuid: String, operator: Operator): Boolean {
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
        PageHelper.startPage<GoodsType>(pages.pageNum, pages.pageSize, pages.sortOrder)
        val list = mapper!!.getListByPages(pages)
        val newPages = Pages(list as Page<GoodsType>)
        return newPages
    }

    override fun getAll(): List<GoodsType> {
        return mapper!!.getAll()
    }

}