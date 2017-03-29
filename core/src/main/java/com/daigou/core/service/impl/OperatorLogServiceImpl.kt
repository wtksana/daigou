package com.daigou.core.service

import com.daigou.core.dao.OperatorLogMapper
import com.daigou.core.domain.OperatorLog
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

/**
 * Created by wt on 2017/2/8.
 */
@Service
open class OperatorLogServiceImpl : OperatorLogService {

    @Autowired
    private val mapper: OperatorLogMapper? = null

    override fun addLog(model: OperatorLog): Boolean {
        if (model.uuid.isNullOrBlank()) {
            model.uuid = UUID.randomUUID().toString().replace("-", "")
        }
        mapper!!.save(model)
        return true
    }
}