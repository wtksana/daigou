package com.daigou.core.service

import com.daigou.core.domain.OperatorLog

/**
 * Created by wt on 2017/2/8.
 */
interface OperatorLogService {

    fun addLog(operatorLog: OperatorLog): Boolean

}