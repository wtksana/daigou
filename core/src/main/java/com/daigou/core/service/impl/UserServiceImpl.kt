package com.daigou.core.service.impl

import com.daigou.core.dao.UserMapper
import com.daigou.core.domain.User
import com.daigou.core.domain.Pages
import com.daigou.core.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 * Created by wt on 2017/2/10.
 */
@Service
open class UserServiceImpl : UserService {

    @Autowired
    private val userMapper: UserMapper? = null

    override fun getUserListBy(pages: Pages): Pages {
        pages.data = userMapper!!.getUserListBy(pages)
        return pages
    }
}