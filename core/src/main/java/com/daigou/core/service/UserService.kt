package com.daigou.core.service

import com.daigou.core.domain.User
import com.daigou.core.util.Pages

/**
 * Created by wt on 2017/2/10.
 */
interface UserService {

    fun save(model: User): Boolean

    fun update(model: User): Boolean

    fun deleteByUuid(uuid: String): Boolean

    fun getByUuid(uuid: String): User?

    fun countByModel(model: User): Int

    fun getListByPages(pages: Pages<User>): Pages<User>
}