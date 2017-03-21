package com.daigou.server.sys

import com.daigou.server.BaseController
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping

/**
 * Created by wt on 2017/2/8.
 */
@Controller
class IndexController : BaseController() {

    @RequestMapping("/index")
    fun index(): String {
        return "/index"
    }
}
