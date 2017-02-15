package com.daigou.core.dao

import com.daigou.core.domain.User
import com.daigou.core.util.Pages

/**
 * Created by wt on 2017/2/10.
 */
interface UserMapper : BaseMapper<User> {

    fun getListByPages(pages: Pages<User>): List<User>

}