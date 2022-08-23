package com.example.spr.todo.controller;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ServiceConfigurationError;

@WebServlet(name = "todoRegisterController",urlPatterns = "/todo/register")
public class TodoRegisterController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        System.out.println("입력 화면을 볼 수 있도록 구성");
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/todo/register.jsp");
        dispatcher.forward(request,response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

        System.out.println("입력을 처리하고 목록 페이지로 이동");  //계석 이상 태로 새로고침을 한다면 똑같은게 반복된다(저장) 그렇기에 redirect사용

        response.sendRedirect("/todo/list");
    }
}
