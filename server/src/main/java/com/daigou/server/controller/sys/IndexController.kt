package com.daigou.server.controller.sys

import com.daigou.server.controller.BaseController
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping

/**
 * Created by wt on 2017/2/8.
 */
@Controller
class IndexController : BaseController() {

    @RequestMapping("/index")
    fun index(): String {
        val operator = request.session.getAttribute("operator_session")
        if (operator != null) {
            return "/sys/home"
        }
        return "/index"
    }
}
