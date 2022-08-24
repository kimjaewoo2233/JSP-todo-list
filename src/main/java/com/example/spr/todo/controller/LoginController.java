package com.example.spr.todo.controller;

import com.example.spr.todo.dto.MemberDTO;
import com.example.spr.todo.service.MemberService;
import lombok.extern.java.Log;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.text.CollationKey;
import java.util.UUID;

@WebServlet("/login")
@Log
public class LoginController extends HttpServlet {
        @Override
        protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
                log.info("login get...");
                request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request,response);
        }

        @Override
        protected void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
                log.info("login post....");

                String mid = request.getParameter("mid");
                String mpw = request.getParameter("mpw");

                String auto = request.getParameter("auto"); //checkbox on인지확인가능

                boolean rememberMe =  auto != null && auto.equals("on");

              try{
                  MemberDTO memberDTO = MemberService.INSTANCE.login(mid,mpw);


                  if(rememberMe){
                      String uuid = UUID.randomUUID().toString();

                      MemberService.INSTANCE.updateUuid(mid,uuid);
                      memberDTO.setUuid(uuid);

                      Cookie rememberCookie = new Cookie("remember-me",uuid);
                      rememberCookie.setMaxAge(60*60*24*7);
                      rememberCookie.setPath("/");

                      response.addCookie(rememberCookie);
                  }
                  HttpSession session = request.getSession();
                  session.setAttribute("loginInfo",memberDTO);
                  response.sendRedirect("/todo/list");

              } catch (Exception e) {
                        response.sendRedirect("/login?result=error");
              }



        }
}
