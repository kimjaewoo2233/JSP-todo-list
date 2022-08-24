package com.example.spr.todo.filter;


import lombok.extern.log4j.Log4j2;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter(urlPatterns = {"/*"})
@Log4j2
public class EncodingFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException{

       log.info("UTF8 Filter ...........");
        HttpServletRequest req = (HttpServletRequest)request;
        //ServletRequest는 상위타입이어서 다운캐스팅이 필요하다
        req.setCharacterEncoding("UTF-8");


        chain.doFilter(request,response);
    }
}
