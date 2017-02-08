package com.daigou.server.sys

import com.daigou.core.service.OperatorService
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * Created by wt on 2017/2/8.
 */
@RestController
class OperatorController {
    val Log = LoggerFactory.getLogger(javaClass)!!

    @Autowired
    private val operatorService: OperatorService? = null

    @RequestMapping("/sys/login")
    fun login(): Any {
        val op = operatorService!!.findOperatorByUuid("54c294528b5041ff838380676a2ed643")
        Log.info(op.toString())
        return op
    }

}