package com.daigou.core.dao

import com.daigou.core.domain.Operator
import org.apache.ibatis.annotations.Param

/**
 * Created by wt on 2017/2/8.
 */
interface OperatorMapper : BaseMapper<Operator> {

    fun login(@Param("userName") userName: String, @Param("pwd") pwd: String): Operator?

}