package com.daigou.server.controller.user

import com.daigou.common.constant.Constant
import com.daigou.common.constant.UrlConstant
import com.daigou.core.domain.Operator
import com.daigou.core.domain.User
import com.daigou.core.service.UserService
import com.daigou.core.util.Pages
import com.daigou.server.controller.BaseController
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

    @RequestMapping("/user/listPage")
    fun listPage(): String {
        return "/user/listPage"
    }

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

    @RequestMapping("/user/addPage")
    fun addPage(): String {
        return "/user/addPage"
    }

    @RequestMapping(UrlConstant.user_add)
    @ResponseBody
    fun userAdd(user: User): Any {
        val operator = session.getAttribute(Constant.OPERATOR_SESSION) as Operator
        val rs = service!!.save(user, operator)
        if (rs) {
            return success()
        } else {
            return fail()
        }
    }

    @RequestMapping("/user/editPage")
    fun editPage(uuid: String): String {
        val model = service!!.getByUuid(uuid)
        request.setAttribute("model", model)
        return "/user/editPage"
    }

    @RequestMapping(UrlConstant.user_edit)
    @ResponseBody
    fun userEdit(user: User): Any {
        val operator = session.getAttribute(Constant.OPERATOR_SESSION) as Operator
        val rs = service!!.update(user, operator)
        if (rs) {
            return success()
        } else {
            return fail()
        }
    }

    @RequestMapping(UrlConstant.user_delete)
    @ResponseBody
    fun userDelete(uuid: String): Any {
        val operator = session.getAttribute(Constant.OPERATOR_SESSION) as Operator
        val rs = service!!.deleteByUuid(uuid, operator)
        if (rs) {
            return success()
        } else {
            return fail()
        }
    }

    @RequestMapping(UrlConstant.user_export)
    @ResponseBody
    fun userExport(pages: Pages<User>): Any {
        val list = service!!.getAllByPages(pages)
        if (list.isNotEmpty()) {
            pages.data = list
            return success(pages)
        } else {
            return fail()
        }
    }
}