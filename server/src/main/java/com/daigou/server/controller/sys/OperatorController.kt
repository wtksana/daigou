package com.daigou.server.controller.sys

import com.daigou.common.constant.Constant
import com.daigou.common.constant.UrlConstant
import com.daigou.core.domain.Operator
import com.daigou.core.service.OperatorService
import com.daigou.server.controller.BaseController
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.ResponseBody

/**
 * Created by wt on 2017/2/8.
 */
@Controller
class OperatorController : BaseController() {

    @Autowired
    private val service: OperatorService? = null


    @RequestMapping("/sys/home")
    fun home(): String {
        return "/sys/home"
    }

    @RequestMapping(value = UrlConstant.sys_login, method = arrayOf(RequestMethod.POST))
    @ResponseBody
    fun login(operator: Operator): Any {
        val op = service!!.login(operator.userName, operator.pwd)
        if (op != null) {
            request.session.setAttribute(Constant.OPERATOR_SESSION, op)
            return success()
        } else {
            return fail("用户名或密码错误")
        }
    }

    @RequestMapping(value = UrlConstant.sys_logout, method = arrayOf(RequestMethod.GET))
    @ResponseBody
    fun logout(): Any {
        session.setAttribute(Constant.OPERATOR_SESSION, null)
        return success()
    }

}