package com.daigou.core.service

import com.daigou.core.domain.User
import com.daigou.core.util.Pages

/**
 * Created by wt on 2017/2/10.
 */
interface UserService {
    fun getUserListBy(pages: Pages<User>): Pages<User>
}