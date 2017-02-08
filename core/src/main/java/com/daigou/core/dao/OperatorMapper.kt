package com.daigou.core.dao

import com.daigou.core.domain.Operator

/**
 * Created by wt on 2017/2/8.
 */
interface OperatorMapper {

    fun findOperatorByUuid(uuid: String): Operator

}