package com.daigou.core.service

import com.daigou.core.domain.Pages

/**
 * Created by wt on 2017/2/10.
 */
interface UserService {
    fun getUserListBy(pages: Pages): Pages
}