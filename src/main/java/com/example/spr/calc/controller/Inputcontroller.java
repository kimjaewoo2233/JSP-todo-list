package com.example.spr.calc.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.print.Pageable;
import java.io.IOException;

@WebServlet(name="inputController",urlPatterns = "/calc/input")
public class Inputcontroller extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        System.out.println("InputController..doGet...");

        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/calc/input.jsp");

        dispatcher.forward(request,response);   //RequestDispatcher는 전달된 요청(Request_)를 다른쪽으로 전달함
    }
}
