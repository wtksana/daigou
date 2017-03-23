package com.daigou.server.interceptor

import org.springframework.web.servlet.ModelAndView
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 * Created by wt on 2017/3/22.
 */
open class AuthenticationInterceptor : HandlerInterceptorAdapter() {
    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
        val operator = request.session.getAttribute("operator_session")
        if (operator == null) {
            val requestedWith = request.getHeader("x-requested-with")
            val loginUrl = "/index.html"
            // ajax请求
            if (requestedWith != null && "XMLHttpRequest" == requestedWith) {
                val loginAlert = "<script>$.messager.alert('提示', '登录超时,请重新登录!',function(data){window.location.href='$loginUrl'});</script>"
                response.setHeader("session-status", "timeout")
                response.characterEncoding = "UTF-8"
                response.writer.print(loginAlert)
            } else {
                response.sendRedirect(loginUrl)
            }
            return false
        }
        return true
    }

    override fun postHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any, modelAndView: ModelAndView?) {
        val contextPath = request.contextPath
        if (modelAndView != null) {
            request.setAttribute("base", contextPath)
        }
    }
}