package com.example.spr.todo.filter;


import com.example.spr.todo.dto.MemberDTO;
import com.example.spr.todo.service.MemberService;
import lombok.extern.log4j.Log4j2;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.EOFException;
import java.io.IOException;
import java.rmi.server.ExportException;
import java.util.Arrays;
import java.util.Optional;

@WebFilter(urlPatterns = {"/todo/*"})       //해당 경로에 요청에 대해 doFilter를 실행한다. /todo/.. 는 다 처리
@Log4j2
public class LoginCheckFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
            throws IOException, ServletException{
        log.info("Login check filter.....");

        HttpServletRequest req = (HttpServletRequest)request;
        HttpServletResponse resp = (HttpServletResponse)response;

        HttpSession session = req.getSession();

        if(session.getAttribute("loginInfo") == null){
            resp.sendRedirect("/login");
            return;
        }
        //session에 loginInfo 값이 없다면
        //쿠키를 체크함
        Cookie cookie = findCookie(req.getCookies(),"remember-me");

        //세션도 없고 쿠키도 없다면 그냥 로그인으로
        if(cookie == null){
            resp.sendRedirect("/login");
            return;
        }
        //쿠키가 존재하는 상황이라면
        log.info("cookie는 존재하는 상황");
        //uuid
        String uuid = cookie.getValue();

        try{
            MemberDTO memberDTO = MemberService.INSTANCE.getByUUId(uuid);

            log.info("쿠키의 값으로 조회한 사용자의 정보: "+memberDTO);
            if(memberDTO == null){
                throw new Exception("Cookie value is not valid");
            }
            //회원정보를 세션에추가
            session.setAttribute("loginInfo",memberDTO);
            filterChain.doFilter(request,response);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
          //doFilter는 필터가 필터링이 필요한 로직을 구현하는 부분이다.
        //doFilter 마지막에는 다음 필터나 목적지(서블릿,JSP)로 갈 수 있도록 FilterChain의 doFilter()를 실행한다.
    }

    private Cookie findCookie(Cookie[] cookies, String name) {
        if(cookies == null || cookies.length == 0){
            return null;
        }

        Optional<Cookie> result = Arrays.stream(cookies)
                .filter(ck -> ck.getName().equals(name))
                .findFirst();

        return result.isPresent() ? result.get() : null;

    }

}
