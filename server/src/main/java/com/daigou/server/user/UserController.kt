package com.daigou.server.user

import com.daigou.common.util.UrlConstant
import com.daigou.core.domain.User
import com.daigou.core.service.UserService
import com.daigou.core.util.Pages
import com.daigou.server.BaseController
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody

/**
 * Created by wt on 2017/2/10.
 */
@Controller
class UserController : BaseController() {
    val Log = LoggerFactory.getLogger(javaClass)!!

    @Autowired
    private val userService: UserService? = null

    @RequestMapping(UrlConstant.user_list)
    @ResponseBody
    fun userList(pages: Pages<User>): Any {
        val pages = userService!!.getUserListBy(pages)
        if (pages.data != null) {
            return success(pages)
        } else {
            return fail()
        }
    }

    @RequestMapping(UrlConstant.user_add)
    @ResponseBody
    fun addUser(user: User): Any {
        println(user)
        return success()
    }
}