package com.daigou.core.util

import com.daigou.core.domain.Operator
import com.daigou.core.domain.OperatorLog
import com.daigou.core.service.OperatorLogService
import org.aspectj.lang.JoinPoint
import org.aspectj.lang.annotation.AfterReturning
import org.aspectj.lang.annotation.Aspect
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

/**
 * Created by wt on 2017/3/29.
 */
@Aspect
@Component
open class OperatorLogAspect {

    @Autowired
    private val operatorLogService: OperatorLogService? = null

    @AfterReturning(pointcut = "execution(* com.daigou.core.service.*Service.save(..))||execution(* com.daigou.core.service.*Service.update(..))||execution(* com.daigou.core.service.*Service.delete*(..))||execution(* com.daigou.core.service.OrderService.orderDone(..))", returning = "rst")
    open fun Log(joinPoint: JoinPoint, rst: Any) {
        val log = OperatorLog()
        log.service = joinPoint.signature.declaringTypeName.split(".").last()
        log.method = joinPoint.signature.name
        log.params = joinPoint.args.first().toString()
        if (joinPoint.args.last() is Operator) {
            log.operator = (joinPoint.args.last() as Operator).userName
        }
        log.result = rst.toString()
        operatorLogService!!.addLog(log)
    }
}