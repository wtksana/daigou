package com.daigou.core.service

import com.daigou.common.util.MD5Util
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
    private val mapper: OperatorMapper? = null

    override fun getByUuid(uuid: String): Operator? {
        return mapper!!.getByUuid(uuid)
    }

    override fun login(userName: String, pwd: String): Operator? {
        return mapper!!.login(userName, MD5Util().getMD5ofStr(pwd))
    }
}