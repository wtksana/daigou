package com.daigou.core.service.impl

import com.daigou.core.dao.UserMapper
import com.daigou.core.domain.Operator
import com.daigou.core.domain.User
import com.daigou.core.service.UserService
import com.daigou.core.util.Pages
import com.github.pagehelper.Page
import com.github.pagehelper.PageHelper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

/**
 * Created by wt on 2017/2/10.
 */
@Service
open class UserServiceImpl : UserService {

    @Autowired
    private val mapper: UserMapper? = null

    override fun save(model: User, operator: Operator): Boolean {
        if (model.userName.isNullOrBlank()) {
            return false
        }
        model.uuid = UUID.randomUUID().toString().replace("-", "")
        mapper!!.save(model)
        return true
    }

    override fun update(model: User, operator: Operator): Boolean {
        if (model.uuid.isNullOrBlank() || model.userName.isNullOrBlank()) {
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

    override fun getByUuid(uuid: String): User? {
        if (uuid.isBlank()) {
            return null
        }
        return mapper!!.getByUuid(uuid)
    }

    override fun countByModel(model: User): Int {
        return mapper!!.countByModel(model)
    }

    override fun getListByPages(pages: Pages<User>): Pages<User> {
        PageHelper.startPage<User>(pages.pageNum, pages.pageSize, pages.sortOrder)
        val list = mapper!!.getListByPages(pages)
        val newPages = Pages(list as Page<User>)
        return newPages
    }

    override fun getAllByPages(pages: Pages<User>): List<User> {
        val list = mapper!!.getListByPages(pages)
        return list
    }
}