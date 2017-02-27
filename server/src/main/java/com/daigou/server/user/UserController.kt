package com.daigou.server.user

import com.daigou.common.util.UrlConstant
import com.daigou.core.domain.User
import com.daigou.core.service.UserService
import com.daigou.core.util.Pages
import com.daigou.server.BaseController
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody

/**
 * Created by wt on 2017/2/10.
 */
@Controller
class UserController : BaseController() {

    @Autowired
    private val service: UserService? = null

    @RequestMapping(UrlConstant.user_list)
    @ResponseBody
    fun userList(pages: Pages<User>): Any {
        val newPages = service!!.getListByPages(pages)
        if (newPages.data != null) {
            return success(newPages)
        } else {
            return fail()
        }
    }

    @RequestMapping(UrlConstant.user_add)
    @ResponseBody
    fun userAdd(user: User): Any {
        val rs = service!!.save(user)
        if (rs) {
            return success()
        } else {
            return fail()
        }
    }

    @RequestMapping(UrlConstant.user_edit)
    @ResponseBody
    fun userEdit(user: User): Any {
        val rs = service!!.update(user)
        if (rs) {
            return success()
        } else {
            return fail()
        }
    }

    @RequestMapping(UrlConstant.user_delete)
    @ResponseBody
    fun userDelete(uuid: String): Any {
        val rs = service!!.deleteByUuid(uuid)
        if (rs) {
            return success()
        } else {
            return fail()
        }
    }
}