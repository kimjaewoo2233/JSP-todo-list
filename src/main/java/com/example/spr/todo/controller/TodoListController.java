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
import java.util.List;

@WebServlet(name = "todoListController",urlPatterns = "/todo/list")
@Log4j2
public class TodoListController extends HttpServlet {

    private TodoService todoService = TodoService.INSTANCE;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        log.info("todo List......................");

        try{
            List<TodoDTO> dtoList = todoService.listAll();
            request.setAttribute("dtoList",dtoList);
            request.getRequestDispatcher("/WEB-INF/todo/list.jsp").forward(request,response);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
