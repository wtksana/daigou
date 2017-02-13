package com.daigou.core.service.impl

import com.daigou.core.dao.UserMapper
import com.daigou.core.domain.User
import com.daigou.core.service.UserService
import com.daigou.core.util.Pages
import com.github.pagehelper.Page
import com.github.pagehelper.PageHelper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 * Created by wt on 2017/2/10.
 */
@Service
open class UserServiceImpl : UserService {

    @Autowired
    private val userMapper: UserMapper? = null

    override fun getUserListBy(pages: Pages<User>): Pages<User> {
        PageHelper.startPage<User>(pages.page, pages.row)
        val list = userMapper!!.getUserListBy(pages)
        val pages = Pages(list as Page<User>)
        return pages
    }
}