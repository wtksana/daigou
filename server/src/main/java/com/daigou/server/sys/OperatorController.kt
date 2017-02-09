package com.daigou.server.sys

import com.daigou.core.service.OperatorService
import com.daigou.server.BaseController
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * Created by wt on 2017/2/8.
 */
@RestController
class OperatorController : BaseController() {
    val Log = LoggerFactory.getLogger(javaClass)!!

    @Autowired
    private val operatorService: OperatorService? = null

    @RequestMapping("/sys/login")
    fun login(uuid: String): Any {
        val op = operatorService!!.findOperatorByUuid(uuid)
        if (op != null) {
            return success(op)
        } else {
            return fail()
        }
    }

}