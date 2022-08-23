package com.example.spr.todo.controller;

import com.example.spr.todo.dto.TodoDTO;
import com.example.spr.todo.service.TodoService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "todoListController",urlPatterns = "/todo/list")
public class TodoListController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        System.out.println("/todo/List");

        List<TodoDTO> dtoList = TodoService.INSTANCE.getList();

        request.setAttribute("list",dtoList);

        request.getRequestDispatcher("/WEB-INF/todo/list.jsp").forward(request,response);
    }
}
