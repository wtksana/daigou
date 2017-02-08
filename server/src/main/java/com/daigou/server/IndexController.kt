package com.daigou.server

import org.slf4j.LoggerFactory
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping

/**
 * Created by wt on 2017/2/8.
 */
@Controller
class IndexController {
    val Log = LoggerFactory.getLogger(javaClass)!!

    @RequestMapping("/index")
    fun index(): String {
        return "index"
    }
}
