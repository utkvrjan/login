package com.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
//触发过滤器的url正则格式就是以下三种：addServlet、logoutServlet、doneServlet
@WebFilter(filterName = "the_Filter",urlPatterns = {"/addServlet/*","/logoutServlet/*","/doneServlet/*"})
class the_Filter implements Filter {
    public void destroy() {
    }
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest)req;
        HttpServletResponse response = (HttpServletResponse)resp;
        Object flag = request.getSession().getAttribute("login");
        //已经登录，标志位存在
        if(null != flag){
            chain.doFilter(req, resp);
        }
        //没有登陆，重定向到登陆界面
        else{
            response.sendRedirect(request.getContextPath()+"/login.html");
        }
    }
    public void init(FilterConfig config) throws ServletException {
    }
}