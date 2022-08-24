package com.example.spr.todo.controller;


import com.example.spr.todo.dto.TodoDTO;
import com.example.spr.todo.service.TodoService;
import lombok.extern.log4j.Log4j2;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "todoReadController",urlPatterns = "/todo/read")
@Log4j2
public class TodoReadController extends HttpServlet{
    private TodoService todoService = TodoService.INSTANCE;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        System.out.println("/todo/read");

        // /todo/read?tno=123


        try {
            Long tno = Long.parseLong(request.getParameter("tno"));

            TodoDTO todoDTO = todoService.get(tno);

            //model담기
            request.setAttribute("dto",todoDTO);

            //쿠키찾기
            Cookie viewTodoCookie = findCookie(request.getCookies(),"viewTodos");
            String todoListStr = viewTodoCookie.getValue();
            boolean exist = false;

            if(todoListStr != null && todoListStr.indexOf(tno+"-") >= 0){
                exist = true;
            }
            log.info("exist: "+exist);

            if(!exist){
                todoListStr += tno+"-";
                viewTodoCookie.setValue(todoListStr);
                viewTodoCookie.setMaxAge(60*60*24);
                viewTodoCookie.setPath("/");
                response.addCookie(viewTodoCookie);
            }
            request.getRequestDispatcher("/WEB-INF/todo/read.jsp").forward(request,response);

        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            throw new ServletException("read error");
        }

    }

    private Cookie findCookie(Cookie[] cookies, String cookieName) {
        Cookie targetCookie = null;

        if(cookies != null && cookies.length > 0){
            for(Cookie ck:cookies){
                if(ck.getName().equals(cookieName)){
                    targetCookie = ck;
                    break;
                }
            }
        }
        if(targetCookie == null){
            targetCookie = new Cookie(cookieName,"");
            targetCookie.setPath("/");
            targetCookie.setMaxAge(60*60*24);
        }

        return targetCookie;
    }
}
