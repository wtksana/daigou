package com.daigou.core.dao

import com.daigou.core.domain.Pages
import com.daigou.core.domain.User

/**
 * Created by wt on 2017/2/10.
 */
interface UserMapper {

    fun getUserListBy(pages: Pages): List<User>

}