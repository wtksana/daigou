package com.daigou.server.user

import com.daigou.core.domain.Pages
import com.daigou.core.service.UserService
import com.daigou.server.BaseController
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * Created by wt on 2017/2/10.
 */
@RestController
class UserController : BaseController() {
    val Log = LoggerFactory.getLogger(javaClass)!!

    @Autowired
    private val userService: UserService? = null

    @RequestMapping("/user/userList")
    fun userList(pages: Pages): Any {
        val userList = userService!!.getUserListBy(pages)
        if (userList != null) {
            return success(userList)
        } else {
            return fail()
        }
    }
}