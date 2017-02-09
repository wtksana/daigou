package com.daigou.core.service

import com.daigou.core.dao.OperatorMapper
import com.daigou.core.domain.Operator
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 * Created by wt on 2017/2/8.
 */
@Service
open class OperatorServiceImpl : OperatorService {

    @Autowired
    private val operatorMapper: OperatorMapper? = null

    override fun findOperatorByUuid(uuid: String): Operator? {
        return operatorMapper!!.findOperatorByUuid(uuid)
    }
}