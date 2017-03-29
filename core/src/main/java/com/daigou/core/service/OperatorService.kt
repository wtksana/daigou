package com.daigou.core.service

import com.daigou.core.domain.Operator

/**
 * Created by wt on 2017/2/8.
 */
interface OperatorService {

    fun getByUuid(uuid: String): Operator?

    fun login(userName: String, pwd: String): Operator?

}