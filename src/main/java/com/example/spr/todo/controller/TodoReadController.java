package com.example.spr.todo.controller;


import com.example.spr.todo.dto.TodoDTO;
import com.example.spr.todo.service.TodoService;
import lombok.extern.log4j.Log4j2;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
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
            TodoDTO dto = todoService.get(tno);
            request.setAttribute("dto",dto);
            request.getRequestDispatcher("/WEB-INF/todo/read.jsp").forward(request,response);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new ServletException("read error");
        }

    }
}
